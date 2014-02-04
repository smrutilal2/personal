package home.algo.misc;

import static java.lang.Math.max;
import static java.lang.Math.min;

//Base cases:
//The smaller array has only one element
//Case 1: N = 1, M = 1.
//Case 2: N = 1, M is odd
//Case 3: N = 1, M is even
//The smaller array has only two elements
//Case 4: N = 2, M = 2
//Case 5: N = 2, M is odd
//Case 6: N = 2, M is even
//
//Case 1: There is only one element in both arrays, so output the average of A[0] and B[0].
//
//Case 2: N = 1, M is odd
//Let B[5] = {5, 10, 12, 15, 20}
//First find the middle element of B[], which is 12 for above array. There are following 4 sub-cases.
//	2.1 If A[0] is smaller than 10, the median is average of 10 and 12.
//	2.2 If A[0] lies between 10 and 12, the median is average of A[0] and 12.
//	2.3 If A[0] lies between 12 and 15, the median is average of 12 and A[0].
//	2.4 If A[0] is greater than 15, the median is average of 12 and 15.
//In all the sub-cases, we find that 12 is fixed. So, we need to find the median of B[ M / 2 - 1 ], B[ M / 2 + 1], A[ 0 ] and take its average with B[ M / 2 ].
//
//Case 3: N = 1, M is even
//Let B[4] = {5, 10, 12, 15}
//First find the middle items in B[], which are 10 and 12 in above example. There are following 3 sub-cases.
//	3.1 If A[0] is smaller than 10, the median is 10.
//	3.2 If A[0] lies between 10 and 12, the median is A[0].
//	3.3 If A[0] is greater than 10, the median is 12.
//So, in this case, find the median of three elements B[ M / 2 - 1 ], B[ M / 2] and A[ 0 ].
//
//Case 4: N = 2, M = 2
//There are four elements in total. So we find the median of 4 elements.
//
//Case 5: N = 2, M is odd
//Let B[5] = {5, 10, 12, 15, 20}
//The median is given by median of following three elements: B[M/2], max(A[0], B[M/2 - 1]), min(A[1], B[M/2 + 1]).
//
//Case 6: N = 2, M is even
//Let B[4] = {5, 10, 12, 15}
//The median is given by median of following four elements: B[M/2], B[M/2 - 1], max(A[0], B[M/2 - 2]), min(A[1], B[M/2 + 1])
//
//Remaining Cases:
//Once we have handled the above base cases, following is the remaining process.
//1) Find the middle item of A[] and middle item of B[].
//	1.1) If the middle item of A[] is greater than middle item of B[], ignore the last half of A[], let length of ignored part is idx. Also, cut down B[] by idx from the start.
//	1.2) else, ignore the first half of A[], let length of ignored part is idx. Also, cut down B[] by idx from the last.

public class MedianOfTwoSortedArrays {

	public int getMedian(int[] array1, int[] array2) {
		if (array1.length <= array2.length)
			return getMedian(array1, 0, array1.length - 1, array2, 0,
					array2.length - 1);
		else
			return getMedian(array2, 0, array2.length - 1, array1, 0,
					array1.length - 1);
	}

	private int getMedian(int[] array1, int start1, int end1, int[] array2,
			int start2, int end2) {

		int length1 = projectedLenght(start1, end1);
		int length2 = projectedLenght(start2, end2);

		if (1 == length1 || 1 == length2) {

			if (1 == length1 && 1 == length2)
				return median(array1[start1], array2[start2]);
			else if (1 == length1) {
				if (0 == length2 % 2)
					return medianOf1AndEven(array1[start1], array2, start2,
							end2);
				else
					return medianOf1AndOdd(array1[start1], array2, start2, end2);

			} else {
				if (0 == length1 % 2)
					return medianOf1AndEven(array2[start2], array1, start1,
							end1);
				else
					return medianOf1AndOdd(array2[start2], array1, start1, end1);
			}
		}

		else if (2 == length1 || 2 == length2) {
			if (2 == length1 && 2 == length2)
				return median(array1[start1], array1[end1], array2[start2],
						array2[end2]);
			else if (2 == length1) {
				if (0 == length2 % 2)
					return medianOf2AndEven(array1, start1, end1, array2,
							start2, end2);
				else
					return medianOf2AndOdd(array1, start1, end1, array2,
							start2, end2);
			} else {
				if (0 == length1 % 2)
					return medianOf2AndEven(array2, start2, end2, array1,
							start1, end1);
				else
					return medianOf2AndOdd(array2, start2, end2, array1,
							start1, end1);
			}
		} else {

			int midIndex1 = (start1 + end1) / 2;
			int midIndex2 = (start2 + end2) / 2;

			int lengthDiscarded = isEven(length1) ? projectedLenght(start1,
					midIndex1) : projectedLenght(start1, midIndex1) - 1;

			if (array1[midIndex1] <= array2[midIndex2]) {
				return getMedian(array1, isEven(length1) ? midIndex1 + 1
						: midIndex1, end1, array2, start2, end2
						- lengthDiscarded);
			} else {
				return getMedian(array1, start1,
						isEven(length1) ? midIndex1 - 1 : midIndex1, array2,
						start2 + lengthDiscarded, end2);
			}

		}

	}

