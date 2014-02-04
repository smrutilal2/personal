package home.algo.misc;

public class RemoveDuplicateDigitsFromArray {

	public int[] removeDupliateEntries(int... nos) {
		int tail = 0;
		for (int i = 0; i < nos.length; i++) {
			if (i < (nos.length - 1) && nos[i] != nos[i + 1]) {
				nos[++tail] = nos[i + 1];
			}
		}
		for (int i = tail + 1; i < nos.length; i++) {
			nos[i] = -1;
		}
		return nos;
	}

	public static void main(String[] args) {
		int[] array = new RemoveDuplicateDigitsFromArray()
				.removeDupliateEntries(1, 1, 2, 3, 4, 4, 4, 5);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

}
