package com.github.lyt.dsnalgo.datastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class DoublyLinkedListTest {

	@Test
	public void testOrdered8() {
		int[] input = {0, 1, 2, 3, 4, 5, 6, 7};

		DoublyLinkedList bst = new DoublyLinkedList(input);

		DoublyLinkedList.print(bst);
	}

	@Test
	public void testRandomized8() {
		int[] input = {4, 2, 6, 1, 3, 5, 7};

		DoublyLinkedList bst = new DoublyLinkedList(input);

		DoublyLinkedList.print(bst);
	}
}
