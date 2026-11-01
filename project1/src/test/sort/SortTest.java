package test.sort;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static test.sort.SortTestHelper.shuffledPermutations;
import static test.sort.SortTestHelper.sortedCopy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import main.permutations.Permutations;
import main.sort.MergeSort;
import main.sort.QuickSort;

class SortTest {

	private Permutations permutations;

	@BeforeEach
	void setUp() {
		permutations = new Permutations();
	}

	static Stream<Arguments> sortTypes() {
		return Stream.of(
			Arguments.of("MergeSort", (Consumer<List<int[]>>) MergeSort::sort),
			Arguments.of("QuickSort", (Consumer<List<int[]>>) QuickSort::sort)
		);
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("sortTypes")
	@DisplayName("should sort shuffled permutations in the expected order")
	void sorts_shuffled_permutations(String name, Consumer<List<int[]>> sortType) {
		List<int[]> expected = sortedCopy(permutations.generatePermutations(3));
		List<int[]> actual = shuffledPermutations(3, 42L);

		sortType.accept(actual);

		// Size should remain the same
		assertEquals(expected.size(), actual.size());

		// Order should be as expected
		assertArrayEquals(expected.toArray(), actual.toArray());
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("sortTypes")
	@DisplayName("should leave an already sorted list unchanged")
	void sorted_input_unchanged(String name, Consumer<List<int[]>> sortType) {
		List<int[]> expected = sortedCopy(permutations.generatePermutations(4));
		List<int[]> actual = new ArrayList<>(expected);

		sortType.accept(actual);

		assertArrayEquals(expected.toArray(), actual.toArray());
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("sortTypes")
	@DisplayName("should handle empty and single-element lists")
	void handles_trivial_sizes(String name, Consumer<List<int[]>> sortType) {
		List<int[]> empty = new ArrayList<>();
		sortType.accept(empty);
		assertTrue(empty.isEmpty());

		int[] only = { 1 };
		List<int[]> single = new ArrayList<>(List.of(only));
		sortType.accept(single);
		assertEquals(1, single.size());
		assertSame(only, single.get(0));
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("sortTypes")
	@DisplayName("should match List.sort for many sizes and seeds")
	void matches_reference_sort(String name, Consumer<List<int[]>> sortType) {
		for (int n = 1; n <= 7; n++) {
			for (long seed = 0; seed < 5; seed++) {
				List<int[]> actual = shuffledPermutations(n, seed);
				List<int[]> expected = sortedCopy(actual);

				sortType.accept(actual);

				assertArrayEquals(expected.toArray(), actual.toArray(),
						"n=" + n + ", seed=" + seed);
			}
		}
	}

}
