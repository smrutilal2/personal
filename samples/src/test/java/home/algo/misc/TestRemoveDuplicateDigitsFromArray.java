/**
 * 
 */
package home.algo.misc;

import home.algo.Util;

import org.junit.Test;

/**
 * @author smruti
 * 
 */
public class TestRemoveDuplicateDigitsFromArray {

	private RemoveDuplicateDigitsFromArray object = new RemoveDuplicateDigitsFromArray();

	@Test
	public void removeDuplicateDigits() {

		int[] result = object.removeDupliateEntries(1, 1, 2, 3, 4, 4, 4, 5);
		Util.print(result);
	}

}
