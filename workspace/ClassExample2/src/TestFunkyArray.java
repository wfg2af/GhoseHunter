import static org.junit.Assert.*;

import org.junit.Test;


public class TestFunkyArray {

	@Test
	public void testConstructor() {
		FunkyArray f = new FunkyArray(11);
		assertEquals("new array's length", 11, f.size());
	}
	@Test
	public void testConstructor2() {
		FunkyArray f = new FunkyArray(0);
		assertEquals("new array's length", 0, f.size());
	}
	@Test(expected=NegativeArraySizeException.class)
	public void testConstructor3() {
		FunkyArray f = new FunkyArray(-1);
		assertEquals("new array's length", -1, f.size());
	}
	@Test
	public void testMinimum() {
		FunkyArray f = new FunkyArray(11);
		int min = f.min();
		assertEquals("min of all 0s is 0", 0, min);
	}
	@Test
	public void testMinimumOfAllThrees() {
		FunkyArray f = new FunkyArray(11);
		f.set(0, 3);
		f.set(1, 3);
		f.set(2, 3);
		int min = f.min();
		assertEquals("min of all 0s is 0", 3, min);
	}
	public void testSet00() {
		FunkyArray f = new FunkyArray(11);
		f.set(0, 0);
		assertEquals("min of all 0s is 0", 3, f.get(0));
	}
}
