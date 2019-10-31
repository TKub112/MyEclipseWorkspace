

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Tests {

	@Test
	void CheckStrings1() {
		double ratio = 1.5;
		String s1 = "kita";
		String s2 = "kot";
		LevensteinCalc calculator = new LevensteinCalc();
		assertEquals(calculator.Calc(s1,s2),1.5,0.0001);
	}
	@Test
	void CheckStrings2() {
		double ratio = 0.5;
		String s1 = "kwiat";
		String s2 = "kwist";
		LevensteinCalc calculator = new LevensteinCalc();
		assertEquals(calculator.Calc(s1,s2),0.5,0.0001);
	}
	@Test
	void CheckStrings3() {
		double ratio = 2;
		String s1 = "drab";
		String s2 = "dal";
		LevensteinCalc calculator = new LevensteinCalc();
		assertEquals(calculator.Calc(s1,s2),2.0,0.0001);
	}
	

}
