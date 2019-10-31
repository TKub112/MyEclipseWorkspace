package application;
import java.util.*; 

public class AddCyclistTimer{
	


	//creating a new instance of timer class 
	 public AddCyclistTimer(Set<String> names,JavaFXStarter window) {
	 
	 Timer timer = new Timer(); 
     timer.scheduleAtFixedRate(new addCyclist(names,window),0, 2400); 
	 }


}
