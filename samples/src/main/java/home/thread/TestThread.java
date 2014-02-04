package home.thread;

public class TestThread {

	public static void main(String[] args) {
		Mythread thread = new Mythread();
		thread.start();

		System.out.println("Main executed");
	}

}

class Mythread extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Thread executed");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
