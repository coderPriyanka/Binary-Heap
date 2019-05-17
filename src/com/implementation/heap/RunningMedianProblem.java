package com.implementation.heap;

import java.util.PriorityQueue;

public class RunningMedianProblem {
	
	public double[] getMedians(int[] arr) {
		double[] medians = new double[arr.length];
		PriorityQueue<Integer> lowers = new PriorityQueue<>((a, b) -> -1 * a.compareTo(b));
		PriorityQueue<Integer> highers = new PriorityQueue<>();
		for(int i = 0; i < arr.length; i++) {
			addNumber(arr[i], lowers, highers);
			rebalance(lowers, highers);
			medians[i] = getMedian(lowers, highers);
		}
		return medians;
	}

	private double getMedian(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
		if(lowers.size() == highers.size()) {
			return (double)(lowers.peek() + highers.peek()) / 2;
		}
		if(lowers.size() > highers.size()) {
			return lowers.peek();
		}
		return highers.peek();
	}

	private void rebalance(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
		int diff = Math.abs(lowers.size() - highers.size());
		if(diff <= 1) {
			return;
		}
		PriorityQueue<Integer> larger = lowers.size() > highers.size() ? lowers : highers;
		PriorityQueue<Integer> smaller = lowers.size() <= highers.size() ? lowers : highers;
		smaller.add(larger.poll());
	}

	private void addNumber(int number, PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
		if(lowers.size() == 0 || number < lowers.peek()) {
			lowers.add(number);
			return;
		}
		highers.add(number);
	}

}
