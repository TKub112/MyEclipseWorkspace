package serverApp;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import clientApp.Packet;

/**
 * Threads that allows queueing the packets in putting them to taskQueue
 * @author Tobiasz Kubiak
 *
 */
public class PacketBuffer implements Runnable {
    
    private BlockingQueue<Packet> taskQueue;
    private ServerSocket serverSocket;
    private boolean state = true;
    
    /**
     * estabilishing connection with port 5000 and making a queue
     * @param taskQueue containing packets
     */
    public PacketBuffer(BlockingQueue<Packet> taskQueue)
    {
	this.taskQueue = taskQueue;
	try
	{
	    serverSocket = new ServerSocket(5000);
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
	
    }
    
    @Override
    public void run() {
	
	while(state) {
		
		try 
		{
		    Socket clientSocket = serverSocket.accept();
		    if(state)
		    {
			Thread t = new Thread(new RequestHandler(clientSocket));
			t.start();
		    }
		    
		    
		} 
		catch(Exception ex) 
		{
		    ex.printStackTrace();
		}
		
	}
	
    }
    public void shutdown()
    {
	state = false;
	try 
	{
	    new Socket(serverSocket.getInetAddress(),serverSocket.getLocalPort()).close();
	} 
	catch (IOException e)
	{
	    e.printStackTrace();
	}
    }
    
    /**
     * Putting packet in queue
     * @author Tobiasz Kubiak
     *
     */
    public class RequestHandler implements Runnable
    {
    Socket connectClient;
	Packet packet;
	ObjectInputStream packetReader;
	
	
	public RequestHandler(Socket connectClient)
	{
	    this.connectClient = connectClient;
	}
	
	@Override
	public void run() {
	    
	    try
	    {
		packetReader = new ObjectInputStream(connectClient.getInputStream());
		
		packet = (Packet) packetReader.readObject();
		packet.setSocket(connectClient);
		
		System.out.println("Request od: " + packet.getUsername());
		
		while(!taskQueue.offer(packet));
		
	    }
	    catch(Exception e)
	    {
		e.printStackTrace();
	    }
	    
	
	}
	
    }
    
}
