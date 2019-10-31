import java.io.File;
import java.io.IOException;

public class testing {

	public static void main(String[] args)throws IOException{
		File file = new File("src/file.txt");
		file.delete();/*
		if(file.createNewFile()){
            System.out.println("file.txt File Created in Project root directory");
        }else System.out.println("File file.txt already exists in the project root directory");*/
		System.out.println("done");

	}

}
