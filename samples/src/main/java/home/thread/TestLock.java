package home.thread;

import java.util.ArrayList;

/**
 * @author sahoos2
 * 
 */
public class TestLock extends Thread {

	private final String message;

	/**
	 * 
	 */
	public TestLock(String message) {
		this.message = message;
	}

	public void write() {

		System.out.println(message);
		System.out.println(message);
	}

	@Override
	public void run() {
		synchronized (ArrayList.class) {
			write();
		}
	}

	public static void main(String[] args) {

		TestLock testLock = new TestLock("X");
		testLock.start();
		testLock.start();
		new TestLock("Y").start();

	}

}
