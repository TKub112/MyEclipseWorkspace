package Poligon;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Set;

import org.junit.jupiter.api.Test;

class TestCases {

	@Test
	void testGetNameURL() {
		try {
			Set<String> set = URLReader.getNamesFromURL();
			//System.out.println(set);
			assertEquals(12,set.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void testSortedQueue() {
		Queue queue ;
		queue = new Queue();
		
		queue.addCyclist("Piotr", 260.00);
		queue.addCyclist("Gawel", 250.00);
		queue.addCyclist("Pd", 229.00);
		queue.addCyclist("Kraków", 210.00);
		queue.addCyclist("Mi", 1.00);
		queue.addCyclist("Maciek", 240.00);
		queue.addCyclist("Maciek", 2500.00);
		
		  System.out.println(Arrays.toString(queue.queue.toArray()));
		
		assertEquals(1, 1);
		
		
		
		
		
	}
	

}
