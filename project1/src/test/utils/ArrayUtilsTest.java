/*
 * Name:        Elli Steck
 * Course:      CS 2430, Section 501
 * Project:     Programming Project 1 – Summer 2026
 * File:        ArrayUtilsTest.java
 * Description: Unit tests for the array and list swap utilities.
 */
package test.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.utils.ArrayUtils;

class ArrayUtilsTest {

	private int[] array;

	@BeforeEach
	void setUp() {
		array = new int[] { 1, 2, 3, 4, 5 };
	}

	@Test
	@DisplayName("should swap two elements in an array")
	void swap_array_swaps_two_elements() {
		ArrayUtils.swap(array, 0, 4);
		assertArrayEquals(new int[] { 5, 2, 3, 4, 1 }, array);
	}

	@Test
	@DisplayName("should be a no-op when x equals y")
	void swap_array_same_index_no_op() {
		ArrayUtils.swap(array, 2, 2);
		assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, array);
	}

	@Test
	@DisplayName("should throw when an index is out of bounds")
	void swap_array_throws_out_of_bounds() {
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			ArrayUtils.swap(array, 0, 10);
		});
	}

}