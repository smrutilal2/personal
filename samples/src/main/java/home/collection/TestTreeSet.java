package home.collection;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Conclusion : For TreeSet compareTo() shadows the equals() method. Whether
 * object will be added to the set is decided by the result of compareTo()
 * method rather than equals() method. If compareTo() returns 0, it doesn't add
 * the object irrespective of the result of the equals() method. If compareTo()
 * returns any thing other than 0, then the object is added to the set
 * irrespective of the result of the equals() method. So, for the TreeSet to
 * work properly it is strongly recommended (though not required) that natural
 * orderings be consistent with equals. The natural ordering for a class
 * <tt>C</tt> is said to be <i>consistent with equals</i> if and only if
 * <tt>(e1.compareTo((Object)e2) == 0)</tt> has the same boolean value as
 * <tt>e1.equals((Object)e2)</tt> for every <tt>e1</tt> and <tt>e2</tt> of class
 * <tt>C</tt>. Note that <tt>null</tt> is not an instance of any class, and
 * <tt>e.compareTo(null)</tt> should throw a <tt>NullPointerException</tt> even
 * though <tt>e.equals(null)</tt> returns <tt>false</tt>.
 * <p>
 */
public class TestTreeSet {

	public static void main(String[] args) {

		Set<Employee> set = new TreeSet<Employee>();

		Employee e1 = new Employee("Smruti");
		set.add(e1);
		/* set.add(new Employee("Smruti1")); */// throws ClassCastException as
												// the Employee class doesn't
												// implement Comparable
												// interface.

		Employee_Comparable ec1 = new Employee_Comparable("Smruti");
		Employee_Comparable ec2 = new Employee_Comparable("Smruti");
		Employee_Comparable ec3 = new Employee_Comparable("Smruti1");
		Set<Employee_Comparable> s = new TreeSet<Employee_Comparable>();
		System.out.println(s.add(ec1));
		System.out.println(s.add(ec2));
		System.out.println(s.add(ec3));
		System.out.println(s.size());

		Employee_Bad_Comparable eb1 = new Employee_Bad_Comparable("Smruti");
		Employee_Bad_Comparable eb2 = new Employee_Bad_Comparable("Smruti1");

		Set<Employee_Bad_Comparable> s1 = new TreeSet<Employee_Bad_Comparable>();
		System.out.println(s1.add(eb1));
		System.out.println(s1.add(eb2)); // as the class violates the comparable
											// contract
											// (!eb1.equals((Object)eb2) &&
											// a.compareTo((Object)b) == 0), it
											// won't add it to the set.

		System.out.println(s1.size()); // prints 1.

		eb1 = new Employee_Bad_Comparable("Smruti") {

			@Override
			public int compareTo(Employee_Bad_Comparable arg0) {
				return 1;
			}
		};

		eb2 = new Employee_Bad_Comparable("Smruti") {

			@Override
			public int compareTo(Employee_Bad_Comparable arg0) {
				return -1;
			}
		};
		s1.clear();
		System.out.println(s1.add(eb1));
		System.out.println(s1.add(eb2)); // as the class violates the comparable
											// contract(a.equals((Object)b) &&
											// a.compareTo((Object)b) != 0), it
											// adds the same object twice to the
											// set.

		System.out.println(s1.size()); // prints 2.

		Set<Employee> empSet = new TreeSet<Employee>(
				new Comparator<Employee>() {

					@Override
					public int compare(Employee e1, Employee e2) {
						return e1.name.compareTo(e2.name);
					}
				});

		empSet.add(new Employee("smruit"));
		empSet.add(new Employee("chhaya"));

		for (Employee employee : empSet) {
			System.out.println(employee);
		}

	}

}

class Employee_Bad_Comparable implements Comparable<Employee_Bad_Comparable> {
	public String name;

	public Employee_Bad_Comparable(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(((Employee_Bad_Comparable) obj).name);
	}

	@Override
	public int compareTo(Employee_Bad_Comparable o) {
		return 0;
	}
}