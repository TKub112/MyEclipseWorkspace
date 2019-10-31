package zad4;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Displayer {
	static DisplayMap map = new DisplayMap();
	static int index=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("InputDialog Example #1");
		String expression = JOptionPane.showInputDialog(frame, "Give expression");
		
		
		//iterate through string
		try {
			checkDisplayNumber(expression);
			checkDisplayOperation(expression);
			checkDisplayNumber(expression);
			checkDisplayOperation(expression);
			checkDisplayNumber(expression);
		}
		catch(StringIndexOutOfBoundsException e)
		{
			System.out.println("Wrong expression");
		}
		catch(IllegalAccessError e)
		{
			System.out.println("Wrong expression");
		}
										
	}
	
	static void checkDisplayNumber(String expression)
	{
		
		if(Character.isDigit(expression.charAt(index))&&expression.length()==index+1){//at last digit
			map.displayNumber(Character.toString(expression.charAt(index)));
		}
		else if(Character.isDigit(expression.charAt(index))&&Character.isDigit(expression.charAt(index+1))){
			map.displayNumber(expression.substring(index,index+2));
			index+=3;
		}
		else if(Character.isDigit(expression.charAt(index))&&expression.charAt(index+1)==' '){
			map.displayNumber(expression.substring(index,index+1));
			index+=2;
		}
		else
		{
			throw new IllegalAccessError("error");
		}
		
	}
	
	static void checkDisplayOperation(String expression)
	{
		if(expression.charAt(index)=='*'||expression.charAt(index)=='+'||expression.charAt(index)=='-'){
			map.displayOperation(expression.substring(index,index+1));
			index++;
		}
		else
		{
			throw new IllegalAccessError("error");
		}
		
		if(expression.charAt(index)==' '){
			index++;
		}
		else
		{
			throw new IllegalAccessError("error");
		}
	}

}
