import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class KeyChecker {
	static Map<Character, Set> Keys = new HashMap<>();;
	String keys1 = "ASDFGHJKL";
	String keys2 = "QWERTUIOP";
	String keys3 = "ZXCVBNM";

	protected void init()
	{
		
		loadKeys(keys1);
		loadKeys(keys2);
		loadKeys(keys3);
		
		
	}
	protected boolean isNeighbor(char c, char c1)
	{
		//pobieram tablice z mapy
		
		Set<Character> keys = new HashSet<Character>();
		if(Keys.get(Character.valueOf(Character.toUpperCase(c))) != null )
		{
			keys = Keys.get(Character.toUpperCase(c));
			if(keys.contains(Character.toUpperCase(c1))){
				 return true;
			 }
			
		}
		if( Keys.get(Character.valueOf(Character.toLowerCase(c))) != null )
		{
			keys = Keys.get(Character.toLowerCase(c));
			if(keys.contains(Character.toUpperCase(c1))){
				 return true;
			 }
			
		}
		
		return false;
		 
		//sprawdzam czy c1 pokrywa sie z tymi wartosciami
		
	}
	
	void loadKeys(String keys1)
	{
		for(int i = 0;i < keys1.length() ;i++)
		{
			//pobieram sasiadow
			 Set<Character> resultSet = new HashSet<Character>();
			
			
			if(i==0)
			{
				resultSet.add(keys1.charAt(i+1));
				
			}
			else if(i==keys1.length()-1)
			{
				resultSet.add(keys1.charAt(i-1));
			}
			else
			{
				resultSet.add(keys1.charAt(i-1));
				resultSet.add(keys1.charAt(i+1));
			}
            
			Keys.put(Character.valueOf(keys1.charAt(i)), resultSet);
			
		}
	}
}
