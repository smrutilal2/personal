package home.generics;

import java.util.Arrays;
import java.util.List;

public class TestWildCard {

	public static void main(String[] args) {
		Integer inums[] = { 1, 2, 3, 4, 5 };
		Double fnums[] = { 1.1, 2.6, 3.2, 4.4, 5.7 };
		Number nums[] = { 1.1, 2.6, 3.2, 4.4, 5.7 };
		WildCard<? extends Number> wc = new WildCard<Integer>(inums);
		System.out.println(wc.sum());
		wc = new WildCard<Double>(fnums);
		System.out.println(wc.sum());

		WildCard<Integer> wc1 = new WildCard<Integer>();
		System.out.println(wc1.add(Arrays.asList(inums)));
		WildCard<Double> wc2 = new WildCard<Double>();
		System.out.println(wc2.add(Arrays.asList(nums)));

		WCard<?> wc3 = new WCard<String>("abc");
		Object take = wc3.take();
		// String take1 = wc3.take(); //NOT ALLOWED
		System.out.println(take);

		WCard<String> wc4 = new WCard<String>("abc");
		String take2 = wc4.take();
		System.out.println(take2);
	}

}

class WildCard<T extends Number> {

	private T[] inums;

	public WildCard() {
	}

	public WildCard(T[] inums) {
		this.inums = inums;
	}

	public double sum() {
		double sum = 0.0;
		for (T num : inums) {
			sum += num.doubleValue();
		}
		return sum;
	}

	public double add(List<? super Integer> inums) {
		double sum = 0.0;
		for (Object number : inums) {
			sum += ((Number) number).doubleValue();
		}
		return sum;
	}
}

class WCard<T> {
	private T t;

	public WCard(T t) {
		this.t = t;
	}

	public void put(T t) {
		this.t = t;
	}

	public T take() {
		return t;
	}

	public <L> void makeList(L t) {

	}
}