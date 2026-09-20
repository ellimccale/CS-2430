package main.utils;

import java.util.Collections;
import java.util.List;

/**
 * Static utility methods for performing operations on arrays and lists.
 * This class is not meant to be instantiated.
 */
public class ArrayUtils {

	private ArrayUtils() {}

	/**
	 * Swaps the elements of array A at indices x and y.
	 *
	 * @param A the array with elements to swap
	 * @param x the first index
	 * @param y the second index
	 * @param <T> the type of elements in the array
	 */
	public static <T> void swap(T[] A, int x, int y) {
		T temp = A[x];
		A[x] = A[y];
		A[y] = temp;
	}

	/**
	 * Swaps the elements of list L at indices x and y.
	 *
	 * @param L the list with elements to swap
	 * @param x the first index
	 * @param y the second index
	 * @param <T> the type of elements in the list
	 */
	public static <T> void swap(List<T> L, int x, int y) {
		Collections.swap(L, x, y);
	}

}
