package main.sort;

/**
 * Top-down merge sort for arrays of integers. This class is not meant to be
 * instantiated.
 *
 * Source: Merge Sort on GeeksforGeeks
 * 
 * @author Elli Steck
 */
public final class MergeSort {

	private MergeSort() {
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
	 * Recursively sorts the range [l, r] of the array.
	 *
	 * @param array the array being sorted
	 * @param l     index of the first element in the range (inclusive)
	 * @param r     index of the last element in the range (inclusive)
	 */
	private static void sort(int[] array, int l, int r) {
		if (l >= r) {
			return;
		}

		// Find the middle point
		int m = l + (r - l) / 2;

		// Sort the first and second halves
		sort(array, l, m);
		sort(array, m + 1, r);

		// Merge the sorted halves
		merge(array, l, m, r);
	}

	/**
	 * Merges the two sorted ranges [l, m] and [m + 1, r] of the array into a
	 * single sorted range [l, r].
	 *
	 * @param array the array containing both ranges
	 * @param l     index of the first element of the left range
	 * @param m     index of the last element of the left range
	 * @param r     index of the last element of the right range
	 */
	private static void merge(int[] array, int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		// Create temporary arrays
		int[] left = new int[n1];
		int[] right = new int[n2];

		// Copy elements from the main array into the temporary arrays
		for (int i = 0; i < n1; i++) {
			left[i] = array[l + i];
		}
		for (int j = 0; j < n2; j++) {
			right[j] = array[m + 1 + j];
		}

		int i = 0, j = 0;
		int k = l;

		// Merge the temporary arrays back into the main array, from left to
		// right
		while (i < n1 && j < n2) {
			int leftEl = left[i];
			int rightEl = right[j];

			if (leftEl <= rightEl) {
				array[k] = leftEl;
				i++;
			} else {
				array[k] = rightEl;
				j++;
			}

			k++;
		}

		// Copy the remaining elements of the left half back into the main array
		while (i < n1) {
			array[k] = left[i];
			i++;
			k++;
		}

		// Copy the remaining elements of the right half back into the main
		// array
		while (j < n2) {
			array[k] = right[j];
			j++;
			k++;
		}
	}
}