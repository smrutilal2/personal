package home.generics;

import java.util.ArrayList;
import java.util.List;

public class TestGenerics {

	public static void main(String[] args) {
		List<Animal> animals = new ArrayList<Animal>();
		tester1(animals);
		List<Dog> dogs = new ArrayList<Dog>();
		// tester1(dogs); //NOT ALLOWED

		tester2(dogs);
		tester3(dogs);
		tester4(dogs);
		List<Integer> tester5 = tester5(11);
		System.out.println(tester5);
		List<?> tester5_1 = tester5(11);
		// tester5_1.add(new Object());//NOT ALLOWED
		tester5_1.add(null);// ALLOWED
		Object x = tester6(new ArrayList<Number>(), 13).get(0); // Cannot cast
		// to String
		System.out.println(x);
	}

	private static void tester1(List<Animal> animals) {
		animals.add(new Animal());
		animals.add(new Dog());
	}

	private static void tester2(List<? extends Animal> dogs) {
		dogs.contains(0);
		// dogs.add(new Animal()); //Not allowed
		// dogs.add(new Dog()); //Not allowed
		dogs.add(null); // Allowed
	}

	private static void tester3(List<? super Dog> dogs) {
		dogs.add(new Dog());
		// dogs.add(new Animal()); //Not allowed
	}

	private static void tester4(List<?> dogs) {
		dogs.contains(0);
		// dogs.add(new Dog()); //Not allowed
		dogs.add(null);
	}

	private static <T> List<T> tester5(T t) {
		List<T> list = new ArrayList<T>();
		list.add(t);
		return list;
	}

	private static <T> List<? super T> tester6(List<? super T> list, T t) {
		list.add(t);
		return list;
	}

}
