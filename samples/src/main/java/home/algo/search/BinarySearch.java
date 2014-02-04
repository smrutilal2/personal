package home.algo.search;

public class BinarySearch {

	private int[] numbers;

	public int search(int[] numbers, int value) {
		this.numbers = numbers;
		return binarySearch1(0, numbers.length - 1, value);
	}

	private int binarySearch1(int start, int end, int value) {
		if (start == end)
			return value == numbers[start] ? start : -1;

		int mid = (start + end) / 2;
		if (value == numbers[mid])
			return mid;
		else if (value < numbers[mid]) {
			return binarySearch1(start, mid - 1, value);
		} else {
			return binarySearch1(mid + 1, end, value);
		}
	}

	public int binarySearch2(int[] numbers, int value, int start, int end) {

		if (start > end)
			return -1;

		int mid = (start + end) / 2;

		if (value == numbers[mid])
			return mid;
		else if (value < numbers[mid])
			return binarySearch2(numbers, value, start, mid - 1);
		else
			return binarySearch2(numbers, value, mid + 1, end);
	}

	public static void main(String[] args) {
		BinarySearch search = new BinarySearch();

		int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		System.out.println(search.search(numbers, 4));
		System.out.println(search.binarySearch2(numbers, 11, 0,
				numbers.length - 1));
	}
}
