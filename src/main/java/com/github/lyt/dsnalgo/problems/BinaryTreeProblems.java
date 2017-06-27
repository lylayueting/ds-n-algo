package com.github.lyt.dsnalgo.problems;

import com.github.lyt.dsnalgo.datastructure.BinarySearchTree;
import com.github.lyt.dsnalgo.domain.BinaryTreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Yueting on 5/17/2017.
 */
public class BinaryTreeProblems {

  /**
   * Use inorder traversal algorithm, and print out path (current Stack elements) when encountering
   * a leaf node.
   */
  private static void treeToDLL(BinaryTreeNode root) {
    if (root == null) {
      return;
    }

    BinaryTreeNode nextSmaller = BinarySearchTree.biggest(root.getLeft());
    treeToDLL(root.getLeft());
    root.setLeft(nextSmaller);
    if (nextSmaller != null) {
      nextSmaller.setRight(root);
    }

    BinaryTreeNode nextLarger = BinarySearchTree.smallest(root.getRight());
    treeToDLL(root.getRight());
    root.setRight(nextLarger);
    if (nextLarger != null) {
      nextLarger.setLeft(root);
    }
  }

  public static void main(String[] args) {
//    int[] input = {7, 3, 8, 2, 6, 1, 4, 5};
//
//    BinarySearchTree bst = new BinarySearchTree(input);
//    BinarySearchTree.print(bst);
//
//    treeToDLL(bst.getRoot());
//
//    BinaryTreeNode smallest = BinarySearchTree.smallest(bst.getRoot());
//    BinaryTreeNode biggest = BinarySearchTree.biggest(bst.getRoot());
//
//    smallest.setLeft(biggest);
//    biggest.setRight(smallest);

    int[] nums = {1, 2, 2, 2, 3, 4, 5, 5};
    for (int i : removeDuplicates(nums))
      System.out.println(i);
  }

  public static int[] removeDuplicates(int[] nums) {
    if (nums.length <=1) {
      return nums;
    }

    int deDupedLeftToThis = 0, current = 0;
    int len = nums.length;
    while (current<len) {
      int num = nums[current];
      nums[deDupedLeftToThis++] = num;
      while(current < len && nums[current] == num) {
        current++;
      }
    }

    return nums;
  }
}
