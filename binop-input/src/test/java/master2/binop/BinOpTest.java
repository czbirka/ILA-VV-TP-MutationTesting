package master2.binop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinOpTest {
		
	@Test
	public void testAddition1 () {
		double result = BinOp.addition(4, 4);
		assertEquals("Result should be ", 8.0 , result, 0.00001);
	}
	
	@Test
	public void testMultiplication1 () {
		double result = BinOp.multiplication(2, 4);
		assertEquals("Result should be ", 8.0 , result, 0.00001);
	}
	
	@Test
	public void testDivision1 () {
		double result = BinOp.division(6, 3);
		assertEquals("Result should be ", 2.0 , result, 0.00001);
	}
	
	@Test
	public void testSoustraction1 () {
		double result = BinOp.soustraction(6, 3);
		assertEquals("Result should be ", 3.0 , result, 0.00001);
	}
	
	@Test
	public void testSuperieur1 () {
		boolean result = BinOp.estSuperieur(4, 3);
		assertTrue("Result should be ", true);
	}
}
