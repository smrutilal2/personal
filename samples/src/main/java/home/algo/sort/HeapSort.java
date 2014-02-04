package home.algo.sort;

import static home.algo.Util.print;
import static home.algo.Util.swap;

public class HeapSort {

	private int[] data;

	public HeapSort(int... data) {

		this.data = data;
		maxHeapify(data, data.length);
		// minHeapify(data);
	}

	public void sort() {
		int length = data.length;
		while (length > 0) {
			swap(data, 0, --length);
			maxHeapify(data, length);
		}
	}

	void maxHeapify(int[] data, int length) {

		int lastParentIndex = length / 2 - 1;
		while (lastParentIndex >= 0) {
			maxSiftDown(data, lastParentIndex--, length - 1);
		}
	}

	void minHeapify(int... data) {

		int lastParentIndex = data.length / 2 - 1;
		while (lastParentIndex >= 0) {
			minSiftDown(data, lastParentIndex--, data.length - 1);
		}
	}

	private void minSiftDown(int[] data, int parentIndex, int lastIndex) {

		int leftChildIndex = parentIndex * 2 + 1;
		int rightChildIndex = parentIndex * 2 + 2;

		int swapIndex = parentIndex;

		if (leftChildIndex <= data.length - 1)
			swapIndex = data[swapIndex] > data[leftChildIndex] ? leftChildIndex
					: swapIndex;
		if (rightChildIndex <= data.length - 1)
			swapIndex = data[swapIndex] > data[rightChildIndex] ? rightChildIndex
					: swapIndex;
		if (swapIndex != parentIndex) {
			swap(data, parentIndex, swapIndex);
		}
	}

	private void maxSiftDown(int[] data, int parentIndex, int lastIndex) {

		int leftChildIndex = parentIndex * 2 + 1;
		int rightChildIndex = parentIndex * 2 + 2;

		int swapIndex = parentIndex;

		if (leftChildIndex <= lastIndex)
			swapIndex = data[swapIndex] < data[leftChildIndex] ? leftChildIndex
					: swapIndex;
		if (rightChildIndex <= lastIndex)
			swapIndex = data[swapIndex] < data[rightChildIndex] ? rightChildIndex
					: swapIndex;
		if (swapIndex != parentIndex)
			swap(data, parentIndex, swapIndex);
	}

	public static void main(String[] args) {

		int[] data = new int[] { 2, 3, 9, 1, 6 };
		print(data);
		HeapSort heapSort = new HeapSort(data);
		heapSort.sort();
		print(data);

		data = new int[] { 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
		print(data);
		heapSort = new HeapSort(data);
		heapSort.sort();
		print(data);
	}

}
