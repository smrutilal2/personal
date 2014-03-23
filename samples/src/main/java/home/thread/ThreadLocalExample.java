package home.thread;

public class ThreadLocalExample implements Runnable {

	public static void main(String[] args) {

		System.out.println(SerialNum.get());
		System.out.println(SerialNum.get());
		SerialNum.set(5);
		System.out.println(SerialNum.get());

		new Thread(new ThreadLocalExample(), "thread-" + 1).start();
		new Thread(new ThreadLocalExample(), "thread-" + 2).start();

		System.out.println("Uninitialized serial number - "
				+ UninitializedSerialNum.get());
		UninitializedSerialNum.set(1);
		System.out.println("Uninitialized serial number now set to "
				+ UninitializedSerialNum.get());
	}

	@Override
	public void run() {

		System.out.println(Thread.currentThread().getName()
				+ "-- has serial number-" + SerialNum.get());

		SerialNum.set(SerialNum.get() + 3);

		System.out.println(Thread.currentThread().getName()
				+ "-- has rest serial number to " + SerialNum.get());
	}

}

class SerialNum {

	private static int nextSerialNum = 0;

	private static ThreadLocal<Integer> serialNum = new ThreadLocal<Integer>() {
		@Override
		protected synchronized Integer initialValue() {
			return new Integer(nextSerialNum++);
		}
	};

	public static Integer get() {
		return serialNum.get();
	}

	public static void set(int value) {
		serialNum.set(value);
	}
}

class UninitializedSerialNum {

	private static ThreadLocal<Integer> serialNum = new ThreadLocal<Integer>();

	public static Integer get() {
		return serialNum.get();
	}

	public static void set(int value) {
		serialNum.set(value);
	}

}
