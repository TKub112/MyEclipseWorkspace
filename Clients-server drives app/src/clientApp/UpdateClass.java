package clientApp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * That class helps with synchronization in files between server and client
 * it have many types of operations sync,share etc.
 * 
 * 
 * @author Tobiasz Kubiak
 *
 */
public class UpdateClass implements Runnable {

    
    private ObjectInputStream readObject;
    private ObjectOutputStream writeObject;
    private ArrayList<String> clientFiles;
    private ArrayList<String> serverFiles;
    private ListView<String> usersList;
    private ArrayList<String> clientFilesToDownload;
    private ArrayList<String> serverFilesToDownload;
    private Button przycisk1;
    private Label stan;
    private String username;
    private String filename;
    private String friendname;
    private String directory;
    private int typeofoperation ;
    
    /**
     * A constructor used to create a thread to share a file with another user.
     * @param username Our nickname by which we are identified by the server.
     * @param friendname Nickname of the person to whom we share the file.
     * @param filename The name of the file being shared.
     * @param directory Path to the folder with our files.
     * @param stan Label, a text field. Enter the activity currently carried out by the thread.
     */
    public UpdateClass(String username, String friendname, String filename, String directory, Label stan,int typeofoperation)
    {
	this.username = username;
	this.friendname = friendname;
	this.directory = directory;
	this.filename = filename;
	this.stan = stan;
	this.typeofoperation = typeofoperation;
    }
    
    
    
    /**
     * A constructor used to run a thread to synchronize files between
     * client and server.
     * @param username Our nickname by which we are identified by the server.
     * @param directory Path to the folder with our files.
     * @param files ObservableList containing a list of files in our folder.
     * @param usersList ListView into which the list of current server users will be entered.
     * @param button1 A reference to the button that will be locked while the thread is in operation.
     * @param stan Label, a text field. Enter the activity currently carried out by the thread.
     */
    public UpdateClass(String username, String directory, ObservableList<String> files,
	    ListView<String> usersList, Button button1, Label stan,int typeofoperation)
    {
	this.username = username;
	this.directory = directory;
	this.clientFiles = new ArrayList<String>(files);
	this.usersList = usersList;
	this.przycisk1 = button1;
	this.stan = stan;
	this.typeofoperation = typeofoperation;
    }

    @Override
    public void run()
    {
	try 
	{   
		if(typeofoperation==1){
			deleteFile(filename);
			return;
	    }
	    if(typeofoperation==2){
			shareFileBetweenClient(filename);
			return;
	    }
	    if(typeofoperation==4){
			resetServer();
			return;
	    }
	    
	    
	    przycisk1.setDisable(true);
	    serverFiles = getListOfFiles();
	    clientFilesToDownload = arrayDifference(serverFiles, clientFiles);
	    serverFilesToDownload = arrayDifference(clientFiles, serverFiles);
	    downloadAllFiles(clientFilesToDownload);
	    uploadAllFiles(serverFilesToDownload);
	    getListOfUsers();
	    clientPrintMsg("Klient gotowy");
	    
	    
	} 
	catch (CommunicationErrorException e) 
	{
	    clientPrintMsg("Blad komunikacji z serwerem!");
	    return;
	}
	catch(UnexpectedException e)
	{
	    clientPrintMsg("Aplikacja nie moze dzialac z nieznanych przyczyn!");
	    return;
	}
	finally
	{
	    przycisk1.setDisable(false);
	}
	
    }
    
    
    
