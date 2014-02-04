/**
 * 
 */
package home.algo.misc;

import static org.junit.Assert.assertTrue;
import home.algo.Util;

import java.util.List;

import org.junit.Test;

/**
 * @author smruti
 * 
 */
public class TestPrimeFactorsOfANumber {

	PrimeFactorsOfANumber object = new PrimeFactorsOfANumber();

	@Test
	public void findPrimeFactors() {
		List<Integer> factors = object.findPrimeFactors(24);
		assertTrue(2 == factors.size());
		assertTrue(factors.contains(2));
		assertTrue(factors.contains(3));
	}

	@Test
	public void findCombinationOfPrimeFactors() {
		List<Integer> factors = object.findCombinationOfPrimeFactors(24);
		assertTrue(4 == factors.size());
		Util.print(factors);
	}

	@Test
	public void findBetterCombinationOfPrimeFactors() {
		List<Integer> factors = object.findBetterCombinationOfPrimeFactors(39);
		assertTrue(2 == factors.size());
		Util.print(factors);
	}

}
