package home.thread;

public class DeadLock {

	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		new Thread(new WorkerA(a), "AAA").start();
		new Thread(new WorkerB(b), "BBB").start();
	}
}

class WorkerA implements Runnable {

	private final A a;

	public WorkerA(A a) {
		this.a = a;
	}

	@Override
	public void run() {
		try {
			a.method(new B());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class WorkerB implements Runnable {

	private final B b;

	public WorkerB(B b) {
		this.b = b;
	}

	@Override
	public void run() {
		try {
			b.method(new A());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class A {
	public synchronized void method(B b) throws InterruptedException {
		Thread.sleep(5000);
		b.method(new A());

	}
}

class B {
	public synchronized void method(A a) throws InterruptedException {
		Thread.sleep(5000);
		a.method(new B());
	}
}
