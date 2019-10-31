package clientApp;


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
 * Class for reading the folder for java fx observable list
 * @author Tobiasz Kubiak
 *
 */
public class FxGetFolder {

    /**
     * Get a list of  file names in folder
     * @param dir Folder location to search.
     * @return List of files in the searched folder.
     */
    public static ObservableList<String> getListFilesFolder(String dir)
    {
	
	ObservableList<String> returnList = FXCollections.observableArrayList();
	String s;
	
	try (Stream<Path> list = Files.list(Paths.get(dir))) 
        {
        	List<String> end;
        	end = list.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
        
        	for(int i=0;i<end.size();i++)
        	{
        	    s = end.get(i).replace(dir + "\\" , "");
        	    returnList.add(s);
        	}
         	    
        } 
	catch(NoSuchFileException e)
	{
	    System.out.println("Nie mo¿na odnalzæ dysków!");
	    return null;
	}
	catch(InvalidPathException e)
	{
	    System.out.println("Nie mozna uzyskac dostepu do dyskow, sprawdz czy sciezka jest poprawna!");
	    return null;
	} 
	catch (IOException e) 
	{
	    System.out.println("Wystapil problem z dostêpem do plików!");
	    return null;
	}

	return returnList; 
    }
    
}
