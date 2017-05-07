package com.github.lyt.dsnalgo.datastructure;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class BinarySearchTreeTest {

	@Test
	public void testRun() {
		int[] input = {4, 2, 6, 1, 3, 5, 7};

		BinarySearchTree bst = new BinarySearchTree(input);

		BinarySearchTree.print(bst);

    BinarySearchTree.printInorderRecursive(bst.getRoot());
	}

	@Test
  public void testPreorderIterative() {
    int[] input = {4, 2, 6, 1, 3, 5, 7};

    BinarySearchTree bst = new BinarySearchTree(input);
    BinarySearchTree.print(bst);

    String expected = "4 2 1 3 6 5 7 ";

    Assert.assertEquals(expected, BinarySearchTree.preorderIterative(bst.getRoot()));
  }

  @Test
  public void testInorderIterative() {
    int[] input = {7, 3, 8, 2, 6, 1, 4, 5};

    BinarySearchTree bst = new BinarySearchTree(input);
    BinarySearchTree.print(bst);

    String expected = "1 2 3 4 5 6 7 8 ";

    Assert.assertEquals(expected, BinarySearchTree.inorderIterative(bst.getRoot()));
  }
}
