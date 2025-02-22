import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class peselData {
	private String sex;
	private Date birthDate;
	private String pesel;
	
	peselData(String pesel) throws WrongPeselException{
		

		this.pesel=pesel;
		
		if(checkSum()||pesel.length()!=11)
		{
			throw new WrongPeselException("Invalid pesel");
		}
		try {
			peselValidation(pesel);
			analyzeSex();
			analyzeDate(pesel);
			printInfo();
		} catch (WrongPeselException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private boolean checkSum() {
		int sum = 1 * pesel.charAt(0) +
		3 * pesel.charAt(1) +
		7 * pesel.charAt(2) +
		9 * pesel.charAt(3) +
		1 * pesel.charAt(4) +
		3 * pesel.charAt(5) +
		7 * pesel.charAt(6) +
		9 * pesel.charAt(7) +
		1 * pesel.charAt(8) +
		3 * pesel.charAt(9);
		sum %= 10;
		sum = 10 - sum;
		sum %= 10;
		 
		if (sum == pesel.charAt(10)) {
		return true;
		}
		else {
		return false;
		}
		}
	

	protected void setSex(String sex)
	{
		this.sex=sex;
	}
	
	protected void setDate(Date birthDate)
	{
		this.birthDate=birthDate;
	}
	
	protected void printInfo()
	{
		System.out.println(sex+ " " + birthDate);
	}
	
	protected void analyzeSex()
	{
		int sexStr = Integer.parseInt(pesel.substring(9,10));
		
		if(sexStr%2==0){
			setSex("Female");
		}
		else {
			setSex("Male");
		}
	}
	protected void analyzeDate(String input)
	{
		String year = input.substring(0,2);
		String month = input.substring(2,4);
		String day = input.substring(4,6);
		int birthInt = Integer.parseInt(month);
		
		if(birthInt>=1&&birthInt<=12)
		{
			year="19"+year;
			
		}
		else if(birthInt>=21&&birthInt<=32)
		{
			year="20"+year;
			month = Integer.toString(birthInt-20);
			
		}
		else if(birthInt>=41&&birthInt<=52)
		{
			year="21"+year;
			month = Integer.toString(birthInt-40);
			
		}
		else if(birthInt>=61&&birthInt<=72)
		{
			year="22"+year;
			month = Integer.toString(birthInt-60);
		}
		if(birthInt>=81&&birthInt<=92)
		{
			year="18"+year;
			month = Integer.toString(birthInt-80);
			
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String dateInString = day + "/" + month + "/" + year;
		Date date = null;
		try {
			date = sdf.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDate(date);
		//System.out.println(date); //15/10/2013
	}
	
	
	public boolean peselValidation(String input) throws WrongPeselException{
		int[] values = new int[] {9,7,3,1};
		int valuePosition = 0;
		int sum=0;
		if(	input.length()!=11)
		{
			throw new WrongPeselException("Invalid pesel");
		}
		int magicNumber = 0;
		for(magicNumber = 0; magicNumber<=9;magicNumber++)
		{
			sum=sum+Integer.parseInt(input.substring(magicNumber,magicNumber+1))*values[valuePosition];
			valuePosition++;
			if(valuePosition==4)
				valuePosition=0;
		}
		sum=sum%10;
		//Integer.parseInt(input.substring(magicNumber,magicNumber+1)));
		if(sum == Integer.parseInt(input.substring(magicNumber,magicNumber+1)))
		{
			//System.out.println(Integer.parseInt(input.substring(magicNumber,magicNumber+1)));
			//System.out.println("Right Pesel");
			return false;
		}
		else {
			throw new WrongPeselException("Invalid pesel");
			
		}
	}
	

}
