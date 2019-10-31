package clientApp;
import java.io.Serializable;
import java.net.Socket;



/**
 * Operation container for managing operations client-server
 * @author Tobiasz Kubiak
 *
 */
public class Packet implements Serializable{
    
    private String neededFile;
    private String directory;
    private Socket clientSocket;
    private String username;
    private String shareUser;
    private String action;
    
    private static final long serialVersionUID = 1L;
    public Packet(){}
    
     /**
     * A constructor used when requesting to send or receive a file.
     * @param username Nickname by which we are identified by the server.
     * @param operation For requesting sending Action = "download", for requesting "upload".
     * @param neededFile The name of the file to be sent or received.
     */
    public Packet(String username, String operation, String neededFile)
    {
	this.username = username;
	this.action = operation;
	this.neededFile = neededFile;
    }
    
    /**
     * The constructor used when creating the object to request a list of files and users.
     * @param username The nickname by which we are identified by the server.s
     * @param action For the Action = "listoffile" file list, for the "users" user list.
     */
    public Packet(String username, String action)
    {
	this.username = username;
	this.action = action;
    }
    
   
    /**
     * The constructor that allow to delete file.
     * @param username Nickname by which we are identified by the server.
     * @param operation For requesting sending Action = "download", for requesting "upload".
     * @param neededFile The name of the file to be sent or received.
     * @param directory the path of directory
     * 
     */
    public Packet(String username, String operation, String neededFile,String directory,String fakestring)
    {
	this.username = username;
	this.action = operation;
	this.neededFile = neededFile;
	this.directory = directory;
    }
    
    /**
     * The constructor used when requesting sharing a file with another user.
     * @param username Nickname by which we are identified by the server.
     * @param operation For requesting sending Action = "download", for requesting "upload".
     * @param neededFile The name of the file to be sent or received.
     * @param shareUser Username of the user who the file will be shared with.
     */
    public Packet(String username, String operation, String neededFile, String shareUser)
    {
	this.username = username;
	this.action = operation;
	this.neededFile = neededFile;
	this.shareUser = shareUser;
    }

    public String getOperation()
    {
	return this.action;
    }
    public void setSocket(Socket clientSocket)
    {
	this.clientSocket = clientSocket;
    }
    
    public String getUsername()
    {
	return this.username;
    }

    public String getShareNameOfShare()
    {
	return this.shareUser;
    }
    
    public String getNeedFile()
    {
	return this.neededFile;
    }
        public Socket getSocket()
    {
	return this.clientSocket;
    }
    public String getDirectory()
    {
	return this.directory;
    }
    

}
