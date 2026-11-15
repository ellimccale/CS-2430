# Project 1 — Sorting Algorithm Comparison

CS 2430: Discrete Structures — Programming Project 1, Summer 2026

Implements and compares merge sort, quick sort, heap sort, and shaker sort by
counting element comparisons across input sizes n = 4, 6, and 8. Every
permutation of the integers 1..n is generated and sorted by each algorithm,
so the results table reports exact minimum, maximum, and average comparison
counts.

## Requirements

- Java 11 or newer (verified with OpenJDK 11.0.26)
- No external dependencies for building and running the program

## Build and Run

From `/project1`:

	javac -d out src/main/Main.java src/main/sort/*.java src/main/permutations/*.java src/main/utils/*.java
	java -cp out main.Main

No command-line arguments; input sizes and data generation are hard-coded.

The program prints a min/max/avg summary table to the console and writes a
full per-run log (input, sorted output, and comparison count for every
permutation) to `output/results.txt`, creating the folder if needed. The file
is overwritten on each run. To capture the console output:

	java -cp out main.Main > output/console.txt

In Eclipse: right-click `Main.java` -> Run As -> Java Application. Eclipse's
default working directory is the project root, which is where the relative
output path expects to run.

## Tests

JUnit 5 tests live in `src/test`:
- `sort/SortTest` — every permutation of 1..3, a cross-check against
  `Arrays.sort` for n = 1–8 across 5 seeds, and empty/single/sorted inputs
- `permutations/PermutationsTest` — factorial counts, uniqueness, Heap's
  algorithm ordering, argument validation
- `utils/ArrayUtilsTest` — swap behavior and out-of-bounds handling

The suite runs from Eclipse, which reads the bundled `.project` and
`.classpath` (they reference the JUnit 5 container): import the repository as
an existing Eclipse project, then right-click a test class or `src/test` ->
Run As -> JUnit Test. Building and running the program itself does not
require JUnit.

## Files

	src/main/Main.java                        entry point; runs the experiment and builds the summary table
	src/main/sort/MergeSort.java              top-down merge sort
	src/main/sort/QuickSort.java              quick sort, last-element pivot (Lomuto partition)
	src/main/sort/HeapSort.java               in-place heap sort using a max-heap
	src/main/sort/ShakerSort.java             shaker (cocktail) sort
	src/main/sort/ComparisonCounter.java      counts every two-element comparison
	src/main/permutations/Permutations.java   all permutations of 1..n (Heap's algorithm)
	src/main/utils/ArrayUtils.java            swap helper
	src/test/...                              JUnit 5 test suite
	output/results.txt                        full per-run log from the latest run

## Notes

- Data generation uses all n! permutations of the integers 1..n rather than
  random samples, so the min/max/avg counts are exact for each algorithm and
  size. The generator accepts n up to 10.
- Counting rule: every execution of a comparison between two data elements is
  counted once, in every algorithm. Merge sort's array copies and quicksort's
  final pivot-placement swap involve no comparisons, so nothing else counts.
- Sorts modify the input array in place and return the comparison count.
