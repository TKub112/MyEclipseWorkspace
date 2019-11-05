package zad3;

import java.util.HashMap;

public class dict {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,Integer> hash_map = new HashMap<String,Integer>(); 
		String s = "intresting is this is my best summer and most intresting summer";
		String[] splitString = s.split(" ");
		
		for (String s1: splitString) {           
		    containMethod(hash_map,s1,1);
		}
		System.out.println(hash_map); 
     
        
	}

	private static void containMethod(HashMap<String,Integer> map,String key, int update) {
			
			
			if(map.containsKey(key)){
				Integer oldValue = map.get(key);
				map.remove(key);
				map.put(key, oldValue+1);
			}
			else{
				map.put(key, 1);
			}
			
			
	    
		
	}

}
