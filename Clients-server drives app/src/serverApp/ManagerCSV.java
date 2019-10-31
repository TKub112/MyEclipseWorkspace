package serverApp;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

/**
 * Class give tools to operate on csv files
 * @author Tobiasz Kubiak
 *
 */
public class ManagerCSV {
    static final String directory = "serverDiscs";
    
    
    /**
     * Creating lis of user
     * @return List of users using the server.
     */
    public static ArrayList<String> listUsers()
    {
	ArrayList<String> users = new ArrayList<String>();
	
	for(int i=1;i<6;i++)
	{
	    try
	    {
		BufferedReader br =new BufferedReader(new FileReader(directory + "/" + Integer.toString(i) + "/book.csv"));
                String line;
           	    
           	    while ((line = br.readLine()) != null) 
           	    {
           		String[] values = line.split(",");
           		if(!users.contains(values[0]))
           		{
           		    users.add(values[0]);
            		}
           	    }
           	    
           	 br.close();
        }  
	    catch (FileNotFoundException e) 
	    {
		System.out.println("Plik nie istnieje!");
	    }
	    catch (IOException e1) 
	    {
		System.out.println("B³¹d odczytu!");
		e1.printStackTrace();
        }
	}
	
	return users;
    }
    
    
    /**
     * Delete record in csv
     * @param username Owner of file
     * @param fileName Name of deleting file
     * @return Disk number, "1", "2", "3", "4" or "5". In case the file does not exist returns null.
     */
    public static String DeleteCSV(String username, String fileName){
	for(int i=1;i<6;i++)
	{	
		 try {
		 BufferedReader br;
		 br = new BufferedReader(new FileReader("serverDiscs/" + Integer.toString(i) + "/book.csv"));
		 String line;
		 while ((line = br.readLine()) != null)
		 {
			 
			 String[] values = line.split(",");
			
			 if(values[0].equals(username) && values[1].equals(fileName))
			    {
				 
			    	//removing a specified line from file
			    	File inputFile = new File("serverDiscs/" + Integer.toString(i) + "/book.csv");
					File tempFile = new File("serverDiscs/" + Integer.toString(i) + "/test.csv");
					tempFile.createNewFile();
					BufferedReader reader = new BufferedReader(new FileReader(inputFile));
					BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

					String lineToRemove = username+","+fileName;
					String currentLine;

					while((currentLine = reader.readLine()) != null) {
					    // trim newline when comparing with lineToRemove
					    String trimmedLine = currentLine.trim();
					    if(trimmedLine.equals(lineToRemove)) continue;
					    writer.write(currentLine + System.getProperty("line.separator"));
					}

					writer.close(); 
					reader.close(); 
					tempFile.renameTo(inputFile);
					
			    }
			 
			 
		 }
		 br.close();
		 File inputFile = new File("serverDiscs/" + Integer.toString(i) + "/book.csv");
		 inputFile.delete();
		 File tempFile = new File("serverDiscs/" + Integer.toString(i) + "/test.csv");
		 tempFile.renameTo(inputFile);
		 }
		 catch(IOException e)
		 {
			 
		 }
		 
	}
	
	return "ok";
    }
    
    /**
     * checking for file disc
     * @param username Owner of file/
     * @param fileName Name of searching file
     * @return the number of disc where file is localisted
     */
    public static String getFileDisc(String username, String fileName)
    {
	for(int i=1;i<6;i++)
	{
	    try
	    {
		BufferedReader br = new BufferedReader(new FileReader(directory + "/" + Integer.toString(i) + "/book.csv"));
		String line;
           	    
		while ((line = br.readLine()) != null) 
		{
		    String[] values = line.split(",");
		    if(values[0].equals(username) && values[1].equals(fileName))
		    {
			return Integer.toString(i);
		    }
		}
		
		br.close();
            } 
	    catch (FileNotFoundException e) 
	    {
		System.out.println("Plik nie istnieje!");
	    }
	    catch (IOException e1) 
	    {
		System.out.println("B³¹d odczytu!");
		e1.printStackTrace();
            }
	}
	
	return null;
    }
    
    

    
    /**
     * Adds to the csv file an entry containing the name of the new file and its owner.
     * @param userName File owner name.
     * @param fileName File name.
     * @param disc Disk where the file is to be saved.
     */
    public static void addRecord(String userName, String fileName, String disc)
    {
	try 
	{
	    BufferedWriter bw = new BufferedWriter(new FileWriter("serverDiscs/" + disc + "/book.csv",true));
	    bw.write(userName + "," + fileName);
	    bw.newLine();
	    bw.close();
	} 
	catch (IOException e) 
	{
	    e.printStackTrace();
	}
	
    }
    /**
     * The method reads csv on sever
     * @param username The user whose files we want to find on the server.
     * @return List of files for specified user
     */
    public static ArrayList<String> getNameOfUserFiles(String username)
    {
	ArrayList<String> csvList = new ArrayList<String>();
	
	for(int i=1;i<6;i++)
	{
	    try
	    {
			BufferedReader br = new BufferedReader(new FileReader(directory + "/" + Integer.toString(i) + "/book.csv"));
		    String line;
		    while ((line = br.readLine()) != null) 
		    {
			String[] values = line.split(",");
			if(values[0].equals(username))
			{
			    csvList.add(values[1]);
				}
		    }   	    
		   	br.close();
	    } 
	    catch (FileNotFoundException e) 
	    {
		System.out.println("Plik nie istnieje!");
	    }
	    catch (IOException e1) 
	    {
		System.out.println("B³¹d odczytu!");
		e1.printStackTrace();
        }
	}
	return csvList;
    }
    
   
    
}
