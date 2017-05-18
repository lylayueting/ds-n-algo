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
    this.root = deleteRecursive(this.root, value);
    this.size--;
  }

  public int height() {
    if (root == null) {
      return 0;
    }

    Stack<BinaryTreeNode> stack = new Stack<>();
    stack.push(root);
    int height = root.getDept();

    while (!stack.isEmpty()) {
      BinaryTreeNode top = stack.peek();

      if (top.getLeft() != null) {
        stack.push(top.getLeft());
        continue;
      }

      while (top.getRight() == null) {
        height = height > top.getDept() ? height : top.getDept();
        stack.pop();
        if (stack.isEmpty()) {
          return height;
        }
        top = stack.peek();
      }

      height = height > top.getDept() ? height : top.getDept();
      stack.pop();
      stack.push(top.getRight());
    }

    return height;
  }

  private static int biggest(BinaryTreeNode root) {
    assert root != null;

    while (root.getRight() != null) {
      root = root.getRight();
    }

    return root.getValue();
  }

  private static int smallest(BinaryTreeNode root) {
    assert root != null;

    while (root.getLeft() != null) {
      root = root.getLeft();
    }

    return root.getValue();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || !(object instanceof BinarySearchTree)) {
      return false;
    }

    BinarySearchTree other = (BinarySearchTree) object;
    return inorderIterative(this.root).equals(inorderIterative(other.getRoot()));
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

  public static void printLevelOrder(BinarySearchTree tree) {
    if (tree == null) {
      return;
    }

    for (int i = 0; i <= tree.height(); i++) {
      printLevelRecursive(tree.getRoot(), i);
    }
  }

  private static void printLevelRecursive(BinaryTreeNode root, int depth) {
    if (root == null) {
      return;
    }

    if (depth == 0) {
      System.out.print(root.toString());
      return;
    }

    printLevelRecursive(root.getLeft(), depth - 1);
    printLevelRecursive(root.getRight(), depth - 1);
  }

  public static BinaryTreeNode deleteRecursive(BinaryTreeNode root, int value) {
    if (root == null) {
      return null;
    }

    if (root.getValue() > value) {
      root.setLeft(deleteRecursive(root.getLeft(), value));
      return root;
    }

    if (root.getValue() < value) {
      root.setRight(deleteRecursive(root.getRight(), value));
      return root;
    }

    return deleteNodeHelper(root);
  }

  private static BinaryTreeNode deleteNodeHelper(BinaryTreeNode node) {
    assert node != null;

    if (node.getLeft() == null && node.getRight() == null) {
      return null;
    }

    if (node.getLeft() != null) {
      int nextSmaller = biggest(node.getLeft());
      node.setValue(nextSmaller);
      node.setLeft(deleteRecursive(node.getLeft(), nextSmaller));
      return node;
    }

    int nextBigger = smallest(node.getRight());
    node.setValue(nextBigger);
    node.setRight(deleteRecursive(node.getRight(), nextBigger));
    return node;
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
    if (root == null) {
      return "";
    }

    Stack<BinaryTreeNode> reverseTraversal = new Stack<>();
    reverseTraversal.push(root);
    // For algorithm's sake, could use string reverse print directly.
    Stack<BinaryTreeNode> visited = new Stack<>();
    StringBuilder builder = new StringBuilder();

    while (!reverseTraversal.isEmpty()) {
      BinaryTreeNode top = reverseTraversal.pop();

      if (top.getLeft() != null) {
        reverseTraversal.push(top.getLeft());
      }

      if (top.getRight() != null) {
        reverseTraversal.push(top.getRight());
      }

      visited.push(top);
    }

    while (!visited.isEmpty()) {
      builder.append(visited.pop().getValue()).append(" ");
    }

    return builder.toString();
  }

  public static String postorderIterativeOneStack(BinaryTreeNode root) {
    if (root == null) {
      return "";
    }

    Stack<BinaryTreeNode> stack = new Stack<>();
    BinaryTreeNode currentRoot = root;
    StringBuilder builder = new StringBuilder();

    do {
      while (currentRoot != null) {
        if (currentRoot.getRight() != null) {
          stack.push(currentRoot.getRight());
        }
        stack.push(currentRoot);
        currentRoot = currentRoot.getLeft();
      }

      BinaryTreeNode top = stack.pop();

      // top node has right child and HAVEN'T BEEN PROCESSED.
      if (top.getRight() != null && !stack.isEmpty() && top.getRight() == stack.peek()) {
        stack.pop(); // Take right node out of stack and process subtree.
        stack.push(top);
        currentRoot = top.getRight();
        continue;
      }

      builder.append(top.getValue()).append(" ");
      currentRoot = null;
    } while (!stack.isEmpty());

    return builder.toString();
  }

  public static String bfs(BinaryTreeNode root) {
    if (root == null) {
      return "";
    }

    Queue<BinaryTreeNode> queue = new ArrayDeque<>();
    queue.add(root);

    StringBuilder builder = new StringBuilder();

    while (!queue.isEmpty()) {
      BinaryTreeNode top = queue.remove();
      builder.append(top.getValue()).append(" ");

      if (top.getLeft() != null) {
        queue.add(top.getLeft());
      }

      if (top.getRight() != null) {
        queue.add(top.getRight());
      }
    }

    return builder.toString();
  }

  public static String inorderWithoutStack(BinaryTreeNode root) {
    if (root == null) {
      return "";
    }

    StringBuilder builder = new StringBuilder();
    BinaryTreeNode current = root;

    while (current != null) {
      if (current.getLeft() == null) {
        builder.append(current.getValue()).append(" ");
        current = current.getRight();
        continue;
      }

      BinaryTreeNode prev = current.getLeft();

      while (prev.getRight() != null && prev.getRight() != current) {
        prev = prev.getRight();
      }

      if (prev.getRight() == null) {
        prev.setRight(current);
        current = current.getLeft();
      } else {
        builder.append(current.getValue()).append(" ");
        prev.setRight(null);
        current = current.getRight();
      }
    }

    return builder.toString();
  }
}
