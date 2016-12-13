package com.github.lyt.dsnalgo.datastructure;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import lombok.Getter;

import com.github.lyt.dsnalgo.domain.BinaryTreeNode;

/**
 * Unique value unbalanced BST.
 */
@Getter
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

	public void insert(int value) {
		BinaryTreeNode current = root;

		BinaryTreeNode newNode = new BinaryTreeNode(value);

		if (null == current) {
			current = newNode;
			current.setDept(0);
			current.setOffset(0);
			root = current;
			size = 1;
			return;
		}

		while (true) {
			newNode.setDept(current.getDept() + 1);

			if (current.getValue() > value) {
				if (null != current.getLeft()) {
					current = current.getLeft();
					continue;
				}

				newNode.setOffset(current.getOffset() * 2);
				current.setLeft(newNode);
				break;
			}

			if (current.getValue() < value) {
				if (null != current.getRight()) {
					current = current.getRight();
					continue;
				}

				newNode.setOffset(current.getOffset() * 2 + 1);
				current.setRight(newNode);
				break;
			}

			throw new IllegalArgumentException("Value exists in current tree.");
		}

		size++;
	}

	public void delete(int value) {

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

			System.out.print(String.format("| %d (%d,%d) ", current.getValue(), current.getDept(),
					current.getOffset()));

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
