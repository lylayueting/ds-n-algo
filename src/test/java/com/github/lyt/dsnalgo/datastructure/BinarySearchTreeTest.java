package com.github.lyt.dsnalgo.datastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class BinarySearchTreeTest {

	@Test
	public void testRightMost() {
		int[] input = {0, 1, 2, 3, 4, 5, 6, 7};

		BinarySearchTree bst = new BinarySearchTree(input);

		BinarySearchTree.print(bst);
	}

	@Test
	public void testFull3() {
		int[] input = {4, 2, 6, 1, 3, 5, 7};

		BinarySearchTree bst = new BinarySearchTree(input);

		BinarySearchTree.print(bst);
	}
}
