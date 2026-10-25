package test.sort;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.permutations.Permutations;
import main.sort.MergeSort;

import static test.sort.SortTestHelper.shuffledPermutations;
import static test.sort.SortTestHelper.sortedCopy;

class MergeSortTest {

	private Permutations permutations;

	@BeforeEach
	void setUp() {
		permutations = new Permutations();
	}

	@Test
	@DisplayName("should sort shuffled permutations in the expected order")
	void sorts_shuffled_permutations() {
		List<int[]> expected = sortedCopy(permutations.generatePermutations(3));
		List<int[]> actual = shuffledPermutations(3, 42L);

		MergeSort.sort(actual);

		// Size should remain the same
		assertEquals(expected.size(), actual.size());

		// Order should be as expected
		assertArrayEquals(expected.toArray(), actual.toArray());
	}

	@Test
	@DisplayName("should leave an already sorted list unchanged")
	void sorted_input_unchanged() {
		List<int[]> expected = sortedCopy(permutations.generatePermutations(4));
		List<int[]> actual = new ArrayList<>(expected);

		MergeSort.sort(actual);

		assertArrayEquals(expected.toArray(), actual.toArray());
	}

	@Test
	@DisplayName("should handle empty and single-element lists")
	void handles_trivial_sizes() {
		List<int[]> empty = new ArrayList<>();
		MergeSort.sort(empty);
		assertTrue(empty.isEmpty());

		int[] only = { 1 };
		List<int[]> single = new ArrayList<>(List.of(only));
		MergeSort.sort(single);
		assertEquals(1, single.size());
		assertSame(only, single.get(0));
	}

	@Test
	@DisplayName("should match List.sort for many sizes and seeds")
	void matches_reference_sort() {
		for (int n = 1; n <= 7; n++) {
			for (long seed = 0; seed < 5; seed++) {
				List<int[]> actual = shuffledPermutations(n, seed);
				List<int[]> expected = sortedCopy(actual);

				MergeSort.sort(actual);

				assertArrayEquals(expected.toArray(), actual.toArray(),
						"n=" + n + ", seed=" + seed);
			}
		}
	}

}
