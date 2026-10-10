package main.utils;

import java.util.Collections;
import java.util.List;

/**
 * Static utility methods for performing operations on arrays and lists. This
 * class is not meant to be instantiated.
 * 
 * @author Elli Steck
 */
public final class ArrayUtils {

	private ArrayUtils() {
	}

	/**
	 * Swaps the elements of an array at indices x and y.
	 *
	 * @param array the array with elements to swap
	 * @param x     the first index
	 * @param y     the second index
	 */
	public static void swap(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

	/**
	 * Swaps the elements of an array at indices x and y.
	 *
	 * @param array the array with elements to swap
	 * @param x     the first index
	 * @param y     the second index
	 * @param <T>   the type of elements in the array
	 */
	public static <T> void swap(T[] array, int x, int y) {
		T temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

	/**
	 * Swaps the elements of a list at indices x and y.
	 *
	 * @param list the list with elements to swap
	 * @param x    the first index
	 * @param y    the second index
	 * @param <T>  the type of elements in the list
	 */
	public static <T> void swap(List<T> list, int x, int y) {
		Collections.swap(list, x, y);
	}

}
