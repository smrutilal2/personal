package home.algo.misc;

public class LongestPalindromInAString {

	public String getLongestPlindrom(String input) {

		String palindrom = "";
		if (null != input) {
			int start = 0;
			int end = input.length() - 1;
			int begin = start;
			int tail = end;
			while (start < end) {
				if (input.charAt(start++) != input.charAt(end--)) {
					begin = start;
					tail = end;
				}
			}
			return input.substring(begin, tail + 1);
		}

		return palindrom;
	}

	public static void main(String[] args) {
		LongestPalindromInAString aString = new LongestPalindromInAString();

		System.out.println(aString.getLongestPlindrom("acdefedca"));
		System.out.println(aString.getLongestPlindrom("acdefedba"));
		System.out.println(aString.getLongestPlindrom("acdef"));
		System.out.println(aString.getLongestPlindrom("acdefceba"));
	}
}
