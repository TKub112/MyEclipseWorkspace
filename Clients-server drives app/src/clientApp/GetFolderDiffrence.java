package clientApp;
import javafx.collections.ObservableList;
import java.util.TimerTask;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.application.Platform;

import javafx.scene.control.Button;


	/**
	 * Class that have refreshed thread by 3 second. This class noticing a new files in client folder that arent at serwer.
	 * @author Tobiasz Kubiak
	 *
	 */
public class GetFolderDiffrence extends TimerTask{

    Button refreshButton;
    String nameOfUser;
    String dir;
    Label stan;
    ListView<String> listOfFiles;
    ListView<String> listOfUsers;
    ObservableList<String> files;
    /**
     * A constructor that assigns input arguments to local variables.
     * @param dir localistion of user folder.
     * @param username name of user.
     * @param state about what client current doing.
     * @param listOfFiles ListView with user files.
     * @param listOfUsers	list of server users.
     * @param files file list of user..
     * @param refreshButton button that refresh file list.
     */
    public GetFolderDiffrence(ListView<String> listOfFiles,ListView<String> listOfUsers, ObservableList<String> files,
	    String dir, String nameOfUser, Button refreshButton, Label stan)
    {
	this.listOfFiles = listOfFiles;
	this.listOfUsers = listOfUsers;
	this.files = files;
	this.refreshButton = refreshButton;
	this.stan = stan;
	this.nameOfUser = nameOfUser;
	this.dir = dir;
    }
    
    @Override
    public void run() {
	
	ObservableList<String> returnList = FxGetFolder.getListFilesFolder(dir);
	
	Platform.runLater(new Runnable() {
	    @Override
	    public void run()
		    {
			try
			{
			    if(!files.containsAll(returnList)){
				files = returnList;
				listOfFiles.setItems(files);
				Thread t = new Thread(new UpdateClass(nameOfUser,dir,files,
					listOfUsers,refreshButton,stan,3));
                			
				t.start();
			    }
			    else{
				files = returnList;
				listOfFiles.setItems(files);
			    }
			}
			catch(NullPointerException e)
			{
			    stan.setText("No access to file!GetDiffrenceclass");
			}

		    }
        }); 
	
    }

}
