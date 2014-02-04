package home.algo.stack;

import java.util.Stack;

/**
 * @author sahoos2
 * 
 */
public class MinStack {

	private Stack<Integer> stack = new Stack<Integer>();
	private Stack<Integer> minStack = new Stack<Integer>();

	public void push(int value) {
		stack.push(value);
		if (minStack.empty())
			minStack.push(value);
		else {
			if (value < minStack.peek()) {
				minStack.pop();
				minStack.push(value);
			}
		}
	}

	public int pop() {
		Integer value = stack.pop();
		if (value == minStack.peek())
			minStack.pop();
		return value;
	}

	public int getMin() {
		return minStack.peek();
	}

	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(3);
		System.out.println(minStack.getMin());
		minStack.push(5);
		System.out.println(minStack.getMin());
		minStack.push(1);
		System.out.println(minStack.getMin());
	}

}
