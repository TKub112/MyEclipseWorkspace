package user;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;


import lombok.Getter;
import user.gui.Show;

/**
 * klasa reprezentuje klienta, zawiera wszytkie kozliwe akcje dla niego
 */
public class Client implements IUser
    {
    private String userName;
    @Getter
    private Path userPath;
    private List<FileData> fileList;
    private Lock lock = new ReentrantLock();
    private Show screen;
    private Socket socket;
    private volatile boolean ready = false;
    ExecutorService executor = Executors.newCachedThreadPool();
    ExecutorService executorForDownload = Executors.newSingleThreadExecutor();

    /**
     * wywolanie symbolizuje ze pliki z serera zostaly pobrane wiec klient jest gotowy do
     * wyswietlenia na ekran
     */
    public void setReady()
        {
        ready = true;
        }


    /**
     * @param userName nazwa usera
     * @param path     scieka jego na dysku
     * @param screen   interfejs do wyswietlania informacji
     * @throws IOException
     * @throws InterruptedException
     */
    public Client(String userName, String path, Show screen) throws IOException, InterruptedException
        {
        this.userName = userName;
        this.userPath = Paths.get(System.getProperty("user.home"));
        this.screen = screen;
        socket = new Socket();
        makeUserFolder(path);
        refreshServerFileList();
        }

    @Override
    public String getName()
        {
        return userName;
        }

    /**
     * odwierza liste plikow, jezeli dodano nowe to wysla je na dysk i dodaje do Gui usera
     *
     * @return czy bylo wymagane odswierzenie
     * @throws IOException
     */
    public boolean refreshFileList() throws IOException
        {
        int oldSize;
        try
            {
            lock.lock();
            screen.writePrompt("odswiezanie widoku");
            oldSize = fileList.size();
            Files.list(userPath).forEach(this::makeFileIfNotExist);
            }
        finally
            {
            lock.unlock();
            }
        return oldSize != fileList.size();
        }

    /**
     * usuwa pliki z gui ktore uzytkownik lokalnie usunol
     *
     * @return czy bylo wymagane odswierzenie
     * @throws IOException
     */
    public boolean cleanFileList() throws IOException
        {
        try
            {
            lock.lock();
            if (Files.list(userPath).count() == fileList.size())
                {
                return false;
                }

            fileList.removeAll(fileList.stream()
                    .filter(e -> ifFileWasDeletedRefreshGui(e.getName()))
                    .collect(Collectors.toList()));
            }
        finally
            {
            lock.unlock();
            }
        return true;
        }

    /**
     * pobiera z serwera pliki
     *
     * @return true oznacza gotowosc usera do dalszego dzialania
     * @throws InterruptedException
     */
    public boolean refreshServerFileList() throws InterruptedException
        {
        try
            {
            lock.lock();
            ready = false;

            while (!ready)
                {
                Task userDispatcherTask = new Task(screen,
                        new TaskData(null, userName, userName, this),
                        Task.OperationType.GET_FILES);
                try
                    {
                    executorForDownload.execute(userDispatcherTask);
                    Thread.sleep(1000);
                    }
                    catch (Exception e){

                e.printStackTrace();
                    }

                }

            }
        finally
            {
            lock.unlock();
            }

        return true;
        }

    /**
     * pobiera liste userow z serwera
     *
     * @return czy pobrano?
     */
    public boolean getUsersFromServer()
        {
        Task userDispatcherTask = new Task(screen,
                Task.OperationType.GET_USERS);

        executor.execute(userDispatcherTask);
        screen.writePrompt("odswiezanie listy userow");
        return true;
        }

    /**
     * wysla plik do serwera
     *
     * @param myFile plik do wyslania
     * @return
     */
    @Override
    public boolean sendFileToServer(FileData myFile)
        {
        Task userDispatcherTask = new Task(screen, new TaskData(myFile.getFilePath(),
                userName, myFile.getOtherUser()));
        executor.execute(userDispatcherTask);
        return true;
        }

    /**
     * @param name nazwa pliku
     * @param to   do kogo wyslamy
     * @return czy wyslano?
     * @see Client#sendFileToServer(FileData)
     */
    @Override
    public boolean sendFileToServer(String name, String to)
        {
        FileData myFile = new FileData(name, userName, to);
        sendFileToServer(myFile);
        return true;
        }

    private void makeUserFolder(String path) throws IOException
        {
        userPath = userPath.resolve(path);
        fileList = new ArrayList<>();
        Files.createDirectories(userPath);
        FileData.path = userPath;
        }

    private Boolean makeFileIfNotExist(Path path)
        {
        String fileName = path.getFileName().toString();

        Optional<String> file =
                fileList.stream()
                        .map(e -> e.getName())
                        .filter(e -> e.equals(fileName))
                        .findFirst();


        /*pliku nie ma na liscie wiec zostal nowo dodany*/

        if (!file.isPresent())
            {
            FileData myFileImpl = new FileData(fileName);
            fileList.add(myFileImpl);
            screen.addToFileList(fileName);
            sendFileToServer(myFileImpl);
            return true;
            }

        return false;
        }

    private Boolean ifFileWasDeletedRefreshGui(String fileName)
        {
        Optional<String> file = null;
        try
            {
            file = Files.list(userPath)
                    .map(e -> e.getFileName().toString())
                    .filter(e -> e.equals(fileName))
                    .findFirst();
            }
        catch (IOException e)
            {
            screen.writePrompt(e.getMessage());
            }
        /*plik jest na liscie wiec nie jest usuniety*/

        if (file.isPresent())
            {
            return false;
            }
        screen.deleteFromFileList(fileName);
        return true;
        }
    }
