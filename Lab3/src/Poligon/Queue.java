package Poligon;

import java.util.Comparator; 
import java.util.PriorityQueue;

import datastructures.CyclistRecord; 
  



  
public class Queue { 
	public static PriorityQueue<CyclistRecord> queue = new PriorityQueue<>();
	
	
	/*static Comparator<CyclistRecord> stringLengthComparator = new Comparator<CyclistRecord>() {
        @Override
        public int compare(CyclistRecord o1, CyclistRecord o2) {

   		 if(o1.getTime()>o2.getTime()){
   	         return 1;
   	     } 
   		 else if (o1.getTime()<o2.getTime()){
   	         return -1;
   	     } 
   		 return 0;
   	} 
    };*/
	
	
	

    public void addCyclist(String name,double time) 
    { 
    	 queue.add(new CyclistRecord(name,time)); 
    }
    
} 