package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.permutations.Permutations;
import main.sort.MergeSort;

/**
 * @author Elli Steck
 */
public class Main {

	private static final int[] INPUT_SIZES = { 4, 6, 8 };

	public static void main(String[] args) {

		Permutations P = new Permutations();
		List<int[]> L = P.generatePermutations(3);

		// Visual sanity check that these results match the JS version
		for (int[] list : L) {
			System.out.print(Arrays.toString(list));
		}
		System.out.println();

		List<int[]> LClone = new ArrayList<>(L);
		MergeSort.sort(LClone);
		for (int[] list : LClone) {
			System.out.print(Arrays.toString(list));
		}
		System.out.println();

	}

}
