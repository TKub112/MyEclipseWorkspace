package Poligon;

import java.util.HashSet;
import java.util.Set;

public class Funplace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> names = new HashSet<String>();
		try {
			names = URLReader.URL();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(names);
		
	}

}
