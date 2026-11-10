/*
 * Name:        Elli Steck
 * Course:      CS 2430, Section 501
 * Project:     Programming Project 1 – Summer 2026
 * File:        ArrayUtils.java
 * Description: Static utility methods for performing operations on arrays
 * 				and lists.
 */
package main.utils;

/**
 * Static utility methods for performing operations on arrays and lists. This
 * class is not meant to be instantiated.
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

}
