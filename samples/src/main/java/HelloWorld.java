public class HelloWorld {

	public static void main(String[] args) {

		// System.out.println("Hello!!");

		// TestEven even = new TestEven();

		// System.out.println(even.isOdd(30068));

		Calculator calculator = new Calculator();
		System.out.println(calculator.add(2, 13));

		Integer i = new Integer(127);
		Integer j = new Integer(127);
		System.out.println(i == j);

		Integer x = 127;
		Integer y = 127;
		System.out.println(x == y);

		x = 1211;
		y = 1211;
		System.out.println(x == y);

		byte b = 5;
		short s = 5;
		long l = 5;
		float f = 5.0f;

		go(b);
		go(s);
		go(l);
		go(f);
	}

	// static void go(short x) {
	// System.out.print("short");
	// }
	//
	// static void go(int x) {
	// System.out.print("int");
	// }
	//
	// static void go(long x) {
	// System.out.print("long");
	// }

	static void go(double x) {
		System.out.print("double");
	}

}

class TestEven {

	public boolean isOdd(int number) {
		return 0 != number / 2;
	}
}
