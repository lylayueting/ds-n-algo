package com.github.lyt.dsnalgo.datastructure;

import com.github.lyt.dsnalgo.domain.BinaryTreeNode;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Unique value unbalanced BST.
 */
@Getter
@NoArgsConstructor
public class BinarySearchTree {

  private BinaryTreeNode root;
  private int size;

  public BinaryTreeNode getRoot() {
    return root;
  }

  public int size() {
    return size;
  }

  public BinarySearchTree(int[] array) {
    for (int x : array) {
      this.insert(x);
    }

    assert size == array.length;
  }

  public BinarySearchTree(List<Integer> list) {
    for (int x : list) {
      this.insert(x);
    }

    assert size == list.size();
  }

  public static BinaryTreeNode insertRecursive(BinaryTreeNode root, int value) {
    if (root == null) {
      root = new BinaryTreeNode(value);
      return root;
    }

    if (value < root.getValue()) {
      root.setLeft(insertRecursive(root.getLeft(), value));
      return root;
    }

    if (value > root.getValue()) {
      root.setRight(insertRecursive(root.getRight(), value));
      return root;
    }

    // if (value == root.value)
    return root;
  }

  public void insert(int value) {
    if (this.root == null) {
      BinaryTreeNode newNode = new BinaryTreeNode(value);
      newNode.setDept(0);
      newNode.setOffset(0);
      this.root = newNode;
      this.size = 1;
      return;
    }

    BinaryTreeNode current = this.root;

    while (true) {
      if (current.getValue() < value) {
        if (current.getRight() == null) {
          BinaryTreeNode newNode = new BinaryTreeNode(value);
          newNode.setDept(current.getDept() + 1);
          newNode.setOffset(2 * current.getOffset() + 1);
          current.setRight(newNode);
          this.size++;
          return;
        }

        current = current.getRight();
        continue;
      }

      if (current.getValue() > value) {
        if (current.getLeft() == null) {
          BinaryTreeNode newNode = new BinaryTreeNode(value);
          newNode.setDept(current.getDept() + 1);
          newNode.setOffset(2 * current.getOffset());
          current.setLeft(newNode);
          this.size++;
          return;
        }

        current = current.getLeft();
        continue;
      }

//    if (current.getValue() == value)
      return;
    }
  }

  public void delete(int value) {

  }

  public static void printInorderRecursive(BinaryTreeNode root) {
    if (root == null) {
      return;
    }

    printInorderRecursive(root.getLeft());
    System.out.print(root.getValue() + " ");
    printInorderRecursive(root.getRight());
  }

  /**
   * Prints a BST with given root in a line separated by single space, pre-order.
   * @param root
   */
  public static String preorderIterative(BinaryTreeNode root) {
    if (root == null) {
      return "";
    }

    StringBuilder builder = new StringBuilder();

    Stack<BinaryTreeNode> stack = new Stack<>();
    stack.push(root);

    while (!stack.empty()) {
      BinaryTreeNode top = stack.pop();

      builder.append(top.getValue()).append(" ");

      if (top.getRight() != null) {
        stack.push(top.getRight());
      }

      if (top.getLeft() != null) {
        stack.push(top.getLeft());
      }
    }

    return builder.toString();
  }

  public static String inorderIterative(BinaryTreeNode root) {
    if (root == null) {
      return "";
    }

    StringBuilder builder = new StringBuilder();

    Stack<BinaryTreeNode> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      BinaryTreeNode top = stack.peek();

      if (top.getLeft() == null) {
        while (top.getRight() == null) {
          builder.append(top.getValue()).append(" ");
          stack.pop();

          if (stack.isEmpty()) {
            return builder.toString();
          }

          top = stack.peek();
        }

        builder.append(top.getValue()).append(" ");
        stack.pop();
        stack.push(top.getRight());
        continue;
      }

      stack.push(top.getLeft());
    }

    return builder.toString();
  }

  public static String postorderIterative(BinaryTreeNode root) {

  }

  public static void print(BinarySearchTree tree) {
    if (tree.getRoot() == null) {
      return;
    }

    Queue<BinaryTreeNode> parent = new ArrayDeque<>();
    Queue<BinaryTreeNode> children = new ArrayDeque<>();

    parent.add(tree.getRoot());

    while (!parent.isEmpty()) {
      BinaryTreeNode current = parent.remove();

      System.out.print(current.toString());

      if (null != current.getLeft()) {
        children.add(current.getLeft());
      }

      if (null != current.getRight()) {
        children.add(current.getRight());
      }

      if (parent.isEmpty()) {
        parent.addAll(children);
        children.clear();

        System.out.print("|\n");
      }
    }
  }
}
