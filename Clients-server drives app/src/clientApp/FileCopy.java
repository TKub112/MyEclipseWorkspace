package clientApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * A class allows to copy a file
 * @author Tobiasz Kubiak
 * @version 1.0
 */
public class FileCopy implements Runnable {
	private File selectedFile;
	private String directory;
	
	public FileCopy(File selectedFile,String directory)
	{
		this.selectedFile=selectedFile;
		this.directory = directory;
	}
	
	
    @Override
    public void run()
    {
	 	File sourceFile = new File(selectedFile.getAbsolutePath());
	    File destinationFile = new File(directory+"/"+selectedFile.getName());
	    try {
	    FileInputStream fileInputStream = new FileInputStream(sourceFile);
	    FileOutputStream fileOutputStream = new FileOutputStream(
	                    destinationFile);

	    int bufferSize;
	    byte[] bufffer = new byte[512];
	    while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
	        fileOutputStream.write(bufffer, 0, bufferSize);
	    }
	    fileInputStream.close();
	    fileOutputStream.close();
	    }
	    catch(FileNotFoundException c)
	    {
	    	System.out.println("FileNotfound");
	    }
	    catch(IOException s)
	    {
	    	System.out.println("IO problem");
	    }
    }
    
}



