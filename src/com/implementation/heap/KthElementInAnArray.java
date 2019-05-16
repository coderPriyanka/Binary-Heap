package com.implementation.heap;

public class KthElementInAnArray {
	
	int size;
	
	public int findKthSmallest(int[] arr, int k) {
		if(k >= arr.length) {
			throw new IllegalStateException();
		}
		buildMinHeap(arr);
		for(int i = 1; i < k; i++) {
			extractMin(arr);
		}
		return extractMin(arr);
	}
	
	private int extractMax(int[] arr) {
		int max = arr[0];
		arr[0] = arr[size - 1];
		size--;
		maxHeapify(arr, 0);
		return max;
	}

	private void buildMinHeap(int[] arr) {
		this.size = arr.length;
		for(int i = arr.length / 2 - 1; i >= 0; i--) {
			minHeapify(arr, i);
		}
	}

	private void minHeapify(int[] arr, int index) {
		int minIndex = index;
		int left = index * 2 + 1;
		if(left < size && arr[left] < arr[minIndex]) {
			minIndex = index;
		}
		int right = index * 2 + 2;
		if(right < size && arr[right] < arr[minIndex]) {
			minIndex = right;
		}
		if(minIndex != index) {
			swap(arr, index, minIndex);
			minHeapify(arr, minIndex);
		}
	}

	private void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	public int findKthLargest(int[] arr, int k) {
		if(k >= arr.length) {
			throw new IllegalStateException();
		}
		buildMaxHeap(arr);
		for(int i = 1; i < k; i++) {
			extractMax(arr);
		}
		return extractMax(arr);
	}

	private int extractMin(int[] arr) {
		int min = arr[0];
		arr[0] = arr[size - 1];
		size--;
		minHeapify(arr, 0);
		return min;
	}

	private void buildMaxHeap(int[] arr) {
		this.size = arr.length;
		for(int i = arr.length / 2 - 1; i >= 0; i--) {
			maxHeapify(arr, i);
		}
	}

	private void maxHeapify(int[] arr, int index) {
		int maxIndex = index;
		int left = index * 2 + 1;
		if(left < size && arr[left] > arr[maxIndex]) {
			maxIndex = left;
		}
		int right = index * 2 + 2;
		if(right < size && arr[right] > arr[maxIndex]) {
			maxIndex = right;
		}
		if(maxIndex != index) {
			swap(arr, index, maxIndex);
			maxHeapify(arr, maxIndex);
		}
	}

}
