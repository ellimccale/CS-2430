package main.sort;

/**
 * Counts comparisons made during one sort run.
 * 
 * @author Elli Steck
 */
final class ComparisonCounter {

	private int count;

	/**
	 * Compares two values and records the comparison.
	 * 
	 * @param x the first integer to compare
	 * @param y the second integer to compare
	 * @return negative if x < y, zero if equal, positive if x > y
	 */
	int compare(int x, int y) {
		count++;
		return Integer.compare(x, y);
	}

	/**
	 * @return the comparison count for the current sort run
	 */
	int getCount() {
		return count;
	}

}
