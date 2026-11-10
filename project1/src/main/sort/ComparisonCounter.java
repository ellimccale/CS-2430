/*
 * Name:        Elli Steck
 * Course:      CS 2430, Section 501
 * Project:     Programming Project 1 – Summer 2026
 * File:        ComparisonCounter.java
 * Description: Counts comparisons made during one sort run.
 */
package main.sort;

/**
 * Counts comparisons made during one sort run.
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
