import static org.junit.Assert.*;

import org.junit.Test;


public class DemoTest {

	@Test
	public void testAddition() {
		int x = 2 + 3;
		assertEquals("adding 2 and 3", 5, x);
	}
	
	@Test(expected=ArithmeticException.class)
	public void testDivisionByZero() {
		int x = 2;
		int y = 0;
		assertEquals("dividing 2 and 0", 0, x/y);
	}

	@Test(timeout=100)
	public void testCountToATrillion() {
		long x = 0;
		for(long i=0; i<100000000000L; i++)
		{
		    x++;
		}
		assertEquals("Count to a Trillion", 100000000000L, x);
	}
}
