package main.sort;

import static main.utils.ArrayUtils.swap;

/**
 * Quick sort for arrays of integers. This class is not meant to be
 * instantiated.
 *
 * Source: Quick Sort on GeeksforGeeks
 * 
 * @author Elli Steck
 */
public final class QuickSort {

	private QuickSort() {
	}

	/**
	 * Entry-point to sort the given array in ascending order.
	 *
	 * @param array the array to sort; modified in place
	 */
	public static void sort(int[] array) {
		sort(array, 0, array.length - 1);
	}

	/**
	 * Recursively sorts the range [l, h] of the array.
	 *
	 * @param array the array being sorted
	 * @param l     index of the first element in the range (inclusive)
	 * @param h     index of the last element in the range (inclusive)
	 */
	private static void sort(int[] array, int l, int h) {
		if (l < h) {
			// Index returned from the last pivot
			int pi = partition(array, l, h);

			// Sort through smaller elements
			sort(array, l, pi - 1);

			// Sort through greater-than or equal elements
			sort(array, pi + 1, h);
		}
	}

	/**
	 * Partitions the range [l, h] around the last element (the pivot). After
	 * this call, every element left of the returned index is less than or equal
	 * to the pivot, every element right of it is greater than the pivot, and
	 * the pivot sits at the returned index in its final sorted position.
	 *
	 * @param array the array containing the range to partition
	 * @param l     index of the first element in the range (inclusive)
	 * @param h     index of the last element in the range (inclusive); also the
	 *              pivot's starting index
	 * @return the final index of the pivot
	 */
	private static int partition(int[] array, int l, int h) {
		int pivot = array[h];

		// Index of the smaller element
		int i = l - 1;

		// Traverse low..high and move all smaller elements to the left
		for (int j = l; j <= h - 1; j++) {
			if (array[j] <= pivot) {
				i++;
				swap(array, i, j);
			}
		}

		swap(array, i + 1, h);

		// Move the pivot point to the right of the sorted smaller elements
		return i + 1;
	}

}