package home.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestGenericsVsArray {

	public static void main(String[] args) {
		// testArray();
		testList();
	}

	private static void testList() {
		List<Cat> cats = Arrays.asList(new Cat(), new Cat());
		// addAnimals(cats); //NOT ALLOWED
		List<Animal> animals = new ArrayList<Animal>();
		animals.add(new Cat());
		animals = addAnimals(animals);
		System.out.println(animals);
	}

	private static List<Animal> addAnimals(List<Animal> animals) {
		animals.add(new Dog());
		return animals;
	}

	private static void testArray() {
		Cat[] cats = { new Cat(), new Cat() };
		Animal[] animals = addAnimals(cats);
		for (int i = 0; i < animals.length; i++) {
			System.out.println(animals[i]);
		}
	}

	private static Animal[] addAnimals(Animal[] animals) {
		animals[0] = new Dog();
		return animals;
	}

}
