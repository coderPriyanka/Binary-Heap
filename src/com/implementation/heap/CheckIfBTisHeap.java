package com.implementation.heap;

import java.util.LinkedList;
import java.util.Queue;

public class CheckIfBTisHeap {
	
	public boolean isHeap(BtNode root) {
		return isMinHeap(root) || isMaxHeap(root);
	}
	
	public boolean isHeapRecursive(BtNode root) {
		return isMaxHeapRecursive(root) || isMinHeapRecursive(root);
	}

	private boolean isMaxHeap(BtNode root) {
		Queue<BtNode> nodes = new LinkedList<>();
		nodes.add(root);
		while(!nodes.isEmpty()) {
			BtNode curr = nodes.poll();
			if(curr.left == null && curr.right != null) {
				return false;
			}
			if(curr.left != null && curr.data < curr.left.data) {
				return false;
			}
			if(curr.left != null) {
				nodes.add(curr.left);
			}
			if(curr.right != null && curr.data < curr.right.data) {
				return false;
			}
			if(curr.right != null) {
				nodes.add(curr.right);
			}
		}
		return true;
	}

	private boolean isMinHeap(BtNode root) {
		Queue<BtNode> nodes = new LinkedList<>();
		nodes.add(root);
		while(!nodes.isEmpty()) {
			BtNode curr = nodes.poll();
			if(curr.left == null && curr.right != null) {
				return false;
			}
			if(curr.left != null && curr.data > curr.left.data) {
				return false;
			}
			if(curr.left != null) {
				nodes.add(curr.left);
			}
			if(curr.right != null && curr.data > curr.right.data) {
				return false;
			}
			if(curr.right != null) {
				nodes.add(curr.right);
			}
		}
		return false;
	}

	public boolean isMaxHeapRecursive(BtNode root) {
		if(root == null) {
			return true;
		}
		boolean c0 = root.left == null && root.right != null;
		boolean c1 = root.left != null && root.left.data > root.data;
		boolean c2 = root.right != null && root.right.data > root.data;
		return !c0 && !c1 && !c2 && isMaxHeapRecursive(root.left) && isMaxHeapRecursive(root.right);
	}
	
	public boolean isMinHeapRecursive(BtNode root) {
		if(root == null) {
			return true;
		}
		boolean c0 = root.left == null && root.right != null;
		boolean c1 = root.left != null && root.left.data < root.data;
		boolean c2 = root.right != null && root.right.data < root.data;
		return !c0 && !c1 && !c2 && isMinHeapRecursive(root.left) && isMinHeapRecursive(root.right);
	}
}
