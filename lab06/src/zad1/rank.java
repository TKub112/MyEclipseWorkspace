package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class rank {

	public static void main(String[] args) {
		
		
		
		String fileName = getname();
		//read file into stream, try-with-resources
		Path path = Paths.get(fileName); // For UNIX
		List f = new ArrayList();
		
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			
			 f.add(stream.filter(s -> s.contains("PL")).collect(Collectors.toList()));
			 System.out.println(f.get(0));

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	private static String getname() {
		
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String s = "";
		try {
			s = currentDirectory.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String fileName = s + "\\src\\zad1\\dane.txt";
		return fileName;
		
	}
	

}
