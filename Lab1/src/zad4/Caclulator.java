package zad4;
import javax.swing.*;
import java.util.Scanner;
import java.util.Arrays;
public class Caclulator {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("InputDialog Example #1");
		String input = JOptionPane.showInputDialog(frame, "Give expression");
		int[] numbers = new int[3];
		char[] characters = new char[2];
		try {
			getNumbers(input,numbers,characters);
		}
		catch(IllegalArgumentException e)
		{
			
		}
		
		
		
		
		System.out.println(calculate(input,numbers,characters));

		
	}
	
	
	static void getNumbers(String input,int[] numbers,char[] operations){
		int indexNumbers=0, indexOperations=0;
		String number = "";
		for(int i = 0 ; input.length() > i; i++){
			
			if(Character.isDigit(input.charAt(i))){
				number=number + Character.getNumericValue(input.charAt(i));
			}
			
			else if (input.charAt(i)=='+' | input.charAt(i)=='-' | input.charAt(i)=='*'){
				operations[indexOperations] = input.charAt(i);
				indexOperations++;
				numbers[indexNumbers] = Integer.parseInt(number);
				number = "";
				
				indexNumbers++;
			}
			else if (input.charAt(i)==' '){
				
			}
			else 
			{
				throw new IllegalArgumentException("Wrong Expression");
			}
		}
		numbers[indexNumbers] = Integer.parseInt(number);
	}
	
	static int calculate(String input,int[] numbers,char[] operations){
		int sum = 0;
		//variant with muliply priority
		if(operations[1]=='*')
		{
			sum = numbers[1]*numbers[2];
			
			if(operations[0]=='+'){
				sum = numbers[0]+sum;
			}
			else if(operations[0]=='-'){
				sum = numbers[0]-sum;
			}
			else if(operations[0]=='*'){
				sum = numbers[0]*sum;
			}
			return sum;
		}
		//without multiple priortiy
		else
		{
			if(operations[0]=='*'){
				sum = numbers[0]*numbers[1];
			}
			else if(operations[0]=='+'){
				sum = numbers[0]+numbers[1];
			}
			else if(operations[0]=='-'){
				sum = numbers[0]-numbers[1];
			}
			if(operations[1]=='*'){
				sum = sum * numbers[2];
			}
			else if(operations[0]=='+'){
				sum = sum + numbers[2];
			}
			else if(operations[0]=='-'){
				sum = sum-numbers[2];
			}
		}
		
			
			
		
		
		
		return sum;
	}
	
	
	
}
	

