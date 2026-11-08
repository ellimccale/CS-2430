package main.sort;

import static main.utils.ArrayUtils.swap;

/**
 * Heap sort for arrays of integers, using a max-heap stored in the array
 * itself. This class is not meant to be instantiated.
 *
 * Source: Heap Sort on GeeksforGeeks
 * 
 * @author Elli Steck
 */
public final class HeapSort {

	private HeapSort() {
	}

	/**
	 * Entry-point to sort the given array in ascending order.
	 *
	 * @param array the array to sort; modified in place
	 */
	public static void sort(int[] array) {
		int n = array.length;

		// Starting from the last non-leaf node
		for (int i = (n / 2) - 1; i >= 0; i--) {
			heapify(array, n, i);
		}

		// Move the largest element to the end, then restore the heap
		for (int j = n - 1; j > 0; j--) {
			swap(array, j, 0);
			heapify(array, j, 0);
		}
	}

	/**
	 * Sifts the element at index i down until the subtree rooted at i is a
	 * max-heap.
	 *
	 * @param array the array holding the heap
	 * @param n     the number of elements in the heap
	 * @param i     the index of the subtree's root
	 */
	private static void heapify(int[] array, int n, int i) {
		// Initialize the maximum element as that at the current index
		int max = i;

		int l = (2 * i) + 1;
		int r = (2 * i) + 2;

		// Check bounds before reading a child, and compare against the
		// current maximum, which may already be the left child
		if (l < n && array[l] > array[max]) {
			max = l;
		}

		if (r < n && array[r] > array[max]) {
			max = r;
		}

		if (max != i) {
			swap(array, i, max);
			heapify(array, n, max);
		}
	}

}