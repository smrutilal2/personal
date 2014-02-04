package home.algo.misc;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestMedianOfTwoSortedArrays {

	private MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();

	@Test
	public void testUseCase_1() {
		int[] A = new int[] { 1, 2, 3 };
		int[] B = new int[] { 4, 5, 6 };
		double median = medianOfTwoSortedArrays.getMedian(A, B);
		System.out.println("Median is:" + median);
		Assert.assertEquals(3.5, median);
	}

	@Test
	public void testUseCase_2() {
		int[] A = new int[] { 1, 7, 9 };
		int[] B = new int[] { 2, 3, 4 };
		double median = medianOfTwoSortedArrays.getMedian(A, B);
		System.out.println("Median is:" + median);
		Assert.assertEquals(3.5, median);
	}

	@Test
	public void testUseCase_3() {
		int[] A = new int[] { 1, 1, 2, 4 };
		int[] B = new int[] { 1, 3, 5 };
		double median = medianOfTwoSortedArrays.getMedian(A, B);
		System.out.println("Median is:" + median);
		Assert.assertEquals(2.0, median);
	}

	@Test
	public void testUseCase_4() {
		int[] A = new int[] { 1, 7, 9 };
		int[] B = new int[] { 7, 8, 9 };
		double median = medianOfTwoSortedArrays.getMedian(A, B);
		System.out.println("Median is:" + median);
		Assert.assertEquals(7.5, median);
	}

	@Test
	public void testUseCase_5() {
		int[] A = new int[] { 1, 3, 9 };
		int[] B = new int[] { 1, 1, 2, 7, 8 };
		double median = medianOfTwoSortedArrays.getMedian(A, B);
		System.out.println("Median is:" + median);
		Assert.assertEquals(2.5, median);
	}

	@Test
	public void testUseCase_6() {
		int[] A = new int[] { 11, 38, 43, 50, 63, 69 };
		int[] B = new int[] { 13, 17, 21, 23, 29, 51, 57 };
		double median = medianOfTwoSortedArrays.getMedian(A, B);
		System.out.println("Median is:" + median);
		Assert.assertEquals(38.0, median);
	}
}
