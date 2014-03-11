package home.algo.dp;

public class MaxSubArraySum {

	public int maxSubArraySum(int[] array) {
		int maxSum = array[0], currentWindow = array[0];
		for (int i = 1; i < array.length; i++) {
			currentWindow = Math.max(array[i], currentWindow + array[i]);
			maxSum = Math.max(maxSum, currentWindow);
		}
		return maxSum;
	}

	public static void main(String[] args) {
		MaxSubArraySum maxSubArraySum = new MaxSubArraySum();

		System.out.println(maxSubArraySum.maxSubArraySum(new int[] { -2, -5, 6,
				-2, -3, 1, 5, -6 }));
		System.out.println(maxSubArraySum.maxSubArraySum(new int[] { -2, -5,
				-6, -2, -3, -1, -5, -6 }));
		System.out.println(maxSubArraySum.maxSubArraySum(new int[] { 2, 5, 6,
				2, 3, 1, 5, 6 }));

	}

}
