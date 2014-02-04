package home.algo;

import home.algo.linkedlist.DoublyNode;
import home.algo.linkedlist.Node;

import java.util.Collection;

public class Util {

	public static void print(Collection<? extends Number> numbers) {
		StringBuilder s = new StringBuilder();
		for (Number number : numbers)
			s.append(number + ", ");

		s.deleteCharAt(s.lastIndexOf(", "));
		System.out.println(s);

	}

	public static void print(int... array) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < array.length; i++)
			s.append(array[i] + ", ");
		s.deleteCharAt(s.lastIndexOf(", "));
		System.out.println(s);
	}

	public static void print(char... array) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < array.length; i++)
			s.append(array[i] + ", ");
		s.deleteCharAt(s.lastIndexOf(", "));
		System.out.println(s);
	}

	public static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static void swap(char[] array, int i, int j) {
		char tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static void anotherSwap(int[] array, int i, int j) {
		array[i] = array[i] + array[j];
		array[j] = array[i] - array[j];
		array[i] = array[i] - array[j];
	}

	public static int[] rest(int[] list) {
		if (list.length == 0)
			return list;
		else {
			int[] rest = new int[list.length - 1];
			System.arraycopy(list, 1, rest, 0, rest.length);
			return rest;
		}
	}

	public static DoublyNode createDoublyLinkedList(int numer) {

		DoublyNode head = new DoublyNode(1);
		DoublyNode start = head;

		for (int i = 2; i <= numer; i++) {

			DoublyNode node = new DoublyNode(i);
			start.setNext(node);
			node.setPrevious(start);
			start = node;
		}
		return head;
	}

	public static Node createLinkedList(int numer) {

		Node head = new Node(1);
		Node start = head;

		for (int i = 2; i <= numer; i++) {

			Node node = new Node(i);
			start.setNext(node);
			start = node;
		}
		return head;
	}

	public static Node createLinkedList(int... numer) {

		Node head = new Node(numer[0]);
		Node start = head;

		for (int i = 1; i < numer.length; i++) {

			Node node = new Node(numer[i]);
			start.setNext(node);
			start = node;
		}
		return head;
	}

	public static void traverseLinkedList(Node node) {

		StringBuilder linkList = new StringBuilder();
		while (null != node) {
			linkList.append(node.getData() + "->");
			node = node.getNext();
		}
		if (linkList.length() > 0)
			linkList.delete(linkList.lastIndexOf("->"), linkList.length());
		System.out.println(linkList);
	}

}
