package home.collection;

import java.util.HashSet;
import java.util.Set;

public class TestHashSet {

	public static void main(String[] args) {
		Set<Object> set = new HashSet<Object>(2);

		set.add(new Employee_Equals("Smruti"));
		set.add(new Employee_Equals("Smruti"));
		System.out.println(set.size()); // size is 2. As hashCode() is not
										// implemented, it gives strange
										// behavior by adding to equal objects
										// in a set.

		set.clear();
		set.add(new Employee_HashCode("Smruti"));
		set.add(new Employee_HashCode("Smruti"));
		System.out.println(set.size()); // size is 2. As equals() is not
										// implemented, it gives strange
										// behavior by adding to equal objects
										// in a set.

		set.clear();
		set.add(new Employee("Smruti"));
		set.add(new Employee("Smruti")); // doesn't add as equals() and
											// hashCode() is implemented
											// properly.
		System.out.println(set.size()); // size is 1.

		set.clear();
		Employee_Diff_ToString et1 = new Employee_Diff_ToString("Smruti");
		Employee_Diff_ToString et2 = new Employee_Diff_ToString("Smruti");
		System.out.println(et1);
		System.out.println(et2);
		set.add(et1);
		set.add(et2);
		System.out.println(set.iterator().next()); // prints the toString() for
													// et1. Set doesn't override
													// the item if added twice.

	}

}
