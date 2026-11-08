package test.sort;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import main.permutations.Permutations;

/**
 * Shared helpers for the sort tests. This class is not meant to be
 * instantiated.
 */
public final class SortTestHelper {

	private SortTestHelper() {
	}

	/**
	 * Picks a pseudo-random permutation of 1..n. The same n and seed always
	 * produce the same permutation, so test failures can be reproduced.
	 *
	 * @param n    the size of the permutation
	 * @param seed the seed for the random number generator
	 * @return a new array containing one permutation of 1..n
	 */
	public static int[] shuffledPermutation(int n, long seed) {
		List<int[]> list = new Permutations().generatePermutations(n);
		int index = new Random(seed).nextInt(list.size());

		return list.get(index).clone();
	}

	/**
	 * Returns a sorted copy of the given array, in ascending order.
	 *
	 * @param array the array to copy and sort
	 * @return a new, sorted array containing the same elements as the passed
	 *         array
	 */
	public static int[] sortedCopy(int[] array) {
		int[] copy = array.clone();
		Arrays.sort(copy);

		return copy;
	}
}