package home.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestHashMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// EmployeeID id = new EmployeeID("1");
		// Employee e1 = new Employee("Smruti");
		//
		// Map<EmployeeID, Employee> map = new HashMap<EmployeeID, Employee>(1);
		// map.put(id, e1);

		// EmployeeID id1 = new EmployeeID("1");
		// System.out.println(map.get(id1));
		//
		// System.out.println("********************************");
		//
		// Employee employee = new Employee("Smruti");
		//
		// EmployeeID_Weird weird1 = new EmployeeID_Weird("1");
		// EmployeeID_Weird weird2 = new EmployeeID_Weird("2");
		//
		// Map<EmployeeID_Weird, Employee> m = new HashMap<EmployeeID_Weird,
		// Employee>(
		// 2);
		// m.put(weird1, employee);
		// m.put(weird2, employee);
		//
		// System.out.println(m.get(new EmployeeID_Weird("1")));

		Map<Person, String> map = new HashMap<Person, String>(10);

		Person p1 = new Person("smruti");
		Person p2 = new Person("smruti");

		// // map.put(p1, p1.getName());
		//
		// // System.out.println(map.size());
		// // System.out.println(map.get(p2));

		Set<Person> set = new HashSet<Person>(10);
		set.add(p1);
		set.add(p2);

		System.out.println(set.size());

	}
}

class Person {
	private String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	// @Override
	// public boolean equals(Object obj) {
	// return name.equals(((Person) obj).name);
	// }
	//
	// @Override
	// public int hashCode() {
	// return name.hashCode();
	// }

}
