package serverApp;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class responsible for reading from folder
 * @author Tobiasz Kubiak
 *
 */
public class FxGetfolder1 {

    /**
     * Scanning folder for files
     * @param directory Folder location to search.
     * @return List of files in the searched folder.
     */
    public static ObservableList<String> readFolder(String directory)
    {
	
	ObservableList<String> returnList = FXCollections.observableArrayList();
	String s;
	
	try (Stream<Path> list = Files.list(Paths.get(directory))) {
    	List<String> result;
    	result = list.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
    	for(int index=0;index<result.size();index++)
    	{
    	    s = result.get(index).replace(directory + "\\" , "");
    	    if(!s.equals("book.csv"))
    	    {
    		returnList.add(s);
    	    }
    	    
    	}
         	    
        } 
	catch(NoSuchFileException e)
	{
	    System.out.println("Nie wykrywa dyskow");
	}
	catch(InvalidPathException e)
	{
	    System.out.println("Sciezka do dysku niepoprawna");
	} 
	catch (IOException e) 
	{
	    System.out.println("Blad io!");
	}

	return returnList; 
    }
    
}
