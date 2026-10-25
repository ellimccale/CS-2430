package test.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.utils.ArrayUtils;

class ArrayUtilsTest {

	// -------------------------------------------------------------------------
	// Primitive arrays
	// -------------------------------------------------------------------------

	@Nested
	@DisplayName("primitive array swap")
	class PrimitiveArraySwap {

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

	// -------------------------------------------------------------------------
	// Generic arrays
	// -------------------------------------------------------------------------

	@Nested
	@DisplayName("generic array swap")
	class GenericArraySwap {

		private Integer[] array;

		@BeforeEach
		void setUp() {
			array = new Integer[] { 1, 2, 3, 4, 5 };
		}

		@Test
		@DisplayName("should swap two elements in an array")
		void swap_array_swaps_two_elements() {
			ArrayUtils.swap(array, 0, 4);
			assertArrayEquals(new Integer[] { 5, 2, 3, 4, 1 }, array);
		}

		@Test
		@DisplayName("should be a no-op when x equals y")
		void swap_array_same_index_no_op() {
			ArrayUtils.swap(array, 2, 2);
			assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5 }, array);
		}

		@Test
		@DisplayName("should throw when an index is out of bounds")
		void swap_array_throws_out_of_bounds() {
			assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
				ArrayUtils.swap(array, 0, 10);
			});
		}

	}

	// -------------------------------------------------------------------------
	// Lists
	// -------------------------------------------------------------------------

	@Nested
	@DisplayName("list swap")
	class ListSwap {

		private List<Integer> list;

		@BeforeEach
		void setUp() {
			list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		}

		@Test
		@DisplayName("should swap two elements in a list")
		void swap_list_swaps_two_elements() {
			ArrayUtils.swap(list, 1, 3);
			assertEquals(List.of(1, 4, 3, 2, 5), list);
		}

		@Test
		@DisplayName("should be a no-op when x equals y")
		void swap_list_same_index_no_op() {
			ArrayUtils.swap(list, 2, 2);
			assertEquals(List.of(1, 2, 3, 4, 5), list);
		}

		@Test
		@DisplayName("should throw when an index is out of bounds")
		void swap_list_throws_out_of_bounds() {
			assertThrows(IndexOutOfBoundsException.class, () -> {
				ArrayUtils.swap(list, 0, 10);
			});
		}

	}

}