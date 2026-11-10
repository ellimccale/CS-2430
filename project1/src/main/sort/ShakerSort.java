package main.sort;

import static main.utils.ArrayUtils.swap;

/**
 * Shaker sort (or cocktail sort) for arrays of integers. This class is not
 * meant to be instantiated.
 * 
 * Source: Cocktail shaker sort on Wikipedia
 * 
 * @author Elli Steck
 */
public final class ShakerSort {

	private ShakerSort() {
	}

	/**
	 * Entry-point to sort the given array in ascending order.
	 *
	 * @param array the array to sort; modified in place
	 * @return the count of the current sort run
	 */
	public static int sort(int[] array) {
		ComparisonCounter counter = new ComparisonCounter();

		boolean swapped = true;

		while (swapped) {
			// Reset the flag
			swapped = false;

			// Forward pass: swap adjacent out-of-order elements from left to
			// right
			for (int i = 0; i <= array.length - 2; i++) {
				if (counter.compare(array[i], array[i + 1]) > 0) {
					swap(array, i, i + 1);
					swapped = true;
				}
			}

			// If no swaps have occurred, end the loop early
			if (!swapped) {
				break;
			}

			swapped = false;

			// Backward pass: swap adjacent out-of-order elements from right to
			// left
			for (int j = array.length - 2; j >= 0; j--) {
				if (counter.compare(array[j], array[j + 1]) > 0) {
					swap(array, j, j + 1);
					swapped = true;
				}
			}
		}

		return counter.getCount();
	}

}
