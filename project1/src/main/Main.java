package main;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static main.permutations.Permutations.generatePermutations;
import main.sort.HeapSort;
import main.sort.MergeSort;
import main.sort.QuickSort;
import main.sort.ShakerSort;

/**
 * @author Elli Steck
 */
public class Main {

	private static final int[] INPUT_SIZES = { 4, 6, 8 };

	public static void main(String[] args) {

		Map<String, Consumer<int[]>> sortTypes = new LinkedHashMap<>();
		sortTypes.put("HeapSort", HeapSort::sort);
		sortTypes.put("MergeSort", MergeSort::sort);
		sortTypes.put("QuickSort", QuickSort::sort);
		sortTypes.put("ShakerSort", ShakerSort::sort);

		List<int[]> L = generatePermutations();

		generateResultsTable(sortTypes);

	}

	private static void generateResultsTable(Map<String, Consumer<int[]>> sortTypes) {
		final int COL_WIDTH = 18;

		String divider = "-".repeat(COL_WIDTH * (INPUT_SIZES.length + 1));
		String cell = "%-" + COL_WIDTH + "s";

		System.out.println("Comparison counts (min / max / avg)");
		System.out.println(divider);
		System.out.printf(cell, "Algorithm");
		for (int n : INPUT_SIZES) {
			System.out.printf(cell, "n = " + n);
		}
		System.out.printf("%n%s%n", divider);

		for (Map.Entry<String, Consumer<int[]>> entry : sortTypes.entrySet()) {
			System.out.printf(cell, entry.getKey());

			for (int n : INPUT_SIZES) {
				System.out.printf(cell, "0 / 0 / 0");
			}

			System.out.printf("%n%s%n", divider);
		}
	}

}
