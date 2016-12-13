package com.github.lyt.dsnalgo.datastructure;

import lombok.Getter;

import com.github.lyt.dsnalgo.domain.DoublyLinkedListNode;

@Getter
public class DoublyLinkedList {

	private DoublyLinkedListNode head;
	private DoublyLinkedListNode tail;
	private int size;

	public DoublyLinkedList(int[] array) {
		for (int x : array) {
			this.add(x);
		}
	}

	public void add(int value) {
		if (null == tail) {
			head = new DoublyLinkedListNode(value);
			tail = head;
			size = 1;
			return;
		}

		DoublyLinkedListNode newNode = new DoublyLinkedListNode(value);
		newNode.setPrevious(tail);
		tail.setNext(newNode);
		tail = newNode;
		size++;
	}

	public void remove() {

	}

	public static void print(DoublyLinkedList list) {

		if (list == null) {
			System.out.println("NULL");
			return;
		}

		DoublyLinkedListNode current = list.getHead();

		while (null != current) {
			System.out.print(String.format("%d <-> ", current.getValue()));
			current = current.getNext();
		}

		System.out.print("NULL");
	}
}
