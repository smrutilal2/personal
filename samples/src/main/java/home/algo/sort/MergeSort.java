package home.algo.sort;

import home.algo.Util;

public class MergeSort {

	private int[] list;

	public int[] getList() {
		return list;
	}

	public MergeSort(int[] listToSort) {
		this.list = listToSort;
	}

	public void sort() {
		sort(list);
	}

	private void sort(int[] array) {
		if (1 == array.length)
			return;

		int[] left = new int[array.length / 2];
		System.arraycopy(array, 0, left, 0, left.length);

		int[] right = new int[array.length - left.length];
		System.arraycopy(array, left.length, right, 0, right.length);

		sort(left);
		sort(right);

		merge(left, right, array);
	}

	private void merge(int[] left, int[] right, int[] array) {
		int leftIndex = 0;
		int rightIndex = 0;
		int arrayIndex = 0;

		while (leftIndex < left.length && rightIndex < right.length) {
			if (left[leftIndex] < right[rightIndex])
				array[arrayIndex++] = left[leftIndex++];
			else
				array[arrayIndex++] = right[rightIndex++];
		}

		int[] rest;
		int restIndex;
		if (leftIndex >= left.length) {
			rest = right;
			restIndex = rightIndex;
		} else {
			rest = left;
			restIndex = leftIndex;
		}

		for (int i = restIndex; i < rest.length; i++) {
			array[arrayIndex++] = rest[i];
		}
	}

	/**
	 * another flavor to merge. memory intensive than the above one.
	 */
	int[] merge(int[] left, int[] right) {

		int[] result = new int[left.length + right.length];
		int index = 0;
		while (left.length > 0 || right.length > 0) {

			if (left.length > 0 && right.length > 0) {
				if (left[0] <= right[0]) {
					result[index++] = left[0];
					left = Util.rest(left);
				} else {
					result[index++] = right[0];
					right = Util.rest(right);
				}
			} else if (left.length > 0) {
				result[index++] = left[0];
				left = Util.rest(left);
			} else {
				result[index++] = right[0];
				right = Util.rest(right);
			}
		}

		return result;
	}

	@Override
	public String toString() {
		return "Time Complexity : nlogn   Sapce complexity : ";
	}

	public static void main(String[] args) {
		int[] arrayToSort = { 12, 9, 4, 99, 120, 1, 3, 10 };
		System.out.print("Unsorted: ");
		Util.print(arrayToSort);

		MergeSort mergeSort = new MergeSort(arrayToSort);
		mergeSort.sort();

		System.out.print("Sorted: ");
		Util.print(mergeSort.getList());
		System.out.println(mergeSort);

		int[] arrayToSort1 = { 8, 3, 1, 7, 6 };
		System.out.print("Unsorted: ");
		Util.print(arrayToSort1);

		BetterMergeSort betterMergeSort = new BetterMergeSort();
		betterMergeSort.sort(arrayToSort1);

		System.out.print("Sorted: ");
		Util.print(arrayToSort1);
		System.out.println(betterMergeSort);

	}
}

/**
 * With space complexity O(n)
 */
class BetterMergeSort {
	public void sort(int[] arrayToSort) {
		int[] tmpArray = new int[arrayToSort.length];
		sort(arrayToSort, tmpArray, 0, arrayToSort.length - 1);
	}

	private void sort(int[] arrayToSort, int[] tmpArray, int startIndex,
			int endIndex) {
		if (startIndex == endIndex)
			return;
		int midIndex = (startIndex + endIndex) / 2;
		sort(arrayToSort, tmpArray, startIndex, midIndex);
		sort(arrayToSort, tmpArray, midIndex + 1, endIndex);
		merge(arrayToSort, tmpArray, startIndex, midIndex, midIndex + 1,
				endIndex);
	}

	private void merge(int[] arrayToSort, int[] tmpArray, int leftStartIndex,
			int leftEndIndex, int rightStartIndex, int rightEndIndex) {

		int size = rightEndIndex - leftStartIndex + 1;
		int tmpIndex = leftStartIndex;

		while (leftStartIndex <= leftEndIndex
				&& rightStartIndex <= rightEndIndex) {
			if (arrayToSort[leftStartIndex] < arrayToSort[rightStartIndex])
				tmpArray[tmpIndex++] = arrayToSort[leftStartIndex++];
			else
				tmpArray[tmpIndex++] = arrayToSort[rightStartIndex++];
		}

		while (rightStartIndex <= rightEndIndex)
			tmpArray[tmpIndex++] = arrayToSort[rightStartIndex++];

		while (leftStartIndex <= leftEndIndex)
			tmpArray[tmpIndex++] = arrayToSort[leftStartIndex++];

		System.arraycopy(tmpArray, 0, arrayToSort, 0, size);
	}

	@Override
	public String toString() {
		return "Time Complexity : nlogn   Sapce complexity : n";
	}
}
