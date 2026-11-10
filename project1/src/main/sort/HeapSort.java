/*
 * Name:        Elli Steck
 * Course:      CS 2430, Section 501
 * Project:     Programming Project 1 – Summer 2026
 * File:        HeapSort.java
 * Description: Implements heap sort for arrays of integers, using a max-heap
 * 				stored in the array itself, and returns the comparison count.
 */
package main.sort;

import static main.utils.ArrayUtils.swap;

/**
 * Implements heap sort for arrays of integers, using a max-heap stored in the
 * array itself, and returns the comparison count. This class is not meant to be
 * instantiated.
 *
 * Source: Heap Sort on GeeksforGeeks
 */
public final class HeapSort {

	private HeapSort() {
	}

	/**
	 * Entry-point to sort the given array in ascending order.
	 *
	 * @param array the array to sort; modified in place
	 * @return the count of the current sort run
	 */
	public static int sort(int[] array) {
		ComparisonCounter counter = new ComparisonCounter();

		int n = array.length;

		// Starting from the last non-leaf node
		for (int i = (n / 2) - 1; i >= 0; i--) {
			heapify(array, n, i, counter);
		}

		// Move the largest element to the end, then restore the heap
		for (int j = n - 1; j > 0; j--) {
			swap(array, j, 0);
			heapify(array, j, 0, counter);
		}

		return counter.getCount();
	}

	/**
	 * Sifts the element at index i down until the subtree rooted at i is a
	 * max-heap.
	 *
	 * @param array   the array holding the heap
	 * @param n       the number of elements in the heap
	 * @param i       the index of the subtree's root
	 * @param counter the counter for the current sort run
	 */
	private static void heapify(
		int[] array,
		int n,
		int i,
		ComparisonCounter counter
	) {
		// Initialize the maximum element as that at the current index
		int max = i;

		int l = (2 * i) + 1;
		int r = (2 * i) + 2;

		// Check bounds before reading a child, and compare against the
		// current maximum, which may already be the left or right child
		if (l < n && counter.compare(array[l], array[max]) > 0) {
			max = l;
		}

		if (r < n && counter.compare(array[r], array[max]) > 0) {
			max = r;
		}

		if (max != i) {
			swap(array, i, max);
			heapify(array, n, max, counter);
		}
	}

}