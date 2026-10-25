package main.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Top-down merge sort for lists of int[] elements. This class is not meant to
 * be instantiated.
 * 
 * Source: Merge Sort on GeeksforGeeks
 * 
 * @author Elli Steck
 */
public final class MergeSort {

	private MergeSort() {
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
	 * Recursively sorts the range [l, r] of the list.
	 *
	 * @param list the list being sorted
	 * @param l    index of the first element in the range (inclusive)
	 * @param r    index of the last element in the range (inclusive)
	 */
	private static void sort(List<int[]> list, int l, int r) {
		if (l >= r) {
			return;
		}

		// Find the middle point
		int m = l + (r - l) / 2;

		// Sort the first and second halves
		sort(list, l, m);
		sort(list, m + 1, r);

		// Merge the sorted halves
		merge(list, l, m, r);
	}

	/**
	 * Merges the two sorted ranges [l, m] and [m + 1, r] of the list into a
	 * single sorted range [l, r].
	 *
	 * @param list the list containing both ranges
	 * @param l    index of the first element of the left range
	 * @param m    index of the last element of the left range
	 * @param r    index of the last element of the right range
	 */
	private static void merge(List<int[]> list, int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		// Create temporary lists
		List<int[]> left = new ArrayList<>();
		List<int[]> right = new ArrayList<>();

		// Copy elements froms the main list into the temporary lists
		for (int i = 0; i < n1; i++) {
			int[] nextEl = list.get(l + i);
			left.add(nextEl);
		}
		for (int j = 0; j < n2; j++) {
			int[] nextEl = list.get(m + 1 + j);
			right.add(nextEl);
		}

		int i = 0, j = 0;
		int k = l;

		// Merge the temporary lists back into the main list, from left to right
		while (i < n1 && j < n2) {
			int[] leftEl = left.get(i);
			int[] rightEl = right.get(j);

			if (Arrays.compare(leftEl, rightEl) <= 0) {
				list.set(k, leftEl);
				i++;
			} else {
				list.set(k, rightEl);
				j++;
			}

			k++;
		}

		// Copy the remaining elements of the left half back into the main list
		while (i < n1) {
			list.set(k, left.get(i));
			i++;
			k++;
		}

		// Copy the remaining elements of the right half back into the main list
		while (j < n2) {
			list.set(k, right.get(j));
			j++;
			k++;
		}
	}
}