    /**
    * The method of connecting to the server and sending a Request
      * with a request to send a list of files from the server belonging to the user.
      * Closes connection after receiving data.
      * Inside the method there are retarders that intentionally extend its duration. Their only one
      * the application is a better illustration of the method.
      *
      * @return Returns an ArrayList containing a list of user files on the server.
      * @throws CommunicationErrorException An exception thrown when it cannot
      * connect to server or something interrupted.
      * @throws UnexpectedException An exception thrown in the event of unforeseen situations
      * such as the inability to open a specific file.
     */
    @SuppressWarnings("unchecked")
    private ArrayList<String> getListOfFiles() throws CommunicationErrorException, UnexpectedException
    {
	try 
	{
	    clientPrintMsg("Czekam na serwer...");
	    appFreezer(2000);

	    Socket socket = getConnection();	
	    
	    //wyslanie pakietu i czekanie na odpowiedz
	    writeObject = new ObjectOutputStream(socket.getOutputStream());
	    Packet r = new Packet(username,"listoffile");
	    writeObject.writeObject(r);
	    readObject = new ObjectInputStream(socket.getInputStream());
	    ArrayList<String> n = (ArrayList<String>) readObject.readObject();
	    writeObject.close();
	    readObject.close();
	    socket.close();  
	    return n;
	} 
	catch (IOException e) 
	{
	    e.printStackTrace();
	    throw new CommunicationErrorException();
	} 
	catch (ClassNotFoundException e) 
	{
	    clientPrintMsg("Nieobslugiwany sposob przesylu danych!");
	    throw new UnexpectedException();
	}
	
    }
    
    /**
     * The method that getting the list of files
      *
      * @throws CommunicationErrorException An exception thrown when it cannot
      * connect to server or something interrupted.
      * @throws UnexpectedException An exception thrown in the event of unforeseen situations
      * such as the inability to open a specific file.
     */
    @SuppressWarnings("unchecked")
    private void getListOfUsers() throws CommunicationErrorException, UnexpectedException
    {
	try 
	{
	    clientPrintMsg("Uyztkownicy pobierani...");
	    
	    appFreezer(3000);
	    
	    Socket socket = getConnection();	
	    
	    writeObject = new ObjectOutputStream(socket.getOutputStream());
	    Packet r = new Packet(username,"users");
	    writeObject.writeObject(r);
	    

	    
	    readObject = new ObjectInputStream(socket.getInputStream());
	    ArrayList<String> n = (ArrayList<String>) readObject.readObject();

	    ObservableList<String> u = FXCollections.observableArrayList(n);
	   
	    
	    Platform.runLater(new Runnable()
	    	{
		    @Override
		    public void run() {
			usersList.setItems(u);
		    }
		    
		});
	    
	    
	    writeObject.close();
	    readObject.close();
	    socket.close();  
	} 
	catch (IOException e) 
	{
	    clientPrintMsg("Blad IO!");
	    appFreezer(3000);
	    throw new UnexpectedException();
	} 
	catch (ClassNotFoundException e) 
	{
	    clientPrintMsg("Nieobslugiwany sposob przesylu danych!");
	    appFreezer(3000);
	    throw new UnexpectedException();
	} 
    }
    

    /**
     * 
    * Its task is to download a file with the given name and save it to the user's folder.
    * @param fileName The name of the file you want to download from the server.
    * @throws CommunicationErrorException An exception thrown when it cannot
    * connect to server or something interrupted.
    * @throws UnexpectedException An exception thrown in the event of unforeseen situations
    * such as the inability to open a specific file.
     */
    private void sendFileToClient(String fileName) throws CommunicationErrorException, UnexpectedException
    {
		
	/*
	 * Wysy³amy request i czekamy na przyslanie pliku.
	 */
	    
	try 
	{
	    clientPrintMsg("Pobieram plik: " + fileName);
	    appFreezer(3000);
	    
	    Socket socket = getConnection();
	    
	    writeObject = new ObjectOutputStream(socket.getOutputStream());
	    Packet r = new Packet(username, "download", fileName);
	    writeObject.writeObject(r);
	    
	    
	    DataInputStream dis = new DataInputStream(socket.getInputStream());
	    FileOutputStream fos = new FileOutputStream(directory + "/" + fileName);
	    
	    int count;
	    byte[] bytes = new byte[8 * 1024];
	    
	    while ((count = dis.read(bytes)) > 0)
	    {
	      fos.write(bytes, 0, count);
	    }
	    
	    fos.close();
	    dis.close();
	    socket.close();
	    
	} 
	catch (IOException e) 
	{
	    clientPrintMsg("Blad zapisu!");
	    appFreezer(3000); 
	    throw new UnexpectedException();
	}
	
    }
    
