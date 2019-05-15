package com.implementation.heap;

import java.util.PriorityQueue;

public class MergeKSortedArrays {
	
	private class HeapNode implements Comparable<HeapNode> {
		int arrayIndex;
		int valueIndex;
		int value;
		
		public HeapNode(int arrayIndex, int index, int value) {
			this.arrayIndex = arrayIndex;
			this.valueIndex = index;
			this.value = value;
		}
		
		public int compareTo(HeapNode node) {
			if(this.value < node.value) return -1;
			if(this.value > node.value) return 1;
			return 0;
		}
	}
	
	public int[] merge(int[][] arrays) {
		PriorityQueue<HeapNode> queue = new PriorityQueue<>();
		int size = 0;
		for(int i = 0; i < arrays.length; i++) {
			size += arrays[i].length;
			if(arrays[i].length != 0) {
				queue.add(new HeapNode(i, 0, arrays[i][0]));
			}
		}
		int[] result = new int[size];
		for(int i = 0; i < size; i++) {
			HeapNode currNode = queue.remove();
			result[i] = currNode.value;
			int index = currNode.valueIndex + 1;
			int arrIndex = currNode.arrayIndex;
			if(index < arrays[arrIndex].length) {
				queue.add(new HeapNode(arrIndex, index, arrays[arrIndex][index]));
			}
		}
		return result;
	}

}
