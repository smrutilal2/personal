package home.exception;

/**
 * @author smruti
 * 
 */
public class TryFinally {

	public void throwsSpecificException() throws E1 {
		try {
			throw new E1();
		} finally {
			// throw new E2(); // won't compile
		}
	}

	@SuppressWarnings("finally")
	public void throwsException() throws E1, E2 {
		try {
			System.out.println("throw E1");
			throw new E1();
		} finally {
			System.out.println("inside finally");
			throw new E2();
		}
	}

	@SuppressWarnings("finally")
	public int returnSomething() {
		try {
			return 1;
		} finally {
			return 2; // perfectly fine. Doesn't give compilation error
		}
		// return 3; //unreachable
	}

	public static void main(String[] args) {

		TryFinally tryFinally = new TryFinally();

		try {
			tryFinally.throwsException();
		} catch (Exception e) {
			if (e instanceof E1)
				System.out.println("E1 caught");
			if (e instanceof E2)
				System.out.println("E2 caught");
		}

		System.out.println("\n");

		System.out.println(tryFinally.returnSomething());
	}

	public class E1 extends Exception {
		private static final long serialVersionUID = 5545132732631215381L;
	}

	public class E2 extends Exception {
		private static final long serialVersionUID = 5545132732631215381L;
	}

	public class RE1 extends RuntimeException {
		private static final long serialVersionUID = 4504310460579520745L;
	}

	public class RE2 extends RuntimeException {
		private static final long serialVersionUID = 4504310460579520745L;
	}
}
