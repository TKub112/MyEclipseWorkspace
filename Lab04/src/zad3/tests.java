package zad3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class tests {

	@Test
	void test1() {
		List<Double> li = new ArrayList<Double>();
		li.add((double)-5100);
		li.add(43.257);
		li.add((double)200000);
		li.add(2000000.5);
		List<String> fn2 = zad3.formattedNumbers(li, 3, '|', 2,false);
		System.out.println(fn2.size());
		assertTrue(fn2.size() == 4);
		assertTrue(fn2.get(0).equals(" -5|100"));
		assertTrue(fn2.get(1).equals("43.26"));
		assertTrue(fn2.get(2).equals("200|000")); 
		assertTrue(fn2.get(3).equals("2|000|000.5")); 
	}
	@Test
	void test2() {
		List<Double> li = new ArrayList<Double>();
		li.add((double)-5100);
		li.add(43.257);
		li.add((double)200000);
		li.add(2000000.5);
		List<String> fn2 = zad3.formattedNumbers(li, 2, ',', 2,true);
		assertTrue(fn2.size() == 4);
		assertTrue(fn2.get(0).equals("-51,00.00"));
		assertTrue(fn2.get(1).equals("43.26"));
		assertTrue(fn2.get(2).equals("20,00,00.00"));
		assertTrue(fn2.get(3).equals("2,00,00,00.50")); 

	}
	

}
