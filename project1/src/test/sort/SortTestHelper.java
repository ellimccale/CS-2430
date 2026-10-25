package test.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	 * Generates all permutations of 1..n and returns them in a pseudo-random
	 * order. The same n and seed always produce the same order, so test
	 * failures can be reproduced.
	 *
	 * @param n    the size of each permutation
	 * @param seed the seed for the random number generator
	 * @return a new, mutable list containing every permutation of 1..n in
	 *         shuffled order
	 */
	public static List<int[]> shuffledPermutations(int n, long seed) {
		List<int[]> list = new ArrayList<>(
				new Permutations().generatePermutations(n));
		Collections.shuffle(list, new Random(seed));

		return list;
	}

	/**
	 * Returns a sorted copy of the given list, ordered lexicographically.
	 *
	 * @param list the list to copy and sort
	 * @return a new, sorted list containing the same elements as the passed list
	 */
	public static List<int[]> sortedCopy(List<int[]> list) {
		List<int[]> copy = new ArrayList<>(list);
		copy.sort(Arrays::compare);

		return copy;
	}
}
