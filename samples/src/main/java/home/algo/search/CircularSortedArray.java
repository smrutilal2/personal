package home.algo.search;

public class CircularSortedArray {

	private BinarySearch binarySearch = new BinarySearch();

	public int search(int[] nums, int num) {
		return search(nums, num, 0, nums.length - 1);
	}

	private int search(int[] nums, int num, int startIndex, int endIndex) {

		if (startIndex >= endIndex)
			return -1;

		int midIndex = (startIndex + endIndex) / 2;
		int mid = nums[midIndex];

		if (mid == num)
			return midIndex;

		int index = -1;
		if (nums[0] < mid) {
			index = binarySearch.binarySearch2(nums, num, 0, midIndex - 1);
			if (-1 == index)
				return search(nums, num, midIndex + 1, endIndex);
		} else {
			index = binarySearch.binarySearch2(nums, num, midIndex + 1,
					endIndex);
			if (-1 == index)
				return search(nums, num, 0, midIndex - 1);
		}

		return index;

	}

	public static void main(String[] args) {

		CircularSortedArray circularSortedArray = new CircularSortedArray();
		int[] nums = new int[] { 4, 5, 7, 8, 1, 2, 3 };
		System.out.println(circularSortedArray.search(nums, 7));
		System.out.println(circularSortedArray.search(nums, 9));
		System.out.println(circularSortedArray.search(nums, 3));

	}
}
