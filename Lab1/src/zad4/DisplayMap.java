package zad4;

import java.util.HashMap;
import java.util.Map;

public class DisplayMap {
	Map<Integer,String> digits = new HashMap<Integer,String>();
	Map<Integer,String> prefixes = new HashMap<Integer,String>();
	Map<Integer,String> operation = new HashMap<Integer,String>();
	DisplayMap(){
		//init
		digits.put(1,"jeden");
		digits.put(2, "dwa");
		digits.put(3, "trzy");
		digits.put(4, "cztery");
		digits.put(5, "piec");
		digits.put(6, "jeden");
		digits.put(7, "dwa");
		digits.put(8, "trzy");
		digits.put(9, "cztery");
		digits.put(10,"jeden");
		digits.put(11, "jedenascie");
		digits.put(12, "dwanascie");
		digits.put(13, "trzynascie");
		digits.put(14, "czternascie");
		digits.put(15, "pietnascie");
		digits.put(16, "szesnascie");
		digits.put(17, "siedemnascie");
		digits.put(18, "osiemnascie");
		digits.put(19, "dziewietnascie");
		digits.put(20, "dwadziesiecia");
		
		
		prefixes.put(2, "dwadziescia");
		prefixes.put(3, "trzydziesci");
		prefixes.put(4, "czterdziesci");
		prefixes.put(5, "piecdziesiat");
		prefixes.put(6, "szczescdziesiat");
		prefixes.put(7, "siedemdziesiat");
		prefixes.put(8, "osiemdzisiat");
		prefixes.put(9, "dziewecdziesiat");
		
		operation.put(1, "plus");
		operation.put(2, "minus");
		operation.put(3, "razy");
		
		
		
		
	}
	
	void displayNumber(String input)
	{
		if(input.length()==1)
		{
			String text = digits.get(Integer.parseInt(input));
			System.out.print(text);
		}
		else if(input.length()==2)
		{
			if(Integer.parseInt(input)<=20 && Integer.parseInt(input)>=10)
			{
				System.out.print(digits.get(Integer.parseInt(input)) + ' ');
			}
			else if(Integer.parseInt(input)%10==0)
			{
				System.out.print(prefixes.get(Character.getNumericValue(input.charAt(0))) + ' ');
			}
			else
			{
				String text = prefixes.get(Character.getNumericValue(input.charAt(0)))+' ';
				text += digits.get(Character.getNumericValue(input.charAt(1)));
				System.out.print(text+' ');
			}
			
			
		}
		
	}
	
	void displayOperation(String input)
	{
		if(input.equals("+"))
		{
			System.out.print(' ' + operation.get(1) + ' ');
		}
		if(input.equals("-"))
		{
			System.out.print(' ' + operation.get(2) + ' ');
		}
		if(input.equals("*"))
		{
			System.out.print(' ' + operation.get(3) + ' ');
		}

	}
	
	
	
}
