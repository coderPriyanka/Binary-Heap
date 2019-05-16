package com.implementation.heap;

public class HeapSort {
	
	private int heapSize;
	
	private void buildHeap(int[] arr) {
		heapSize = arr.length;
		for(int i = arr.length / 2 - 1; i >= 0; i--) {
			heapify(arr, i);
		}
	}

	private void heapify(int[] arr, int index) {
		int maxIndex = index;
		int left = (index * 2) + 1;
		if(left < heapSize && arr[left] > arr[maxIndex]) {
			maxIndex = left;
		}
		int right = (index * 2) + 2;
		if(right < heapSize && arr[right] > arr[maxIndex]) {
			maxIndex = right;
		}
		if(maxIndex != index) {
			swap(arr, index, maxIndex);
			heapify(arr, maxIndex);
		}
	}

	private void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	public int[] heapSort(int[] arr) {
		buildHeap(arr);
		int[] result = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			result[i] = extractMax(arr);
		}
		return result;
	}

	private int extractMax(int[] arr) {
		int max = arr[0];
		arr[0] = arr[heapSize - 1];
		heapSize--;
		heapify(arr, 0);
		return max;
	}

}
