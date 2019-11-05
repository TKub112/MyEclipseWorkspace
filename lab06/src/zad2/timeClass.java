package zad2;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class timeClass {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		
		Date startWW2 = new Date(1900,9,1);
		Date endWW2 = new Date(1945,9,1);
		String dateString = format.format( new Date()   );
		try {
			startWW2       = format.parse ( "1939-12-31" );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		String dateString1 = format.format( new Date()   );
		try {
			endWW2       = format.parse ( "1945-12-31" );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		//(wliczaj¹c zarówno 1.09.1939, jak i 8.05.1945
		long days1 = endWW2.getTime() / (24 * 60 * 60 * 1000) - startWW2.getTime() / (24 * 60 * 60 * 1000);
		System.out.println("WW2 time : " + days1);
		
		/*
		LocalDateTime myObj = LocalDateTime.now();
		Date d = new Date();
		LocalDateTime today =  LocalDateTime.now(); 
		Date currentDatePlusOneDay = Date.from(today.toInstant(null));
		System.out.println(currentDatePlusOneDay);      //Sun Jul 15 22:25:04 IST 2018
		
		Date dt = new Date();
		Calendar c = new GregorianCalendar(2016,2,1);
		c.setTime(dt); 
		
		c.add(Calendar., 1);
		dt = c.getTime();
		dt.getDay();
		dt.getMonth();
		
		System.out.println(dt);
	    System.out.println(dt.getDay());
	    System.out.println(dt.getMonth());
	    */
	    
		
		//2 
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");	
		Calendar calendar = new GregorianCalendar(2016,0,01);	
		//System.out.println("Date : " + sdf.format(calendar.getTime()));
		String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", 
		         "Oct", "Nov", "Dec"};
		String daysofWeek[] = {"", "Mon","Tue","Wedn","Thurs","Friday","Saturday","Sunday"}; // 0 is empty
		
		calendar.add(Calendar.DAY_OF_MONTH, 68);
		System.out.println("Date : " + sdf.format(calendar.getTime()));
		int month      = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
		int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println("Month " + months[month]);
		System.out.println("day " + daysofWeek[dayOfWeek]);
		getMonth();
		getDay();
		
		
		
		
		
	}

	private static void getDay() {
		// TODO Auto-generated method stub
		
	}

	private static void getMonth() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
