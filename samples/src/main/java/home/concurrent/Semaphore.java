package home.concurrent;

public class Semaphore {

	private final int permits;
	private int acquired;

	public Semaphore(int permits) {
		this.permits = permits;
	}

	public synchronized void acquire() throws InterruptedException {

		while (acquired == permits)
			wait();
		acquired++;
		notify();

	}

	public synchronized void release() throws InterruptedException {

		while (acquired == 0)
			wait();
		acquired--;
		notify();
	}

	public static void main(String[] args) {

		Semaphore semaphore = new Semaphore(4);

		for (int i = 0; i < 10; i++) {
			new Thread(new Worker(semaphore, i + 1)).start();
		}

	}
}

class Worker implements Runnable {

	private final Semaphore semaphore;
	private final int no;

	public Worker(Semaphore semaphore, int no) {
		this.semaphore = semaphore;
		this.no = no;
	}

	@Override
	public void run() {

		try {
			semaphore.acquire();
			System.out.println(no + "acquired");

			Thread.sleep(2000);

			System.out.println(no + "released");
			semaphore.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
