package home.algo.misc;

public class PermutationOfCharactersInString {

	public void showPermutation(String input) {
		showPatternRecursive("", input);
	}

	private void showPatternRecursive(String str, String input) {

		if (input.length() <= 1)
			System.out.println(str + input);
		else {
			for (int i = 0; i < input.length(); i++) {
				String newString = input.substring(0, i)
						+ input.substring(i + 1);
				showPatternRecursive(str + input.charAt(i), newString);
			}
		}
	}

	public static void main(String[] args) {
		PermutationOfCharactersInString string = new PermutationOfCharactersInString();
		string.showPermutation("abcd");
		System.out.println("*********");
		string.showPermutation("aacd");
	}
}
