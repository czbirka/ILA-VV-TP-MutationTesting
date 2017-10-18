package m2.ila.fr.istic.ila.vv;


import org.junit.Test;

import junit.framework.Assert;

public class BinOpTest {
		
	@Test
	public void testAddition1 () {
		double result = BinOp.addition(4, 4);
		Assert.assertEquals("Result should be ", 8.0 , result);
	}
	
	@Test
	public void testMultiplication1 () {
		double result = BinOp.multiplication(2, 4);
		Assert.assertEquals("Result should be ", 8.0 , result);
	}
	
	@Test
	public void testDivision1 () {
		double result = BinOp.division(6, 3);
		Assert.assertEquals("Result should be ", 2.0 , result);
	}
	
	@Test
	public void testSoustraction1 () {
		double result = BinOp.soustraction(6, 3);
		Assert.assertEquals("Result should be ", 3.0 , result);
	}
}
