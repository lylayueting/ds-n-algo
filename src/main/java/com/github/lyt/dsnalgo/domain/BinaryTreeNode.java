package com.github.lyt.dsnalgo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BinaryTreeNode {
	private int value;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	private int dept;
	private int offset;

	public BinaryTreeNode(int value) {
		this.value = value;
	}

	public BinaryTreeNode(int value, BinaryTreeNode left, BinaryTreeNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
}
