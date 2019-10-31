package server.loadBalancer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import server.gui.ServerFrame;

import static java.lang.Math.abs;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;


/**
 * Klasa realizuje zapis jednego pliku, pobiera sciezki do serwerow i pule taskow.
 */
public class ServerSave implements Runnable
    {
    private LinkedBlockingQueue<Path> paths;
    private PriorityBlockingQueue<ServerSaveTask> tasks;
    private OutputStream out;
    private InputStream in;
    byte[] bytes = new byte[4096];

    /**
     * @param paths Lista serwerow
     * @param tasks Lista zadani do zapisania
     */
    public ServerSave(LinkedBlockingQueue<Path> paths, PriorityBlockingQueue<ServerSaveTask> tasks)
        {
        this.paths = paths;
        this.tasks = tasks;
        }

    @Override
    public void run()
        {
        try
            {
            Path path = paths.take();//czeka az bedzie woly server
            ServerSaveTask task = tasks.take();//pobranie taska o naj. piorytecie
            Path filePath = makeFile(path, task);
            in = task.getSocket().getInputStream();
            out = null;

            if (!ServerSaveData.fileExist(task.getUserName(), task.getFileName()))
                {
                wait(task);
                saveAndCopy(path, task, filePath);
                }
            else if (task.getSendTo().length() > 0 && !ServerSaveData.fileExist(task.getSendTo(),
                    task.getFileName()))
                {
                wait(task);
                saveOnlyToOtherUser(path, task);
                }

            if (out != null)
                {
                out.close();
                }
            paths.put(path);
            task.getSocket().close();
            task.getDone().set(true);
            }
        catch (Exception e)
            {
            e.printStackTrace();
            Logger.getAnonymousLogger().log(Level.WARNING, "problem with save");
            }

        }

    private void saveOnlyToOtherUser(Path path, ServerSaveTask task) throws IOException
        {
        out =
                Files.newOutputStream(path.resolve(task.getSendTo()).resolve(task.getFileName()));
        writeFromUser();

        copyToOtherServers(path, task, path.resolve(task.getSendTo()).resolve(task.getFileName())
                , task.getSendTo());

        ServerSaveData.addUserFilesRegister(task.getSendTo(),
                path.resolve(task.getSendTo()).resolve(task.getFileName()));
        }

    private void saveAndCopy(Path path, ServerSaveTask task, Path filePath) throws IOException
        {
        out = Files.newOutputStream(filePath, StandardOpenOption.CREATE);
        writeFromUser();
        ServerSaveData.addUserFilesRegister(task.getUserName(), filePath);

        copyToOtherServers(path, task, filePath, task.getUserName());


        if (task.getSendTo().length() > 0 && !ServerSaveData.fileExist(task.getSendTo(), task.getFileName()))
            {
            Files.copy(filePath, path.resolve(task.getSendTo()).resolve(task.getFileName()),
                    StandardCopyOption.REPLACE_EXISTING);

            copyToOtherServers(path, task, filePath, task.getSendTo());

            ServerSaveData.addUserFilesRegister(task.getSendTo(), path.resolve(task.getSendTo()).resolve(task.getFileName()));
            }
        }

    private void copyToOtherServers(Path path, ServerSaveTask task, Path filePath, String userName) throws IOException
        {

        Files.createDirectories(path.getParent().resolve("server1").resolve(userName));
        Files.createDirectories(path.getParent().resolve("server2").resolve(userName));
        Files.createDirectories(path.getParent().resolve("server3").resolve(userName));
        Files.createDirectories(path.getParent().resolve("server4").resolve(userName));

        saveInfoAboutFile(path, task, userName, "server0");

        Files.copy(filePath,
                path.getParent().resolve("server1").resolve(userName).resolve(task.getFileName()),
                StandardCopyOption.REPLACE_EXISTING);

        saveInfoAboutFile(path, task, userName, "server1");

        Files.copy(filePath,
                path.getParent().resolve("server2").resolve(userName).resolve(task.getFileName()),
                StandardCopyOption.REPLACE_EXISTING);

        saveInfoAboutFile(path, task, userName, "server2");

        Files.copy(filePath,
                path.getParent().resolve("server3").resolve(userName).resolve(task.getFileName()),
                StandardCopyOption.REPLACE_EXISTING);

        saveInfoAboutFile(path, task, userName, "server3");

        Files.copy(filePath,
                path.getParent().resolve("server4").resolve(userName).resolve(task.getFileName()),
                StandardCopyOption.REPLACE_EXISTING);

        saveInfoAboutFile(path, task, userName, "server4");
        }

    private void saveInfoAboutFile(Path path, ServerSaveTask task, String userName, String server) throws IOException
        {
        OutputStream os = Files.newOutputStream(path.getParent().resolve(server).resolve("log" +
                ".csv"), APPEND,CREATE);
        PrintWriter writer = new PrintWriter(os);
        writer.println(userName+":"+task.getFileName());
        writer.flush();
        writer.close();
        }

    private void writeFromUser() throws IOException
        {
        int count;//ilosc danych odczytu

        while ((count = in.read(bytes)) > 0)
            {
            out.write(bytes, 0, count);
            }
        }

    private Path makeFile(Path path, ServerSaveTask task) throws IOException
        {
        if (!Files.exists(path.resolve(task.getUserName())))
            {
            Files.createDirectories(path.resolve(task.getUserName()));
            }

        if (!Files.exists(path.resolve(task.getSendTo())) && task.getSendTo().length() > 0)
            {
            Files.createDirectories(path.resolve(task.getSendTo()));
            }

        Path filePath = path.resolve(task.getUserName()).resolve(task.getFileName());

        return filePath;
        }

    private void wait(ServerSaveTask task)
        {
        ServerFrame.setField(task.getUserName() + " klient zapisuje plik " + task.getFileName() +
                " jako piorytet to " +
                "piorytet " + task.getPriority());
        try
            {
            Thread.sleep(abs(new Random().nextInt()) % 1000 * 10);
            }
        catch (InterruptedException e)
            {
            e.printStackTrace();
            }
        }
    }
