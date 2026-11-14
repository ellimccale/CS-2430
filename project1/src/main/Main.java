/*
 * Name:        Elli Steck
 * Course:      CS 2430, Section 501
 * Project:     Programming Project 1 – Summer 2026
 * File:        Main.java
 * Description: Runs all four sorts on every permutation of n = 4, 6, and 8,
 *              and prints the results table.
 */
package main;

import static main.permutations.Permutations.generatePermutations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

import main.sort.HeapSort;
import main.sort.MergeSort;
import main.sort.QuickSort;
import main.sort.ShakerSort;

/**
 * Entry-point to the program. Runs all four sorts on every permutation of
 * n = 4, 6, and 8, prints the results table to the console, and writes every
 * run to output/results.txt.
 */
public class Main {

	private static final int[] INPUT_SIZES = { 4, 6, 8 };

	private static final int CONSOLE_COL_WIDTH = 18;
	private static final int FILE_DIVIDER_WIDTH = 72;

	private static final Path OUTPUT_PATH = Path.of("output", "results.txt");

	public static void main(String[] args) {

		Map<String, ToIntFunction<int[]>> sortTypes = new LinkedHashMap<>();
		sortTypes.put("HeapSort", HeapSort::sort);
		sortTypes.put("MergeSort", MergeSort::sort);
		sortTypes.put("QuickSort", QuickSort::sort);
		sortTypes.put("ShakerSort", ShakerSort::sort);

		generateResults(sortTypes);

	}

	/**
	 * Runs each sorting algorithm on every permutation of each n in INPUT_SIZES.
	 * Prints a table of min, max, and average comparison counts to the console,
	 * and writes every individual run to the results file. Each permutation is
	 * sorted once, and that result is used by both outputs.
	 *
	 * @param sortTypes algorithm names mapped to their sort methods, in display
	 *                  order
	 */
	private static void generateResults(Map<String, ToIntFunction<int[]>> sortTypes) {

		// Formatting for the results.txt output
		String fileDivider = "-".repeat(FILE_DIVIDER_WIDTH);

		StringBuilder results = new StringBuilder();
		results.append(String.format("%s%n%s%n%s%n", fileDivider,
				"Comparison counts by algorithm name, n value", fileDivider));

		// Formatting for the console output
		String conDivider = "-".repeat(CONSOLE_COL_WIDTH * (INPUT_SIZES.length + 1));
		String conCell = "%-" + CONSOLE_COL_WIDTH + "s";

		StringBuilder console = new StringBuilder();
		console.append(String.format("%s%n%s%n%s%n", conDivider,
				"Comparison counts (min / max / avg)", conDivider));
		console.append(String.format(conCell, "Algorithm"));
		for (int n : INPUT_SIZES) {
			console.append(String.format(conCell, "n = " + n));
		}
		console.append(String.format("%n%s%n", conDivider));

		// Start iterating, sorting, and counting
		for (Map.Entry<String, ToIntFunction<int[]>> entry : sortTypes.entrySet()) {
			String name = entry.getKey();
			ToIntFunction<int[]> sort = entry.getValue();

			console.append(String.format(conCell, name));

			for (int n : INPUT_SIZES) {
				IntSummaryStatistics stats = new IntSummaryStatistics();
				List<int[]> permutations = generatePermutations(n);
				StringBuilder runs = new StringBuilder();

				// Sort a clone of each permutation
				for (int[] permutation : permutations) {
					int[] sorted = permutation.clone();

					int count = sort.applyAsInt(sorted);
					stats.accept(count);

					runs.append(String.format("%s -> %s  (%d comparisons)%n",
							Arrays.toString(permutation),
							Arrays.toString(sorted), count));
				}

				// Section for results.txt
				results.append(
						String.format("%nAlgorithm: %s, n = %d (%d runs)%n",
						name, n, permutations.size()));
				results.append(
						String.format("%nComparisons (min / max / avg): %d / %d / %.1f%n%n",
						stats.getMin(), stats.getMax(), stats.getAverage()));
				results.append(runs);
				results.append(String.format("%n%s%n", fileDivider));

				// Cell for the console table
				String conResult = String.format("%-2d / %-2d / %-3.1f", stats.getMin(), stats.getMax(),
						stats.getAverage());
				console.append(String.format(conCell, conResult));
			}

			console.append(String.format("%n%s%n", conDivider));
		}

		System.out.print(console);
		writeResults(results);
	}

	/**
	 * Writes the full results to OUTPUT_PATH, creating the output folder if it
	 * does not exist. Overwrites any existing file.
	 *
	 * @param results the text to write
	 */
	private static void writeResults(CharSequence results) {
		try {
			Files.createDirectories(OUTPUT_PATH.getParent());
			Files.writeString(OUTPUT_PATH, results);

			System.out.printf("%nResults written to %s%n",
					OUTPUT_PATH.toAbsolutePath());
		} catch (IOException e) {
			System.err.println("Could not write results: " + e.getMessage());
		}
	}

}