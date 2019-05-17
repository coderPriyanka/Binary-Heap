package com.implementation.heap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class KthElementInAnArray {
	
	private int[] heap;
	private int size;

	private void buildMinHeap(int[] arr) {
		initialiseHeap(arr);
		this.size = arr.length;
		for(int i = arr.length / 2 - 1; i >= 0; i--) {
			minHeapify(i);
		}
	}

	private void initialiseHeap(int[] arr) {
		this.heap = Arrays.copyOf(arr, arr.length);
		this.size = arr.length;
	}

	private void minHeapify(int index) {
		int minIndex = index;
		int left = index * 2 + 1;
		if(left < size && heap[left] < heap[minIndex]) {
			minIndex = left;
		}
		int right = index * 2 + 2;
		if(right < size && heap[right] < heap[minIndex]) {
			minIndex = right;
		}
		if(minIndex != index) {
			swap(index, minIndex);
			minHeapify(minIndex);
		}
	}

	private int extractMin() {
		int min = heap[0];
		heap[0] = heap[size - 1];
		size--;
		minHeapify(0);
		return min;
	}

	public int findKthSmallest(int[] arr, int k) {
		if(k >= arr.length) {
			throw new IllegalStateException();
		}
		buildMinHeap(arr);
		printHeap(arr);
		for(int i = 1; i < k; i++) {
			extractMin();
		}
		return extractMin();
	}

	private void swap(int index1, int index2) {
		int temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}

	public int findKthLargest(int[] arr, int k) {
		initialiseHeap(arr);
		if(k >= arr.length) {
			throw new IllegalStateException();
		}
		buildMaxHeap(arr);
		printHeap(arr);
		for(int i = 1; i < k; i++) {
			extractMax();
		}
		return extractMax();
	}
	
	private void printHeap(int[] arr) {
		Queue<Integer> q = new LinkedList<>();
		q.add(arr[0]);
		int pow = 0;
		int index = 1;
		int count = 1;
		System.out.println("Heap : ");
		while(!q.isEmpty()) {
			System.out.print(q.poll() + " ");
			count--;
			if(count == 0) {
				System.out.println();
				count = (int)Math.pow(2, ++pow);
			}
			if(index < arr.length) {
				q.add(arr[index++]);
			}
		}
		System.out.println();
	}

	private void buildMaxHeap(int[] arr) {
		this.size = arr.length;
		for(int i = arr.length / 2 - 1; i >= 0; i--) {
			maxHeapify(i);
		}
	}

	private int extractMax() {
		int max = heap[0];
		heap[0] = heap[size - 1];
		size--;
		maxHeapify(0);
		return max;
	}

	private void maxHeapify(int index) {
		int maxIndex = index;
		int left = index * 2 + 1;
		if(left < size && heap[left] > heap[maxIndex]) {
			maxIndex = left;
		}
		int right = index * 2 + 2;
		if(right < size && heap[right] > heap[maxIndex]) {
			maxIndex = right;
		}
		if(maxIndex != index) {
			swap(index, maxIndex);
			maxHeapify(maxIndex);
		}
	}

}
