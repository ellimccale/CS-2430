package main.permutations;

import java.util.ArrayList;
import java.util.List;

import static main.utils.ArrayUtils.swap;

/**
 * Generates all permutations of the integers 1 through n using Heap's
 * algorithm. n defaults to 4.
 * 
 * @author Elli Steck
 */
public final class Permutations {

	private static final int MAX_N = 10;

	/**
	 * Generates all permutations of the integers 1 through 4.
	 * 
	 * @return a list containing all permutations
	 */
	public List<int[]> generatePermutations() {
		return generatePermutations(4);
	}

	/**
	 * Generates all permutations of the integers 1 through n.
	 * 
	 * @param n the length of the list to generate
	 * @return a list containing all permutations
	 * @throws IllegalArgumentException when n is less than 1 or greater than 10
	 *                                  (n > 10 may exceed heap capacity and
	 *                                  n > 12 exceeds list size)
	 */
	public List<int[]> generatePermutations(int n) {
		if (n < 1 || n > MAX_N) {
			throw new IllegalArgumentException(
					"n must be positive and no larger than " + MAX_N);
		}

		List<int[]> permutations = new ArrayList<>();

		int[] src = new int[n];
		for (int i = 0; i < n; i++) {
			src[i] = i + 1;
		}

		permutations.add(src.clone());
		permute(n, src, permutations);

		return permutations;
	}

	/**
	 * Recursively generates a list of all permutations containing numbers 1
	 * through n using Heap's algorithm, and adds a clone of each new
	 * arrangement to the results list.
	 * 
	 * Source: Heap's algorithm on Wikipedia
	 * 
	 * @param k        the number of elements to permute
	 * @param elements the array of elements being permuted
	 * @param result   the resulting list of permutations
	 */
	private void permute(int k, int[] elements, List<int[]> result) {
		if (k == 1) {
			return;
		}

		permute(k - 1, elements, result);

		for (int i = 0; i < k - 1; i++) {
			if (k % 2 == 0) {
				swap(elements, i, k - 1);
			} else {
				swap(elements, 0, k - 1);
			}

			result.add(elements.clone());
			permute(k - 1, elements, result);
		}
	}

}
