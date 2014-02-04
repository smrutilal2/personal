package home.algo.misc;

import home.algo.Util;

public class SortWhiteBlackBalls {

	public void sort(char[] array) {
		int left = 0;
		int right = array.length - 1;

		for (; left <= right;) {
			if (array[left] == 'w' && array[right] == 'b') {
				Util.swap(array, left, right);
				left++;
				right--;
			} else {
				if (array[left] == 'b')
					left++;
				if (array[right] == 'w')
					right--;
			}

		}
	}

	public static void main(String[] args) {
		SortWhiteBlackBalls obj = new SortWhiteBlackBalls();
		char[] array = new char[] { 'w', 'b', 'b', 'w', 'w', 'b', 'b', 'b' };
		Util.print(array);
		obj.sort(array);
		Util.print(array);
	}
}
