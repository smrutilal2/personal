package home.thread;

/**
 * @author sahoos2
 * 
 */
public class PrintEvenOdd {

	public static void main(String[] args) {

		Number number = new Number(10);
		new Thread(new NumberPrinter(number), "EVEN-PRINTER").start();
		new Thread(new NumberPrinter(number), "ODD-PRINTER").start();
	}

}

class NumberPrinter implements Runnable {

	private final Number number;

	public NumberPrinter(Number number) {
		this.number = number;
	}

	@Override
	public void run() {
		while (number.getValue() <= number.getLimit()) {
			number.print();
		}
	}

}

class Number {

	private int limit;

	private int value = 1;

	private boolean isEven;

	public Number(int limit) {
		this.limit = limit;
	}

	public int getValue() {
		return value;
	}

	public int getLimit() {
		return limit;
	}

	public synchronized void print() {
		String name = Thread.currentThread().getName();
		while (name.equals("ODD-PRINTER") && isEven) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		while (name.equals("EVEN-PRINTER") && !isEven) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		if (value <= limit) {
			System.out.println(name + ":" + value++);
			isEven = !isEven;
		}
		notify();
	}

}
