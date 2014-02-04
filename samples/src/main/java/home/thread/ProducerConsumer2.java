package home.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an example of how crapy code you can write for consumer/producer
 * model. Wrong design doesn't work.
 * 
 */
public class ProducerConsumer2 {

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<Integer>();

		new Producer2(list);
		new Consumer2(list);

	}
}

class Producer2 extends Thread {

	private List<Integer> list;
	private boolean isProduced;

	public Producer2(List<Integer> list) {
		this.list = list;
		start();
	}

	@Override
	public void run() {
		synchronized (list) {
			for (int i = 1; i <= 10; i++) {
				if (isProduced)
					try {
						list.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				System.out.println("Producing : " + i);
				list.add(i);
				isProduced = true;
				list.notify();
			}
		}
	}
}

class Consumer2 extends Thread {

	private List<Integer> list;

	private boolean isProduced;

	public Consumer2(List<Integer> list) {
		this.list = list;
		start();
	}

	@Override
	public void run() {
		synchronized (list) {
			for (int i = 1; i <= 10; i++) {
				// if (list.size() == 0)
				if (!isProduced)
					try {
						list.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				System.out.println("Consuming : " + list.get(list.size() - 1));
				isProduced = true;
				list.notify();
			}

		}
	}
}
