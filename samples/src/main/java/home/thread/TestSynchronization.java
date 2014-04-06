package home.thread;

public class TestSynchronization {

	public static void main(String[] args) {

		Test test1 = new Test();
		Test test2 = new Test();

		new Thread(new Thread_Object(test1)).start();
		new Thread(new Thread_Object(test2)).start();
		// new Thread(new Thread_Class(test1)).start();
		// new Thread(new Thread_Class(test2)).start();
	}

}

class Thread_Object implements Runnable {

	private final Test test;

	public Thread_Object(Test test) {
		this.test = test;
	}

	@Override
	public void run() {
		test.setCount(10);
	}

}

class Thread_Class implements Runnable {

	private final Test test;

	public Thread_Class(Test test) {
		this.test = test;
	}

	@Override
	public void run() {
		Test.setClassCount(100);
	}

}

class Test {

	private int count;

	private static int classCount;

	public synchronized void setCount(int count) {
		System.out.println("setting count");
		this.count = count;
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("count is set");
	}

	public static synchronized void setClassCount(int classCount) {
		System.out.println("setting class count");
		Test.classCount = classCount;
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("class count is set");
	}

}
