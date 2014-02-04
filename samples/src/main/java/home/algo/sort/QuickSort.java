package home.algo.sort;

import home.algo.Util;

public class QuickSort {

	public void sort(int[] arrayToSort) {
		sort(arrayToSort, 0, arrayToSort.length - 1);
	}

	private void sort(int[] arrayToSort, int startIndex, int endIndex) {
		if (startIndex >= endIndex)
			return;

		int pivotIndex = (startIndex + endIndex) / 2;

		int partitionIndex = partition2(arrayToSort, startIndex, endIndex,
				pivotIndex);

		sort(arrayToSort, startIndex, partitionIndex - 1);
		sort(arrayToSort, partitionIndex + 1, endIndex);
	}

	private int partition1(int[] arrayToSort, int startIndex, int endIndex,
			int pivotIndex) {
		int pivotvalue = arrayToSort[pivotIndex];
		Util.swap(arrayToSort, pivotIndex, endIndex);
		int index = startIndex;
		for (int i = startIndex; i < endIndex; i++) {
			if (arrayToSort[i] <= pivotvalue) {
				Util.swap(arrayToSort, i, index++);
			}
		}
		Util.swap(arrayToSort, index, endIndex);
		return index;
	}

	private int partition2(int[] array, int startIndex, int endIndex,
			int pivotIndex) {
		int pivotValue = array[pivotIndex];
		while (startIndex < endIndex) {
			if (array[startIndex] >= pivotValue
					&& array[endIndex] <= pivotValue) {
				Util.swap(array, startIndex, endIndex);
			}
			if (array[startIndex] < pivotValue)
				startIndex++;
			if (array[endIndex] > pivotValue)
				endIndex--;
		}
		return startIndex;
	}

	public static void main(String[] args) {
		int[] arrayToSort = { 12, 9, 4, 99, 120, 1, 3, 10 };
		System.out.print("Unsorted: ");
		Util.print(arrayToSort);

		QuickSort quickSort = new QuickSort();
		// OtherQuickSort quickSort = new OtherQuickSort();
		quickSort.sort(arrayToSort);

		System.out.print("Sorted: ");
		Util.print(arrayToSort);
	}

}

class OtherQuickSort {
	public void sort(int[] array) {
		sort(array, 0, array.length - 1);
		// sort2(array);
	}

	// public void sort2(int[] array) {
	// int pivotindex = array.length / 2;
	// partition(array, pivotindex);
	// }

	// private void partition(int[] array, int pivotindex) {
	// int pivotValue = array[pivotindex];
	// int startIndex = 0;
	// int endIndex = array.length - 1;
	// while (startIndex < endIndex) {
	// if (array[startIndex] >= pivotValue
	// && array[endIndex] <= pivotValue) {
	// Util.swap(array, startIndex, endIndex);
	// }
	// if (array[startIndex] < pivotValue)
	// startIndex++;
	// if (array[endIndex] > pivotValue)
	// endIndex--;
	// }
	//
	// partition(array, (0 + startIndex) / 2);
	// partition(array, (startIndex + 1 + array.length - 1) / 2);
	//
	// }

	private void sort(int[] array, int startIndex, int endIndex) {
		if (startIndex >= endIndex)
			return;
		int pivotIndex = (startIndex + endIndex) / 2;
		int partitionIndex = partition(array, startIndex, endIndex, pivotIndex);
		sort(array, startIndex, partitionIndex - 1);
		sort(array, partitionIndex + 1, endIndex);
	}

	private int partition(int[] array, int startIndex, int endIndex,
			int pivotIndex) {
		int pivotValue = array[pivotIndex];
		while (startIndex < endIndex) {
			if (array[startIndex] >= pivotValue
					&& array[endIndex] <= pivotValue) {
				Util.swap(array, startIndex, endIndex);
			}
			if (array[startIndex] < pivotValue)
				startIndex++;
			if (array[endIndex] > pivotValue)
				endIndex--;
		}
		return startIndex;
	}
}
