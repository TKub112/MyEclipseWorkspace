package clientApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipOutputStream;

import serverApp.ManagerCSV;

import java.util.zip.*;
public class TestCode {

	public static void main(String[] args)throws IOException {
		
		//DeleteCSV("Adam","use2");
		//ServerReset();
		compress("user1/3.txt","user1");
		
			
	}
	public static void compress(String filePath,String directory)
	{
		try {
			File file = new File(filePath);
			if(file.exists())
				System.out.println("exists");
		    String zipFileName = file.getName().concat(" compressed.zip");
		
		    FileOutputStream fos = new FileOutputStream(directory+"/"+zipFileName);
		    ZipOutputStream zos = new ZipOutputStream(fos);
		
		    zos.putNextEntry(new ZipEntry(file.getName()));
		
		    byte[] bytes = Files.readAllBytes(Paths.get(filePath));
		    zos.write(bytes, 0, bytes.length);
		    zos.closeEntry();
		    zos.close();
		}
		catch(IOException e)
		{
			System.out.println("Blad podczas kompresji");
		}

		
	}
	
	
	public static void ServerReset()
	{
		 for(int i=1;i<6;i++) {
			try {
				try (Stream<Path> list = Files.list(Paths.get("files/"+Integer.toString(i)+"/"))) 
		        {
		        	List<String> result;
		        	result = list.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
		        	//System.out.println(result);
		        	for (String filename : result) {
		        		if(!filename.equals("files\\"+ Integer.toString(i) + "\\book.csv"))
		        		{
		        			File file = new File(filename);
		        			
		        			file.delete();
		        		}
		        	    
		        	}
  
		        } 
		 }
			catch(IOException e)
			 {
				 
			 }
		 }
	
		 
		 
		 
	}
			
			
			
			
			//ManagerCSV.DeleteCSV(r.getUsername(), r.getNeededFile());
	//}
	
	
	 public static String DeleteCSV(String username, String fileName) throws FileNotFoundException,IOException
	    {
		 
		 
		 BufferedReader br;
		 br = new BufferedReader(new FileReader("files/1/book.csv"));
		 String line;
		 while ((line = br.readLine()) != null)
		 {
			 String[] values = line.split(",");
			
			 if(values[0].equals(username) && values[1].equals(fileName))
			    {
				 
			    	//removing a specified line from file
			    	File inputFile = new File("files/" + "1" + "/book.csv");
					File tempFile = new File("files/" + "1" + "/test.csv");
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
		 File inputFile = new File("files/" + "1" + "/book.csv");
		 inputFile.delete();
		 File tempFile = new File("files/" + "1" + "/test.csv");
		 tempFile.renameTo(inputFile);
			
		/*
		BufferedReader br;
		br = new BufferedReader(new FileReader("files/1/book.csv"));
		
	    
		
		for(int i=1;i<6;i++)
		{
		    try
		    {
			
			String line;
	           	    
			while ((line = br.readLine()) != null) //blad tylko gdzie ?!
			{
				
				
				
				
			    String[] values = line.split(",");
			    if(values[0].equals(username) && values[1].equals(fileName))
			    {
			    	//removing a specified line from file
			    	File inputFile = new File("files/" + "1" + "/book.csv");
					File tempFile = new File("files/" + "1" + "/test.txt");

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
		*/
		
		
		
		return null;
	    }

	    
}
