/**
 * 
 */
package home.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author smruti
 * 
 */
public class ProducerConsumerUsingLock {

	public static void main(String[] args) {

		ProducerConsumerUsingLock consumerUsingLock = new ProducerConsumerUsingLock();
		SharedData sharedData = consumerUsingLock.new SharedData();

		Thread producer = new Thread(consumerUsingLock.new Producer(sharedData));

		Thread consumer = new Thread(consumerUsingLock.new Consumer(sharedData));

		producer.start();
		consumer.start();

	}

	class SharedData {
		private int data;
		private boolean isProduced;

		private Lock lock = new ReentrantLock();
		private Condition producedState = lock.newCondition();
		private Condition consumedState = lock.newCondition();

		public void produce(int data) {

			lock.lock();

			while (isProduced) {
				try {
					producedState.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println("Poducing: " + data);
			this.data = data;
			isProduced = true;
			consumedState.signal();

			lock.unlock();
		}

		public int consume() {

			lock.lock();

			while (!isProduced) {
				try {
					consumedState.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println("Consuming: " + data);
			isProduced = false;
			producedState.signal();

			lock.unlock();

			return data;
		}

	}

	class Producer implements Runnable {

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

	class Consumer implements Runnable {

		private SharedData sharedData;

		public Consumer(SharedData sharedData) {
			this.sharedData = sharedData;
		}

		@Override
		public void run() {

			for (int i = 1; i <= 10; i++) {
				sharedData.consume();
			}
		}

	}
}
