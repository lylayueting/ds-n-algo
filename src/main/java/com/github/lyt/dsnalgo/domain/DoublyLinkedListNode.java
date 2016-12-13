package com.github.lyt.dsnalgo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoublyLinkedListNode {

	private int value;
	private DoublyLinkedListNode previous;
	private DoublyLinkedListNode next;

	public DoublyLinkedListNode(int value) {
		this.value = value;
	}
}
