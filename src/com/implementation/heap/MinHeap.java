package com.implementation.heap;

import java.util.LinkedList;
import java.util.Queue;

public class MinHeap {

	private int capacity;
	private int[] heap;
	private int heapSize;
	
	public MinHeap(int capacity) {
		this.capacity = capacity;
		this.heap = new int[capacity];
		this.heapSize = 0;
	}
	
	public void add(int data) {
		if(heapSize == capacity) {
			throw new IllegalStateException("");
		}
		heap[heapSize++] = data;
		heapifyUp();
	}
	
	private void heapifyUp() {
		int index = heapSize - 1;
		while(index > 0) {
			if(heap[getParentIndex(index)] < heap[index]) {
				break;
			}
			int parent = getParentIndex(index);
			swap(index, parent);
			index = parent;
		}
	}
	
	public int peek() {
		if(heapSize == 0) {
			throw new IllegalStateException();
		}
		return heap[0];
	}
	
	public int extractMin() {
		if(heapSize == 0) {
			throw new IllegalStateException("");
		}
		int minValue = heap[0];
		heap[0] = heap[heapSize - 1];
		heapSize--;
		heapifyDown();
		return minValue;
	}
	
	public boolean isEmpty() {
		return heapSize == 0;
	}
	
	public void delete(int key) {
		decreaseKey(key, Integer.MIN_VALUE);
		extractMin();
	}
	
	public void decreaseKey(int key, int value) {
		if(key >= heapSize) {
			return;
		}
		heap[key] = value;
		heapifyUp();
	}

	private void heapifyDown() {
		int index = 0;
		while(index < heapSize) {
			int left, right, minIndex = index;
			if(hasLeftChild(index)) {
				left = getLeftIndex(index);
				minIndex = findMinIndex(minIndex, left);
			}
			if(hasRightChild(index)) {
				right = getRightIndex(index);
				minIndex = findMinIndex(minIndex, right);
			}
			if(minIndex == index) {
				break;
			}
			swap(index, minIndex);
			index = minIndex;
		}
	}
	
	private void swap(int index1, int index2) {
		int temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}

	private int findMinIndex(int index1, int index2) {
		return heap[index1] < heap[index2] ? index1  : index2;
	}

	private boolean hasLeftChild(int index) {
		return index * 2 + 1 < heapSize;
	}
	
	private boolean hasRightChild(int index) {
		return index * 2 + 2 < heapSize;
	}

	private int getParentIndex(int index) {
		return (index - 1) / 2;
	}

	private int getLeftIndex(int index) {
		return index * 2 + 2;
	}

	private int getRightIndex(int index) {
		return index * 2 + 1;
	}

	public void printHeap() {
		System.out.println("Heap size = " + heapSize);
		Queue<Integer> q = new LinkedList<>();
		int index = 0;
		q.add(heap[index++]);
		int power = 0;
		int count = (int)Math.pow(2, power++);
		while(!q.isEmpty()) {
			System.out.print(q.remove() + " ");
			count--;
			if(count == 0) {
				count = (int)Math.pow(2, power++);
				System.out.println();
			}
			if(index < heapSize) {
				q.add(heap[index++]);
			}
		}
		System.out.println();
	}

}