	private boolean isOdd(int number) {
		return !isEven(number);
	}

	private boolean isEven(int number) {
		return 0 == number % 2;
	}

	private int medianOf2AndOdd(int[] smallArray, int first, int second,
			int[] bigArray, int bigStart, int bigEnd) {
		int midIndex = (bigStart + bigEnd) / 2;
		return median(bigArray[midIndex],
				max(smallArray[first], bigArray[midIndex - 1]),
				min(smallArray[second], bigArray[midIndex + 1]));
	}

	private int medianOf2AndEven(int[] smallArray, int first, int second,
			int[] bigArray, int bigStart, int bigEnd) {

		int midIndex = (bigStart + bigEnd) / 2;
		return median(smallArray[first], smallArray[second],
				bigArray[midIndex], bigArray[midIndex + 1]);
	}

	private int medianOf1AndOdd(int n, int[] array, int start, int end) {
		int midIndex = (start + end) / 2;
		int mid1 = array[midIndex];
		int mid2 = median(n, array[midIndex - 1], array[midIndex + 1]);
		return median(mid1, mid2);
	}

	private int medianOf1AndEven(int n, int[] array, int start, int end) {
		return median(n, array[(start + end) / 2],
				array[((start + end) / 2) + 1]);
	}

	private int median(int a, int b, int c, int d) {
		int max = max(a, max(b, max(c, d)));
		int min = min(a, min(b, min(c, d)));
		return (a + b + c + d - max - min) / 2;
	}

	private int median(int x, int y, int z) {
		return x + y + z - max(x, max(y, z)) - min(x, min(y, z));
	}

	private int median(int x, int y) {
		return (x + y) / 2;
	}

	private int projectedLenght(int start, int end) {
		return end - start + 1;
	}

	public static void main(String[] args) {

		MedianOfTwoSortedArrays mosa = new MedianOfTwoSortedArrays();

		System.out.println(mosa.getMedian(new int[] { 3, 19, 19, 202, 301 },
				new int[] { 2, 7, 31, 39, 104, 205, 290, 305, 308, 400 }));

		System.out.println(mosa.getMedian(new int[] { 2, 7, 31, 39, 104, 205,
				290, 305, 308, 400 }, new int[] { 3, 19, 19, 202, 301 }));

		System.out.println(mosa.getMedian(new int[] { 5, 104, 109, 301, 400 },
				new int[] { 99, 97, 205, 350, 375 }));

		System.out.println(mosa.getMedian(new int[] { 5, 104, 109, 301, 400,
				450 }, new int[] { 99, 97, 205, 350, 375, 380 }));

		System.out.println(mosa.findMedianSortedArrays(new int[] { 3, 19, 19,
				202, 301 }, new int[] { 2, 7, 31, 39, 104, 205, 290, 305, 308,
				400 }));

		System.out.println(mosa.findMedianSortedArrays(new int[] { 2, 7, 31,
				39, 104, 205, 290, 305, 308, 400 }, new int[] { 3, 19, 19, 202,
				301 }));

		System.out.println(mosa.findMedianSortedArrays(new int[] { 5, 104, 109,
				301, 400 }, new int[] { 99, 97, 205, 350, 375 }));

		System.out.println(mosa.findMedianSortedArrays(new int[] { 5, 104, 109,
				301, 400, 450 }, new int[] { 99, 97, 205, 350, 375, 380 }));
	}

	double findMedian(int A[], int B[], int aStartIndex, int aEndIndex,
			int aLength, int bLength) {

		if (aStartIndex > aEndIndex)
			return findMedian(B, A, max(0, (aLength + bLength) / 2 - aLength),
					min(bLength, (aLength + bLength) / 2), bLength, aLength);

		int i = (aStartIndex + aEndIndex) / 2;
		int j = (aLength + bLength) / 2 - i - 1;
		if (j >= 0 && A[i] < B[j])
			return findMedian(A, B, i + 1, aEndIndex, aLength, bLength);
		else if (j < bLength - 1 && A[i] > B[j + 1])
			return findMedian(A, B, aStartIndex, i - 1, aLength, bLength);
		else {
			if ((aLength + bLength) % 2 == 1)
				return A[i];
			else if (i > 0)
				return (A[i] + Math.max(B[j], A[i - 1])) / 2.0;
			else
				return (A[i] + B[j]) / 2.0;
		}
	}

	double findMedianSortedArrays(int A[], int aLength, int B[], int bLength) {
		if (aLength < bLength)
			return findMedian(A, B, 0, aLength - 1, aLength, bLength);
		else
			return findMedian(B, A, 0, bLength - 1, bLength, aLength);
	}

	double findMedianSortedArrays(int A[], int B[]) {
		return findMedianSortedArrays(A, A.length, B, B.length);
	}

}
