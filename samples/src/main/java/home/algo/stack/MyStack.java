package home.algo.stack;

public class MyStack {

	private Object[] stack;

	private int top = -1;

	public MyStack(int size) {
		stack = new Object[size];
	}

	public void push(Object object) throws StatckOverflowException {
		if (top == stack.length - 1)
			throw new StatckOverflowException();
		stack[++top] = object;
	}

	public Object pop() {

		if (top == -1)
			throw new RuntimeException("Stack is empty");
		return stack[top--];
	}

}

class StatckOverflowException extends Exception {
	private static final long serialVersionUID = 1583132769637469501L;

}
