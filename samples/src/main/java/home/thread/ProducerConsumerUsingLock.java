/**
 * 
 */
package home.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author smruti
 * 
 */
public class ProducerConsumerUsingLock {

	public static void main(String[] args) {
		SharedData sharedData = new ProducerConsumerUsingLock.SharedData();

		Thread producer = new Thread(new ProducerConsumerUsingLock.Producer(
				sharedData));

		Thread consumer = new Thread(new ProducerConsumerUsingLock.Consumer(
				sharedData));

		producer.start();
		consumer.start();

	}

	static class SharedData {
		private int data;
		private boolean isProduced;

		private Lock lock = new ReentrantLock();

		public void produce(int data) {

			lock.lock();

			while (isProduced) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println("Poducing data: " + data);
			this.data = data;
			isProduced = true;
			notify();

			lock.unlock();
		}

		public int consume() {

			lock.lock();

			while (!isProduced) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println("Consuming: " + data);
			isProduced = false;
			notify();

			lock.unlock();

			return data;
		}

	}

	static class Producer implements Runnable {

		private SharedData sharedData;

		public Producer(SharedData sharedData) {
			this.sharedData = sharedData;
		}

		@Override
		public void run() {

			for (int i = 1; i <= 10; i++) {
				sharedData.produce(i);
			}

		}

	}

	static class Consumer implements Runnable {

		private SharedData sharedData;

		public Consumer(SharedData sharedData) {
			this.sharedData = sharedData;
		}

		@Override
		public void run() {

			for (int i = 1; i < 10; i++) {
				sharedData.consume();
			}

		}

	}
}
