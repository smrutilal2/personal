package home.thread;


public class ProducerConsumer {

	public static void main(String[] args) {

		SharedData sharedData = new SharedData();
		new Producer(sharedData);
		new Consumer(sharedData);
	}
}

class SharedData {
	private int data;
	private boolean isProduced;

	public synchronized void produce(int data) {

		if (isProduced) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Producing : " + data);
		this.data = data;
		isProduced = true;
		notify();
	}

	public synchronized int consume() {
		if (!isProduced) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Consuming : " + data);
		isProduced = false;
		notify();
		return data;
	}
}

class Producer extends Thread {

	private SharedData sharedData;

	public Producer(SharedData sharedData) {
		this.sharedData = sharedData;
		start();
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			sharedData.produce(i + 1);
		}
	}
}

class Consumer extends Thread {

	private final SharedData sharedData;

	public Consumer(SharedData sharedData) {
		this.sharedData = sharedData;
		start();
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			sharedData.consume();
		}
	}

}
