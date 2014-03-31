package home.algo.linkedlist;

import home.algo.Util;

/**
 * @author smruti
 * 
 */
public class LinkedList {

	/**
	 * Reverses a doubly linked list.
	 * 
	 * @param head
	 *            of the original linked list
	 * @return head of the reversed linked list
	 */
	public DoublyNode reverse(DoublyNode head) {

		while (null != head) {
			DoublyNode next = head.getNext();
			head.setNext(head.getPrevious());
			head.setPrevious(next);
			if (null == next)
				break;
			head = next;
		}
		return head;
	}

	public DoublyNode recursiveReverse(DoublyNode head) {

		if (null == head || null == head.getNext())
			return head;

		DoublyNode next = head.getNext();
		head.setNext(null);

		DoublyNode reversed = recursiveReverse(next);

		next.setNext(head);
		head.setPrevious(next);

		return reversed;
	}

	/**
	 * Reverses a singly linked list
	 * 
	 * @param head
	 *            node of the original linked list.
	 * @return head of the reversed liked list.
	 */
	public Node reverse(Node head) {

		Node newHead = null;
		while (null != head) {
			Node tmp = head.getNext();
			head.setNext(newHead);
			newHead = head;
			head = tmp;
		}
		return newHead;
	}

	public Node reverseCopy(Node head) {
		Node newHead = null;
		while (null != head) {
			Node newNext = new Node(head.getData());
			newNext.setNext(newHead);
			newHead = newNext;
			head = head.getNext();
		}
		return newHead;
	}

	public Node recursiveReverse(Node head) {

		if (null == head || null == head.getNext())
			return head;

		Node next = head.getNext();
		head.setNext(null);

		Node reversed = recursiveReverse(next);
		next.setNext(head);
		return reversed;
	}

	public Node nthFromLast(Node head, int n) {

		Node p1 = head;

		for (int i = 0; i < n; i++) {
			p1 = p1.getNext();
		}

		Node p2 = head;

		while (null != p1) {
			p1 = p1.getNext();
			p2 = p2.getNext();
		}
		return p2;
	}

	public boolean isPalindrome(Node head) {
		return false;
	}

	public static void main(String[] args) {

		LinkedList linkedList = new LinkedList();

		System.out.println("*********Reverse doubly linkedlist*************");
		DoublyNode dHead = Util.createDoublyLinkedList(5);
		Util.traverseLinkedList(dHead);
		Util.traverseLinkedList(linkedList.reverse(dHead));
		System.out.println("**********************\n");

		System.out
				.println("**********Recursive reverse doubly linkedlist************");
		dHead = Util.createDoublyLinkedList(7);
		Util.traverseLinkedList(dHead);
		Util.traverseLinkedList(linkedList.recursiveReverse(dHead));
		System.out.println("**********************\n");

		System.out.println("**********************");
		Node head = Util.createLinkedList(10);
		Util.traverseLinkedList(head);
		System.out
				.println("**********Reverse linkedlist. Original modified************");
		Node newHead = linkedList.reverse(head);
		Util.traverseLinkedList(head);
		Util.traverseLinkedList(newHead);
		System.out.println("**********************\n");

		System.out.println("**********************");
		head = Util.createLinkedList(10);
		Util.traverseLinkedList(head);
		System.out
				.println("*********Reverse linkedlist. Original is not modified*************");
		newHead = linkedList.reverseCopy(head);
		Util.traverseLinkedList(head);
		Util.traverseLinkedList(newHead);
		System.out.println("**********************\n");

		System.out
				.println("**********Recursive reverse linkedlist************");
		head = Util.createLinkedList(12);
		Util.traverseLinkedList(head);
		Util.traverseLinkedList(linkedList.recursiveReverse(head));
		System.out.println("**********************\n");

		System.out.println("********* 4th from last *************");
		head = Util.createLinkedList(10);
		Util.traverseLinkedList(head);
		System.out.println(linkedList.nthFromLast(head, 4));
		System.out.println("**********************\n");

		System.out.println("**********isPalindrom************");
		head = Util.createLinkedList(new int[] { 1, 2, 3, 2, 1 });
		Util.traverseLinkedList(head);
		System.out.println(linkedList.isPalindrome(head));
		System.out.println("**********************\n");
	}

}
