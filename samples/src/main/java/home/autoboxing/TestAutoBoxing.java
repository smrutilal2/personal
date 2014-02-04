package home.autoboxing;

import java.util.HashMap;
import java.util.Map;

public class TestAutoBoxing {

	public static void main(String[] args) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(new String("hi"), 3);

		Integer value = map.get("hello");
		// int value = map.get("hello"); throws null pointer exception
		System.out.println(value);

		Parent parent = new Parent();
		Child child = new Child();
		parent.add(2, 3);

		child.add(2d, 3d);

	}
}

class Parent {
	public void add(int x, int y) {
		System.out.println("parent add");
	}
}

class Child extends Parent {
	// @Override
	public void add(Double x, Double y) {
		System.out.println("child add");
	}

}
