/**
 * 
 */
package home.algo.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author smruti
 * 
 */
public class PrimeFactorsOfANumber {

	public List<Integer> findPrimeFactors(int number) {

		List<Integer> factors = new ArrayList<Integer>();
		findFactors(number, number / 2, factors);
		for (Iterator<Integer> iterator = factors.iterator(); iterator
				.hasNext();) {
			Integer factor = iterator.next();
			if (!isPrime(factor))
				iterator.remove();
		}
		return factors;
	}

	public List<Integer> findCombinationOfPrimeFactors(int number) {
		List<Integer> primeFactors = findPrimeFactors(number);
		List<Integer> combinatinOfPrimeFactors = new ArrayList<Integer>();
		findCombination(number, primeFactors, 0, combinatinOfPrimeFactors);
		return combinatinOfPrimeFactors;
	}

	public List<Integer> findBetterCombinationOfPrimeFactors(int number) {

		List<Integer> combinatinOfPrimeFactors = new ArrayList<Integer>();
		if (0 == number || 1 == number || 0 > number)
			return combinatinOfPrimeFactors;
		else
			findBetterCombination(number, 2, combinatinOfPrimeFactors);
		return combinatinOfPrimeFactors;
	}

	private void findBetterCombination(int number, int factor,
			List<Integer> combinatinOfPrimeFactors) {
		if (1 == number)
			return;
		if (0 == number % factor) {
			combinatinOfPrimeFactors.add(factor);
			findBetterCombination(number / factor, factor,
					combinatinOfPrimeFactors);
		} else
			findBetterCombination(number, ++factor, combinatinOfPrimeFactors);
	}

	private void findCombination(int number, List<Integer> primeFactors,
			int index, List<Integer> combinatinOfPrimeFactors) {

		if (1 == number)
			return;
		if (index >= primeFactors.size())
			return;
		int factor = primeFactors.get(index);
		if (0 == number % factor) {
			combinatinOfPrimeFactors.add(factor);
			findCombination(number / factor, primeFactors, index,
					combinatinOfPrimeFactors);
		} else {
			findCombination(number, primeFactors, ++index,
					combinatinOfPrimeFactors);
		}
	}

	private boolean isPrime(Integer number) {

		if (0 == number || 1 == number)
			return false;
		List<Integer> factors = new ArrayList<Integer>();
		findFactors(number, number / 2, factors);
		return 1 >= factors.size();
	}

	private void findFactors(int number, int devisor, List<Integer> factors) {

		if (0 == devisor)
			return;
		else {
			if (0 == number % devisor)
				factors.add(devisor);
			findFactors(number, --devisor, factors);
		}
	}

}
