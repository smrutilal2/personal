package home.algo.sort;

import home.algo.Util;

public class BubbleSort {
	public int[] sort(int array[]) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					Util.swap(array, i, j);
				}
			}
		}
		return array;
	}

	public static void main(String[] args) {
		int[] unsortedArray = new int[] { 3, 8, 1, 7, 6 };
		Util.print(unsortedArray);
		Util.print(new BubbleSort().sort(unsortedArray));
		System.out.println("Complexity : O(n^2)");
	}

}
