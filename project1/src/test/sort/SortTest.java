/*
 * Name:        Elli Steck
 * Course:      CS 2430, Section 501
 * Project:     Programming Project 1 – Summer 2026
 * File:        SortTest.java
 * Description: Unit tests for the four sorting algorithms.
 */
package test.sort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static test.sort.SortTestHelper.shuffledPermutation;
import static test.sort.SortTestHelper.sortedCopy;

import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static main.permutations.Permutations.generatePermutations;
import main.sort.HeapSort;
import main.sort.MergeSort;
import main.sort.QuickSort;
import main.sort.ShakerSort;

class SortTest {

	static Stream<Arguments> sortTypes() {
		return Stream.of(
			Arguments.of("MergeSort", (Consumer<int[]>) MergeSort::sort),
			Arguments.of("QuickSort", (Consumer<int[]>) QuickSort::sort),
			Arguments.of("HeapSort", (Consumer<int[]>) HeapSort::sort),
			Arguments.of("ShakerSort", (Consumer<int[]>) ShakerSort::sort)
		);
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("sortTypes")
	@DisplayName("should sort every permutation of 1..3 into ascending order")
	void sorts_every_permutation(String name, Consumer<int[]> sortType) {
		int[] expected = { 1, 2, 3 };

		for (int[] permutation : generatePermutations(3)) {
			int[] actual = permutation.clone();

			sortType.accept(actual);

			// Size should remain the same
			assertEquals(expected.length, actual.length);

			// Order should be as expected
			assertArrayEquals(expected, actual);
		}
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("sortTypes")
	@DisplayName("should leave an already sorted array unchanged")
	void sorted_input_unchanged(String name, Consumer<int[]> sortType) {
		int[] expected = { 1, 2, 3, 4 };
		int[] actual = expected.clone();

		sortType.accept(actual);

		assertArrayEquals(expected, actual);
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("sortTypes")
	@DisplayName("should handle empty and single-element arrays")
	void handles_trivial_sizes(String name, Consumer<int[]> sortType) {
		int[] empty = {};
		sortType.accept(empty);
		assertEquals(0, empty.length);

		int[] single = { 1 };
		sortType.accept(single);
		assertArrayEquals(new int[] { 1 }, single);
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("sortTypes")
	@DisplayName("should match Arrays.sort for many sizes and seeds")
	void matches_reference_sort(String name, Consumer<int[]> sortType) {
		for (int n = 1; n <= 8; n++) {
			for (long seed = 0; seed < 5; seed++) {
				int[] actual = shuffledPermutation(n, seed);
				int[] expected = sortedCopy(actual);

				sortType.accept(actual);

				assertArrayEquals(expected, actual, "n=" + n + ", seed=" + seed);
			}
		}
	}

}