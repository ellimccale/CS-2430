package main;

import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.ToIntFunction;

import static main.permutations.Permutations.generatePermutations;
import main.sort.HeapSort;
import main.sort.MergeSort;
import main.sort.QuickSort;
import main.sort.ShakerSort;

/**
 * @author Elli Steck
 */
public class Main {

	public static void main(String[] args) {

		Map<String, ToIntFunction<int[]>> sortTypes = new LinkedHashMap<>();
		sortTypes.put("HeapSort", HeapSort::sort);
		sortTypes.put("MergeSort", MergeSort::sort);
		sortTypes.put("QuickSort", QuickSort::sort);
		sortTypes.put("ShakerSort", ShakerSort::sort);

		generateResultsTable(sortTypes);

	}

	private static void generateResultsTable(Map<String, ToIntFunction<int[]>> sortTypes) {
		final int COL_WIDTH = 18;
		final int[] INPUT_SIZES = { 4, 6, 8 };

		String divider = "-".repeat(COL_WIDTH * (INPUT_SIZES.length + 1));
		String cell = "%-" + COL_WIDTH + "s";

		System.out.println("Comparison counts (min / max / avg)");
		System.out.println(divider);
		System.out.printf(cell, "Algorithm");
		for (int n : INPUT_SIZES) {
			System.out.printf(cell, "n = " + n);
		}
		System.out.printf("%n%s%n", divider);

		for (Map.Entry<String, ToIntFunction<int[]>> entry : sortTypes.entrySet()) {
			String name = entry.getKey();
			ToIntFunction<int[]> sort = entry.getValue();

			System.out.printf(cell, name);

			for (int n : INPUT_SIZES) {
				IntSummaryStatistics stats = new IntSummaryStatistics();

				for (int[] permutation : generatePermutations(n)) {
					int count = sort.applyAsInt(permutation.clone());
					stats.accept(count);
				}

				String result = String.format("%2d / %2d / %3.1f",
						stats.getMin(), stats.getMax(), stats.getAverage());
				System.out.printf(cell, result);
			}

			System.out.printf("%n%s%n", divider);
		}
	}

}
