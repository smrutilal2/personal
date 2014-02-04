package home.concurrent;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

	private static class ProcessingThread_Type1 implements Runnable {

		private String name;
		private CountDownLatch countDownLatch;
		private long duration;

		public ProcessingThread_Type1(String name,
				CountDownLatch countDownLatch, long duration) {
			this.name = name;
			this.countDownLatch = countDownLatch;
			this.duration = duration;
		}

		@Override
		public void run() {

			System.out.println(name + " working for " + (duration / 1000)
					+ " seconds");
			try {
				Thread.sleep(duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(name + " is done");
			countDownLatch.countDown();
		}
	}

	private static class ProcessingThread_Type2 implements Runnable {

		private String name;
		private CountDownLatch countDownLatch;
		private int count;

		public ProcessingThread_Type2(String name,
				CountDownLatch countDownLatch, int count) {
			this.name = name;
			this.countDownLatch = countDownLatch;
			this.count = count;
		}

		@Override
		public void run() {

			for (int i = 0; i < count; i++) {

				System.out.println(name + " working on service " + (i + 1));
				try {
					Thread.sleep(i * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			}
			System.out.println(name + " is done");
		}
	}

	public static void main(String[] args) {

		CountDownLatch countDownLatch1 = new CountDownLatch(3);
		Thread thread1 = new Thread(new ProcessingThread_Type1("Worker1",
				countDownLatch1, 2000));
		Thread thread2 = new Thread(new ProcessingThread_Type1("Worker2",
				countDownLatch1, 4000));
		Thread thread3 = new Thread(new ProcessingThread_Type1("Worker3",
				countDownLatch1, 6000));

		thread1.start();
		thread2.start();
		thread3.start();

		System.out
				.println("Parent thread waiting for Children processes to complete....");

		try {
			countDownLatch1.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Parent thread resumes");

		CountDownLatch countDownLatch2 = new CountDownLatch(3);
		Thread thread4 = new Thread(new ProcessingThread_Type2("Worker4 ",
				countDownLatch2, 3));
		thread4.start();

		System.out
				.println("Parent thread waiting for child thread to complete");
		try {
			countDownLatch2.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Parent resumes agian");
	}

}
