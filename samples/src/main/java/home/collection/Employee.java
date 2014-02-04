package home.collection;

class Employee {
	public String name;

	public Employee(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(((Employee) obj).name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}

class Employee_HashCode {
	public String name;

	public Employee_HashCode(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return name;
	}

}

class Employee_Equals {
	public String name;

	public Employee_Equals(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(((Employee) obj).name);
	}
}

class Employee_Diff_ToString {
	public static int count;

	public String name;

	private int serialNo;

	public Employee_Diff_ToString(String name) {
		this.name = name;
		serialNo = ++count;
	}

	@Override
	public String toString() {
		return name + serialNo;
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(((Employee_Diff_ToString) obj).name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}

class Employee_Comparable implements Comparable<Employee_Comparable> {
	public String name;

	public Employee_Comparable(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(((Employee_Comparable) obj).name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public int compareTo(Employee_Comparable o) {
		return name.compareTo(o.name);
	}
}