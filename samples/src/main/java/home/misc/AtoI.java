package home.misc;

public class AtoI {

	public int atoi(String number) {
		boolean signed;
		char sBit = number.charAt(0);
		signed = sBit == '+' || sBit == '-';
		int i = (signed) ? 1 : 0;
		int n = 0;

		for (; i < number.length(); i++) {
			char c = number.charAt(i);
			int digit = toDigit(c);
			n = n * 10 + digit;
		}

		if (signed)
			n = toSigned(n, sBit);
		return n;
	}

	private int toSigned(int n, char sBit) {
		if (sBit == '-')
			return n * -1;
		else
			return n;
	}

	private int toDigit(char c) {

		if (Character.isDigit(c))
			return Character.getNumericValue(c);
		else
			throw new IllegalArgumentException();
	}

	public static void main(String[] args) {

		AtoI atoI = new AtoI();

		System.out.println(atoI.atoi("123"));
		System.out.println(atoI.atoi("+123"));
		System.out.println(atoI.atoi("-123"));
		System.out.println(atoI.atoi("er45"));
	}

}