    /**
     * 
     * Its task that remove all files from server 
     * @throws IOException  when io get interupted
     */
    
    
    private void resetServer() throws CommunicationErrorException, UnexpectedException
    {
		
	/*
	 * Wysy³amy request i czekamy na przyslanie pliku.
	 */
	    
	try 
	{
	    clientPrintMsg("Resetuje serwer ");
	    //Celowe spowolnienie dzialania
	    appFreezer(3000);
	    
	    Socket socket = getConnection();
	    
	    writeObject = new ObjectOutputStream(socket.getOutputStream());
	    Packet r = new Packet(username, "reset","null",directory,"null");
	    writeObject.writeObject(r);
	    socket.close();
	    
	    
	} 
	catch (IOException e) 
	{
	    clientPrintMsg("Blad zapisu odbieranego pliku!");
	    appFreezer(3000); 
	    throw new UnexpectedException();
	}
	
    }
    
    /**
     * 
     * Sending a file to server
     *
     * @param fileName The name of the file you want to send to the server.
     * @throws CommunicationErrorException An exception thrown when it cannot
     * connect to server or something interrupted.
     * @throws UnexpectedException An exception thrown in the event of unforeseen situations
     * such as the inability to open a specific file.
     */
    private void sendFileToServer(String fileName) throws CommunicationErrorException, UnexpectedException
    {
	
	try
	{
	    clientPrintMsg("Wysylam plik: " + fileName);
	    appFreezer(3000);
	    Socket socket = getConnection();
	    
	    
	    writeObject = new ObjectOutputStream(socket.getOutputStream());
	    Packet r = new Packet(username, "upload", fileName);
	    writeObject.writeObject(r);
	   
	    
	    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
	    FileInputStream fis = new FileInputStream(directory + "/" + fileName);
	    
	    byte[] bytes = new byte[8 * 1024];
		
	    int count;
	    while ((count = fis.read(bytes)) > 0)
	    {
		dos.write(bytes, 0, count);
	    }
		
	    writeObject.close();
	    dos.close();
	    fis.close();
	    socket.close();

	}
	catch(IOException e)
	{
	    clientPrintMsg("Blad odczytu wysylanego pliku!");
	    appFreezer(3000);
	    throw new UnexpectedException();
	}
    }
    
    /**
     * The purpose of this method is to make our file available to another server user.
      * It sends a Request for Sharing and then closes the connection.
      * Does not wait for confirmation of receipt of the Request.
      *
      * @param fileName The name of the shared file.
      * @throws CommunicationErrorException An exception thrown when it cannot
      * connect to server or something interrupted.
      * @throws UnexpectedException An exception thrown in the event of unforeseen situations
      * such as the inability to open a specific file.
     */
    private void shareFileBetweenClient(String fileName) throws CommunicationErrorException, UnexpectedException
    {
	try 
	{
	    clientPrintMsg("Udostepniam plik : " + fileName);
	    
	    appFreezer(3000);
	    
	    Socket socket = getConnection();
	    
	    /*
	     * Wysylamy pakiet o udostepnienie.
	     */
	    
	    writeObject = new ObjectOutputStream(socket.getOutputStream());
	    Packet r = new Packet(username, "share", fileName, friendname);
	    writeObject.writeObject(r);
	    
	    writeObject.close();
	    clientPrintMsg("Gotowosc do dzialania" );
	    socket.close();
	    
	} 
	catch(IOException e)
	{
	    clientPrintMsg("Blad IO!");
	    appFreezer(3000);
	    throw new UnexpectedException();
	}
	
    }
    
    /**
      * Deleting a file
      *
      * @param fileName The name of the shared file.
      * @throws IOException io problem
     */
    
