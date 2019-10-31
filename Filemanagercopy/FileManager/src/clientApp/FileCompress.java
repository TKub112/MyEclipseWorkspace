package clientApp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * A class allows to compress a file
 * @author Tobiasz Kubiak
 * @version 1.0
 */


public class FileCompress implements Runnable {
	 String filename;
	 String directory;
	
	public FileCompress(String filename,String directory)
	{
		this.directory=directory;
		this.filename = filename;
	}
	
	
	    @Override
	    public void run()
		    {
	    	try {
				File file = new File(directory+"/"+filename);
				if(file.exists())
					System.out.println("exists");
			    String zipFileName = file.getName().concat(" compressed.zip");
			
			    FileOutputStream fos = new FileOutputStream(directory+"/"+zipFileName);
			    ZipOutputStream zos = new ZipOutputStream(fos);
			
			    zos.putNextEntry(new ZipEntry(file.getName()));
			
			    byte[] bytes = Files.readAllBytes(Paths.get(directory+"/"+filename));
			    zos.write(bytes, 0, bytes.length);
			    zos.closeEntry();
			    zos.close();
			}
			catch(IOException e)
			{
				System.out.println("Blad podczas kompresji");
			}
	
		    }
}
