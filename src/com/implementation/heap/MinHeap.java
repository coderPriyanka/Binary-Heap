package com.implementation.heap;

import java.util.Arrays;

public class MinHeap {

	int[] heap;
	int heapSize;
	
	public void buildMinHeap(int[] heap) {
		this.heap = heap;
		this.heapSize = heap.length;
		for(int i = heapSize / 2 - 1; i >= 0; i--) {
			minHeapify(i);
		}
	}
	
	private void minHeapify(int index) {
		int minIndex = index;
		int left = index * 2 + 1;
		if(left < heapSize && heap[left] < heap[minIndex]) {
			minIndex = left;
		}
		int right = index * 2 + 2;
		if(right < heapSize && heap[right] < heap[minIndex]) {
			minIndex = right;
		}
		if(minIndex != index) {
			swap(index, minIndex);
			minHeapify(minIndex);
		}
	}
	
	private void swap(int index1, int index2) {
		int temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}
	
	public void insertKey(int key) {
		ensureExtraCapacity();
		heap[heapSize] = Integer.MIN_VALUE;
		increaseKey(heapSize, key);
		heapSize++;
	}

	private void ensureExtraCapacity() {
		if(heapSize == heap.length) {
			heap = Arrays.copyOf(heap, heapSize * 2);
		}
	}

	public int extractMin() {
		if(heapSize == 0) {
			throw new IllegalStateException();
		}
		int min = heap[0];
		heap[0] = heap[heapSize - 1];
		heapSize--;
		minHeapify(0);
		return min;
	}
	
	public int peek() {
		if(heapSize == 0) {
			throw new IllegalStateException();
		}
		return heap[0];
	}
	
	public void increaseKey(int index, int key) {
		if(index >= heapSize || key < heap[index]) {
			throw new IllegalStateException();
		}
		heap[index] = key;
		minHeapify(index);
	}
	
	public void decreaseKey(int index, int key) {
		if(index >= heapSize || key < heap[index]) {
			throw new IllegalStateException();
		}
		heap[index] = key;
		while(index > 0) {
			int parent = (index - 1) / 2;
			if(heap[parent] <= heap[index]) {
				break;
			}
			swap(index, parent);
			index = parent;
		}
	}

}