    private void deleteFile(String fileName) throws CommunicationErrorException, UnexpectedException
    {
	try 
	{
	    clientPrintMsg("Usuwam plik : " + fileName);
	    
	    appFreezer(3000);
	    
	    Socket socket = getConnection();
	    
	    
	    writeObject = new ObjectOutputStream(socket.getOutputStream());
	    Packet r = new Packet(username, "delete", fileName,directory,"null");
	    writeObject.writeObject(r);
	    
	    writeObject.close();
	    clientPrintMsg("Gotowosc do dzialania" );
	    socket.close();
	    
	} 
	catch(IOException e)
	{
	    clientPrintMsg("Blad IO!");
	    appFreezer(3000);
	    throw new UnexpectedException();
	}
	
    }
    
    /**
    * The method works similarly to downloadFile (), however it accepts the list of files to download,
      * not the name of one file.
      *
      * @param files List of files to download
      * @throws CommunicationErrorException An exception thrown when it cannot
      * connect to server or something interrupted.
      * @throws UnexpectedException An exception thrown in the event of unforeseen situations
      * such as the inability to open a specific file.
     */
    private void downloadAllFiles(ArrayList<String> files) throws CommunicationErrorException, UnexpectedException
    {
	
	for(String s:files)
	{
	    sendFileToClient(s);
	}
	
    }
    /**
      * Method works to upload all files
      *
      * @param files List of files to send
     */
    private void uploadAllFiles(ArrayList<String> files) throws CommunicationErrorException, UnexpectedException
    {
	
	for(String s:files)
	{
	    sendFileToServer(s);
	}
	
    }
    
    /*
     * arrayDifference ()
      *getting a diffrence between a 2 arrays
      * /
     /**
      * The method used to create the ArrayList names of files that are in one list
      * @param tab1 The first list, we subtract the contents of the second from its contents.
      * @param tab2 Second list, its content is subtracted from the first.
      * @return Elements that are in the first list but not in the second.
     */
    private static ArrayList<String> arrayDifference(ArrayList<String> tab1, ArrayList<String> tab2)
    {
	ArrayList<String> n = new ArrayList<String>();
	for(String s:tab1)
	{
	    if(!tab2.contains(s))
	    {
		n.add(s);
	    }
	}
	
	return n;
    }
    
    /**
    *
      * The method used to connect to the server.
      *
      * @return Socket with connection established.
      * @throws CommunicationErrorException An exception thrown when it cannot
      * connect to server or something interrupted.
      * @throws UnexpectedException An exception thrown in the event of unforeseen situations
      * such as the inability to open a specific file.
     */
    private Socket getConnection() throws CommunicationErrorException, UnexpectedException
    {
	try {
	    Socket socket = new Socket("127.0.0.1", 5000);
	    return socket;
	} 
	catch (ConnectException e){
	    throw new CommunicationErrorException();
	} 
	catch (UnknownHostException e){
	    throw new CommunicationErrorException();
	} 
	catch (IOException e) {
	    clientPrintMsg("Blad io!");
	    appFreezer(3000);
	    throw new UnexpectedException();
	} 
    }
    
    /**
     * Short version of Thread.sleep ();
     * @param The number of milliseconds to sleep the thread.
     */
    private void appFreezer(int n)
    {
	try 
	{
	    Thread.sleep(n);
	} 
	catch (InterruptedException e) 
	{
	    //NOT GONNA HAPPEN
	    e.printStackTrace();
	}
	
    }
    
    /**
     * The method is used to display the currently performed activity.
     * @param String to be placed in the Label object.
     */
    private void clientPrintMsg(String n)
    {
	Platform.runLater(new Runnable()
	{
	    @Override
	    public void run() {
		stan.setText(n);
	    }
	    
	});
    }
    
   
    
    private class CommunicationErrorException extends Exception
    {
	private static final long serialVersionUID = 1L;

	public CommunicationErrorException()
	{
	    super("Nie mozna nalaczyc polaczenia ze serwerem!");
	}
	
    }
    
 
    private class UnexpectedException extends Exception
    {
	private static final long serialVersionUID = 1L;

	public UnexpectedException()
	{
	    super("Undefined error!");
	}
	
    }

}//class
