package home.thread;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class ThreadPool {

	private int poolSize;

	private int activeThreadCount;

	private LinkedList<Runnable> jobQueue;

	private Set<Thread> workers = new HashSet<Thread>(poolSize);

	private static int state;

	private static final int RUN = 0;

	private static final int STOP = 1;

	private class WorkerThread implements Runnable {

		public void run() {
			while (true) {
				try {
					Runnable job = dequeue();
					if (ThreadPool.STOP == ThreadPool.state) {
						Thread.interrupted();
						return;
					}
					job.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void interrupt() {
			Thread.currentThread().interrupt();
		}
	}

	public ThreadPool(int poolSize) {
		this.poolSize = poolSize;
		this.jobQueue = new LinkedList<Runnable>();
		state = RUN;
	}

	public void addJob(Runnable job) {
		enqueue(job);
	}

	public void shutDown() {
		state = STOP;
		for (Thread thread : workers) {
			while (!Thread.State.TERMINATED.equals(thread.getState())) {
			}
		}
		System.out.println("Shutting down!!");
		System.exit(0);
	}

	public void shutDownNow() {

	}

	private void enqueue(Runnable job) {
		synchronized (jobQueue) {
			jobQueue.offer(job);
			if (1 == jobQueue.size())
				jobQueue.notifyAll();
			startWorker();
		}
	}

	private Runnable dequeue() throws InterruptedException {
		synchronized (jobQueue) {
			while (0 == jobQueue.size())
				jobQueue.wait();
			return jobQueue.poll();
		}
	}

	private void startWorker() {
		if (activeThreadCount < poolSize && activeThreadCount < jobQueue.size()) {
			Thread thread = new Thread(new WorkerThread(),
					String.valueOf(activeThreadCount++ + 1));
			workers.add(thread);
			thread.start();
		}
	}

	public static void main(String[] args) {
		ThreadPool threadPool = new ThreadPool(3);

		try {
			threadPool.addJob(new Job(1));
			System.out.println(1 + " added");
			Thread.sleep(10000);

			threadPool.addJob(new Job(2));
			System.out.println(2 + " added");
			Thread.sleep(10000);

			threadPool.addJob(new Job(3));
			System.out.println(3 + " added");
			Thread.sleep(10000);

			threadPool.addJob(new Job(4));
			System.out.println(4 + " added");
			Thread.sleep(10000);

			threadPool.addJob(new Job(5));
			System.out.println(5 + " added");
			Thread.sleep(10000);

			threadPool.addJob(new Job(6));
			System.out.println(6 + " added");
			Thread.sleep(10000);

			threadPool.addJob(new Job(7));
			System.out.println(7 + " added");
			Thread.sleep(10000);
		} catch (Exception e) {
		}

		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		// threadPool.shutDown();
	}

}

class Job implements Runnable {

	private final int data;

	public Job(int data) {
		this.data = data;
	}

	@Override
	public void run() {

		// System.out.println("Processing : " + data + " by Thread "
		// + Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Processed : " + data + " by Thread "
				+ Thread.currentThread().getName());
	}
}
