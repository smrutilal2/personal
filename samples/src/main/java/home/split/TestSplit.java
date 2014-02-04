package home.split;

import java.util.StringTokenizer;

public class TestSplit /* extends Assert */{

	// @Test
	public void testBasicSplit() {
		// assertArrayEquals(new String[] { "a", "b", "c" },
		// "a,b,c".split(","));
		//
		// assertArrayEquals(new String[] { "a", "", "b", "c" },
		// "a,,b,c".split(","));
		//
		// // assertArrayEquals(new String[] { "", "a", "b", "c" },
		// ",a,b,c".split(","));

		// assertArrayEquals(new String[] { "a", "b", "c" },
		// ",a,b,c".split("//,"));

	}

	// @Test
	public void testStringTokenizer() {
		StringTokenizer tokenizer = new StringTokenizer("this is a test");
		while (tokenizer.hasMoreElements()) {
			String string = (String) tokenizer.nextElement();
			System.out.println(string);
		}

	}

}
