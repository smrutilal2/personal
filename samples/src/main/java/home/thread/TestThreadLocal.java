package home.thread;

public class TestThreadLocal implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}

class SerialNum {
	private static int nextSerialNum = 0;

	private static ThreadLocal<Integer> serialNum = new ThreadLocal<Integer>() {
		@Override
		protected synchronized Integer initialValue() {
			return new Integer(nextSerialNum++);
		}
	};

	public static int get() {
		return serialNum.get();
	}
}
