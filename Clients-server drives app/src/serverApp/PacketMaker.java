package serverApp;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import clientApp.Packet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import java.io.IOException;
import javafx.scene.control.Label;

import java.nio.file.Path;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import javafx.application.Platform;
import java.io.File;
import java.io.DataOutputStream;
/**
 * Packet requests are handled by PacketMaker, it gives respond to Client for every query
 * @author Tobiasz Kubiak
 *
 */
public class PacketMaker implements Runnable {
	
	private ObjectOutputStream objectw;
    private Packet packet;
    private BlockingQueue<Packet> operationBuffer;
    private Label stan;
    private boolean stateOfServer = true;
    PacketMaker(BlockingQueue<Packet> operationBuffer, Label stan)
    {
	this.operationBuffer = operationBuffer;
	this.stan = stan;
    }
    
   
    
    private void resetAnswer() throws IOException, InterruptedException
    {

	serverMsg("Resetuje serwer do ustawien domyslnych");
	Thread.sleep(3000);
	
	for(int i=1;i<6;i++) {
		try {
			try(Stream<Path> list = Files.list(Paths.get("serverDiscs/"+Integer.toString(i)+"/"))) {
	        	List<String> result;
	        	result = list.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
	        	//System.out.println(result);
	        	for (String filename : result) {
	        		if(!filename.equals("serverDiscs\\"+ Integer.toString(i) + "\\book.csv"))
	        		{
	        			File file = new File(filename);
	        			file.delete();
	        			File file2 = new File(packet.getDirectory()+"/"+filename);
	        			file2.delete();
	        			
	        		}
	        		File file1 = new File("serverDiscs/"+Integer.toString(i)+"/book.csv");
	        		file1.delete();
        			file1.createNewFile();
	        	    
	        	}

	        } 
	 }
		catch(IOException e)
		 {
			 System.out.print("Wystail problem podczas resetu serwera");
		 }
	 }
	
	
	
	packet.getSocket().close();
	Platform.exit();
    }
    
    @Override
    public void run() {

	while(stateOfServer)
	{
		try 
		{
		    serverMsg("Gotowy");
		    packet = operationBuffer.take();
		    if(!stateOfServer){
			break;
		    }
		    if(packet.getOperation().equals("users")){
			userListAnswer();
		    }
		    else if(packet.getOperation().equals("listoffile")){
			synchListOfFilesAnswer();
		    }
		    else if(packet.getOperation().equals("download")){
			downloadAnswer();
		    }
		    else if(packet.getOperation().equals("upload")){
			uploadAnswer();
		    }
		    else if(packet.getOperation().equals("share")){
			shareAnswer();
		    }
		    else if(packet.getOperation().equals("delete")){
			deleteRespond();
		    }
		    else if(packet.getOperation().equals("reset")){
			resetAnswer();
		    }
		    
		    
		} 
		catch (IOException e){
		    e.printStackTrace();
		}
		catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		catch (Exception e){
		    e.printStackTrace();
		}
		
	}
	
    }
    
    
    private void deleteRespond() throws IOException, InterruptedException
    {

	serverMsg("Usuwanie plliku " + packet.getNeedFile());
	Thread.sleep(3000);
	for(int i=1;i<6;i++)
	{
		File file = new File("serverDiscs/"+Integer.toString(i)+"/"+packet.getNeedFile());
		file.delete();
		
	}
	
	
	File file = new File(packet.getDirectory()+"/"+packet.getNeedFile());
	file.delete();

	ManagerCSV.DeleteCSV(packet.getUsername(), packet.getNeedFile());

	packet.getSocket().close();
    }
    

    private void synchListOfFilesAnswer() throws IOException, InterruptedException
    {
	
	serverMsg("Wysy³anie listy plikow do " + packet.getUsername());
	Thread.sleep(3000);
	
	objectw = new ObjectOutputStream(packet.getSocket().getOutputStream());
	objectw.writeObject( ManagerCSV.getNameOfUserFiles(packet.getUsername() ));
	objectw.close();
	packet.getSocket().close();
    }

