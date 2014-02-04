package home.algo.linkedlist;

/**
 * @author sahoos2
 * 
 */
public class DoublyNode extends Node {

	private DoublyNode previous;

	public DoublyNode(int data) {
		super(data);
	}

	public DoublyNode getNext() {
		return (DoublyNode) super.getNext();
	}

	public DoublyNode getPrevious() {
		return previous;
	}

	public void setPrevious(DoublyNode previous) {
		this.previous = previous;
	}
}
