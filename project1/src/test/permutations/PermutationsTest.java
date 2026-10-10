package test.permutations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.permutations.Permutations;

class PermutationsTest {

	private Permutations permutations;

	@BeforeEach
	void setUp() {
		permutations = new Permutations();
	}

	// -------------------------------------------------------------------------
	// Helpers
	// -------------------------------------------------------------------------

	private static int[] identity(int n) {
		int[] result = new int[n];

		for (int i = 0; i < n; i++) {
			result[i] = i + 1;
		}

		return result;
	}

	private int factorial(int n) {
		if (n == 1) {
			return n;
		}

		return n * factorial(n - 1);
	}

	// -------------------------------------------------------------------------
	// Tests
	// -------------------------------------------------------------------------

	@ParameterizedTest(name = "n = {0}")
	@ValueSource(ints = { -1, 0, 11 })
	@DisplayName("should throw an IllegalArgumentException when n < 1 or > 10")
	void invalid_argument_throws_expected_exception(int n) {
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class, () -> {
					permutations.generatePermutations(n);
				});
		assertEquals("n must be positive and no larger than 10", exception.getMessage());
	}

	@Test
	@DisplayName("should generate the expected order for Heap's algorithm")
	void generates_expected_permutations_order_for_heaps_algorithm() {
		List<int[]> expected = Arrays
				.asList(new int[][] {
					{ 1, 2, 3 },
					{ 2, 1, 3 },
					{ 3, 1, 2 },
					{ 1, 3, 2 },
					{ 2, 3, 1 },
					{ 3, 2, 1 } });
		List<int[]> actual = permutations.generatePermutations(3);
		assertArrayEquals(expected.toArray(), actual.toArray());
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7, 8 })
	@DisplayName("should generate the expected permutations of n")
	void generates_expected_permutations_of_n(int n) {
		int[] expected = identity(n);
		List<int[]> actual = permutations.generatePermutations(n);

		// Size should be correct
		assertEquals(factorial(n), actual.size());

		// First entry should be the identity array
		assertArrayEquals(expected, actual.get(0));

		// Each entry should be an arrangement of 1..n with no repeats
		Set<String> seen = new HashSet<>();
		for (int[] permutation : actual) {
			// Compare sorted arrangement with defensive clone
			int[] clone = permutation.clone();
			Arrays.sort(clone);
			assertArrayEquals(expected, clone,
					"entry is not an arrangement of 1..n: " + Arrays.toString(permutation));

			// Store by string value
			boolean isNew = seen.add(Arrays.toString(permutation));
			assertTrue(isNew, "duplicate permutation: " + Arrays.toString(permutation));
		}
	}

	@Test
	@DisplayName("should default to n=4 when no arguments are passed")
	void no_args_defaults_to_n_equals_4() {
		List<int[]> expected = permutations.generatePermutations(4);
		List<int[]> actual = permutations.generatePermutations();

		// Size should be correct
		assertEquals(factorial(4), actual.size());
		assertEquals(expected.size(), actual.size());

		// Each entry should match
		for (int i = 0; i < actual.size(); i++) {
			assertArrayEquals(expected.get(i), actual.get(i));
		}
	}

}