    private void userListAnswer() throws IOException, InterruptedException
    {
	
	serverMsg("Wyslanie listy uzytkownikow");
	Thread.sleep(3000);
	
	objectw = new ObjectOutputStream(packet.getSocket().getOutputStream());
	objectw.writeObject( ManagerCSV.listUsers());
	
	objectw.close();
	packet.getSocket().close();
    }
    
    private void downloadAnswer() throws IOException, InterruptedException
    {
		serverMsg("Wysy³anie pliku " + packet.getNeedFile() );
		Thread.sleep(4000);
		
		String loc = "serverDiscs/" + ManagerCSV.getFileDisc(packet.getUsername(), packet.getNeedFile()) + "/" + packet.getNeedFile();
		DataOutputStream dos = new DataOutputStream(packet.getSocket().getOutputStream());
		FileInputStream fis = new FileInputStream(loc);
		
		byte[] bytes = new byte[8 * 1024];
		
		int count;
	    while ((count = fis.read(bytes)) > 0) {
	        dos.write(bytes, 0, count);
	    }
	
	    dos.close();
	    fis.close();
	    packet.getSocket().close();
    }
    
    private void uploadAnswer() throws IOException, InterruptedException
    {
	
	serverMsg("Odbieranie " + packet.getNeedFile()+" uzytownika "+packet.getUsername());
	Thread.sleep(3500);
	
	String loc1 = "serverDiscs/" + "1" + "/" + packet.getNeedFile();
	String loc2 = "serverDiscs/" + "2" + "/" + packet.getNeedFile();
	String loc3 = "serverDiscs/" + "3" + "/" + packet.getNeedFile();
	String loc4 = "serverDiscs/" + "4" + "/" + packet.getNeedFile();
	String loc5 = "serverDiscs/" + "5" + "/" + packet.getNeedFile();
	DataInputStream dis = new DataInputStream(packet.getSocket().getInputStream());
	FileOutputStream fos1 = new FileOutputStream(loc1);
	FileOutputStream fos2 = new FileOutputStream(loc2);
	FileOutputStream fos3 = new FileOutputStream(loc3);
	FileOutputStream fos4 = new FileOutputStream(loc4);
	FileOutputStream fos5 = new FileOutputStream(loc5);
	int count;
	byte[] bytes = new byte[8 * 1024];
	    
	while ((count = dis.read(bytes)) > 0)
	{
		
	    fos1.write(bytes, 0, count);
	    fos2.write(bytes, 0, count);
	    fos3.write(bytes, 0, count);
	    fos4.write(bytes, 0, count);
	    fos5.write(bytes, 0, count);
	}
	
	ManagerCSV.addRecord(packet.getUsername(),packet.getNeedFile(),"1");
	ManagerCSV.addRecord(packet.getUsername(),packet.getNeedFile(),"2");
	ManagerCSV.addRecord(packet.getUsername(),packet.getNeedFile(),"3");
	ManagerCSV.addRecord(packet.getUsername(),packet.getNeedFile(),"4");
	ManagerCSV.addRecord(packet.getUsername(),packet.getNeedFile(),"5");
	
	fos1.close();
	fos2.close();
	fos3.close();
	fos4.close();
	fos5.close();
	dis.close();
	packet.getSocket().close();
    }
    /**
     * The method to print what is doing in application
     * @param n string to print in window
     */
    private void serverMsg(String n)
    {
	Platform.runLater(new Runnable()
	{
	    @Override
	    public void run() {
		stan.setText(n);
	    }
	    
	});
    }
    private void shareAnswer() throws IOException, InterruptedException
    {
	serverMsg("Udostepniam plik " + packet.getNeedFile() + " dla " + packet.getShareNameOfShare());
	Thread.sleep(3000);
	if( !ManagerCSV.getNameOfUserFiles(packet.getShareNameOfShare()).contains(packet.getNeedFile()) )
	{
	    ManagerCSV.addRecord(packet.getShareNameOfShare(), packet.getNeedFile(),
		    ManagerCSV.getFileDisc(packet.getUsername(), packet.getNeedFile()));
	}
	packet.getSocket().close();
    }
    
    public void exitServer()
    {
	stateOfServer = false;
	try
	{
	    operationBuffer.add(new Packet());
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
    }
    
}
