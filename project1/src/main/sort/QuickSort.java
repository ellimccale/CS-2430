package main.sort;

import java.util.Arrays;
import java.util.List;

import static main.utils.ArrayUtils.swap;

/**
 * Quick sort for lists of int[] elements. This class is not meant to be
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
	 * Entry-point to sort the given list in ascending order.
	 *
	 * @param list the list to sort; modified in place
	 */
	public static void sort(List<int[]> list) {
		sort(list, 0, list.size() - 1);
	}

	/**
	 * Recursively sorts the range [l, h] of the list.
	 *
	 * @param list the list being sorted
	 * @param l    index of the first element in the range (inclusive)
	 * @param h    index of the last element in the range (inclusive)
	 */
	private static void sort(List<int[]> list, int l, int h) {
		if (l < h) {
			// Index returned from the last pivot
			int pi = partition(list, l, h);

			// Sort through smaller elements
			sort(list, l, pi - 1);

			// Sort through greater-than or equal elements
			sort(list, pi + 1, h);
		}
	}

	/**
	 * Partitions the range [l, h] around the last element (the pivot). After
	 * this call, every element left of the returned index is less than or equal
	 * to the pivot, every element right of it is greater than the pivot, and
	 * the pivot sits at the returned index in its final sorted position.
	 *
	 * @param list the list containing the range to partition
	 * @param l    index of the first element in the range (inclusive)
	 * @param h    index of the last element in the range (inclusive); also the
	 *             pivot's starting index
	 * @return the final index of the pivot
	 */
	private static int partition(List<int[]> list, int l, int h) {
		int[] pivot = list.get(h);

		// Index of the smaller element
		int i = l - 1;

		// Traverse low..high and move all smaller elements to the left
		for (int j = l; j <= h - 1; j++) {
			if (Arrays.compare(list.get(j), pivot) <= 0) {
				i++;
				swap(list, i, j);
			}
		}

		swap(list, i + 1, h);

		// Move the pivot point to the right of the sorted smaller elements
		return i + 1;
	}

}
