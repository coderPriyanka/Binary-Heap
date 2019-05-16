package com.implementation.heap;

import java.util.Arrays;

public class MaxHeap {
	
	int[] heap;
	int heapSize;
	
	public void buildMaxHeap(int[] heap) {
		this.heap = heap;
		this.heapSize = heap.length;
		for(int i = heapSize / 2 - 1; i >= 0; i--) {
			maxHeapify(i);
		}
	}

	private void maxHeapify(int index) {
		int maxIndex = index;
		int left = (index * 2) + 1;
		if(left < heapSize && heap[left] > heap[maxIndex]) {
			maxIndex = left;
		}
		int right = (index * 2) + 2;
		if(right < heapSize && heap[right] > heap[maxIndex]) {
			maxIndex = right;
		}
		if(maxIndex != index) {
			swap(index, maxIndex);
			maxHeapify(maxIndex);
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
		if(heap.length == heapSize) {
			heap = Arrays.copyOf(heap, heapSize * 2);
		}
	}
	
	public int extractMax() {
		if(heapSize == 0) {
			throw new IllegalStateException();
		}
		int max = heap[0];
		heap[0] = heap[heapSize - 1];
		heapSize--;
		maxHeapify(0);
		return max;
	}
	
	public int getMax() {
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
		while(index > 0) {
			int parent = (index - 1) / 2;
			if(heap[parent] >= heap[index]) {
				break;
			}
			swap(index, parent);
			index = parent;
		}
	}
	
	public void decreaseKey(int index, int key) {
		if(index >= heapSize || key > heap[index]) {
			throw new IllegalStateException();
		}
		heap[index] = key;
		maxHeapify(index);
	}

}
