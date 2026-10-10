package main;

import java.util.Arrays;
import java.util.List;

import main.permutations.Permutations;

/**
 * @author Elli Steck
 */
public class Main {

	private static final int[] INPUT_SIZES = { 4, 6, 8 };

	public static void main(String[] args) {

		Permutations P = new Permutations();
		List<int[]> L = P.generatePermutations(3);

		// Visual sanity check that this list matches the JS version
		for (int[] list : L) {
			System.out.print(Arrays.toString(list));
		}

	}

}
