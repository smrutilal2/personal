package home.misc;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestAtoI {

	AtoI atoI = new AtoI();

	@Test
	public void testBasicPositiveNumber() {
		assertTrue(123 == atoI.atoi("123"));
	}

	@Test
	public void testSignedNumber() {
		assertTrue(-123 == atoI.atoi("-123"));
		assertTrue(483 == atoI.atoi("+483"));
	}

	@Test
	public void testInvalidInput() {
		try {
			atoI.atoi("a65");
			fail("Invalid input");
		} catch (Exception e) {
			// expected
		}
	}

}
