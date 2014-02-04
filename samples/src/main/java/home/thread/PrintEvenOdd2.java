package home.thread;

/**
 * @author sahoos2
 * 
 */
public class PrintEvenOdd2 {

	public static void main(String[] args) {

		Number2 number = new Number2(10);

		new Thread(new EvenPrinter(number)).start();
		new Thread(new OddPrinter(number)).start();
	}

}

class Number2 {

	private int value = 1;

	private final int limit;

	public Number2(int limit) {
		this.limit = limit;
	}

	public int getValue() {
		return value;
	}

	public int getLimit() {
		return limit;
	}

	public void increment() {
		value++;
	}

}

class EvenPrinter implements Runnable {

	private final Number2 number;

	public EvenPrinter(Number2 number) {
		this.number = number;
	}

	@Override
	public void run() {
		while (number.getValue() <= number.getLimit()) {
			int value = number.getValue();
			synchronized (number) {
				if (value % 2 != 0)
					try {
						number.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				else {
					System.out.println("EVEN : " + value);
					number.increment();
				}
				number.notify();
			}
		}

	}
}

class OddPrinter implements Runnable {

	private final Number2 number;

	public OddPrinter(Number2 number) {
		this.number = number;
	}

	@Override
	public void run() {
		while (number.getValue() <= number.getLimit()) {
			int value = number.getValue();
			synchronized (number) {
				if (value % 2 == 0)
					try {
						number.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				else {
					System.out.println("ODD : " + value);
					number.increment();
				}
				number.notify();
			}
		}

	}
}
