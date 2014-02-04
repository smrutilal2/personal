package home.algo.sort;

import home.algo.Util;

public class InsertionSort {

	public int[] sort(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int numToInsert = nums[i];
			int holePosition = i;
			while (holePosition > 0 && numToInsert < nums[holePosition - 1]) {
				nums[holePosition] = nums[holePosition - 1];
				holePosition = holePosition - 1;
			}
			nums[holePosition] = numToInsert;
		}
		return nums;
	}

	public static void main(String[] args) {
		InsertionSort insertionSort = new InsertionSort();
		int[] nums = new int[] { 3, 1, 5, 2, 9 };
		nums = insertionSort.sort(nums);
		Util.print(nums);
	}
}
