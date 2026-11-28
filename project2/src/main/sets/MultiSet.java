/*
 * Name:        Elli Steck
 * Course:      CS-2430, Section 501
 * Project:     Programming Project 2 – Summer 2026
 * File:        MultiSet.java
 * Description: Represents a multiset, or bag, as an array of element counts of
 *              the universal set, and implements union, intersection,
 *              sum, and difference as operations on the multiset.
 */
package main.sets;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * A multiset of the universal set, stored as an array of counts.
 * <p>
 * There is one entry per element of the universal set, where the entry at index
 * {@code i} is the number of times {@code UniversalSet.elementAt(i)} is found
 * within the multiset and {@code 0} otherwise.
 * <p>
 * Instances are immutable and operations return a new {@code MultiSet} for
 * every result.
 */
public final class MultiSet {

	private final int[] counts;

	/**
	 * Creates a multiset containing the named elements. Each occurrence of a name
	 * adds one to that element's count, so
	 * {@code new MultiSet("Snow College", "Snow College")} contains Snow College
	 * twice. Calling with no arguments creates an empty multiset.
	 * <p>
	 * Names are matched on {@code UniversalSet.indexOf()}, which ignores letter
	 * casing and strips whitespace.
	 * 
	 * @param names the names of the elements to include in this new set, repeated
	 *              once per occurrence.
	 * @throws IllegalArgumentException if any name is {@code null}, blank, or not
	 *                                  an element of the universal set
	 */
	public MultiSet(String... names) {
		this.counts = new int[UniversalSet.size()];

		for (String name : names) {
			int index = UniversalSet.indexOf(name);

			if (index == -1) {
				throw new IllegalArgumentException("Not an element of the universal set: " + name);
			}

			this.counts[index]++;
		}
	}

	/**
	 * Wraps a trusted array computed by one of this class's operations.
	 * <p>
	 * This constructor is private because it trusts its argument: it does not check
	 * the array's length or contents, or copy it.
	 * 
	 * @param counts the array to wrap
	 */
	private MultiSet(int[] counts) {
		this.counts = counts;
	}

	/**
	 * Returns the multiset union of this multiset and {@code other}: each element
	 * appears as many times as its larger count in the two multisets.
	 * 
	 * @param other the multiset to combine with this one
	 * @return a new multiset containing the union
	 */
	public MultiSet union(MultiSet other) {
		int[] result = new int[UniversalSet.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = Math.max(this.counts[i], other.counts[i]);
		}

		return new MultiSet(result);
	}

	/**
	 * Returns the multiset intersection of this multiset and {@code other}: each
	 * element appears as many times as its smaller count in the two multisets.
	 * 
	 * @param other the multiset to combine with this one
	 * @return a new multiset containing the intersection
	 */
	public MultiSet intersection(MultiSet other) {
		int[] result = new int[UniversalSet.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = Math.min(this.counts[i], other.counts[i]);
		}

		return new MultiSet(result);
	}

	/**
	 * Returns the multiset sum of this multiset and {@code other}: each element
	 * appears as many times as its two counts added together.
	 * 
	 * @param other the multiset to add to this one
	 * @return a new multiset containing the sum
	 */
	public MultiSet sum(MultiSet other) {
		int[] result = new int[UniversalSet.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = this.counts[i] + other.counts[i];
		}

		return new MultiSet(result);
	}

	/**
	 * Returns the multiset difference of this multiset minus {@code other}: each
	 * element's count in {@code other} is subtracted from its count in this
	 * multiset, with any result below zero set to zero.
	 * 
	 * @param other the multiset whose counts are subtracted from this one
	 * @return a new multiset containing the difference
	 */
	public MultiSet difference(MultiSet other) {
		int[] result = new int[UniversalSet.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = Math.max(0, this.counts[i] - other.counts[i]);
		}

		return new MultiSet(result);
	}

	/**
	 * Returns this multiset's representation as a string of comma-separated
	 * integers: each element's count within this multiset, in index order, where 0
	 * represents that the element is absent from this multiset. Formatted as
	 * {@code [1, 3, 7, ...]}.
	 * 
	 * @return the counts, with one entry per element of the universal set
	 */
	public String toCountString() {
		return Arrays.toString(this.counts);
	}

	/**
	 * Returns the names of the elements in this multiset, in universal set index
	 * order, formatted as { A x1, B x2, C x3 }, where the number after x is how
	 * many times that element appears in this multiset. Elements with a count of 0
	 * are omitted.
	 * 
	 * @return the element-name listing of this multiset
	 */
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "{ ", " }");

		for (int i = 0; i < UniversalSet.size(); i++) {
			if (this.counts[i] > 0) {
				sj.add(UniversalSet.elementAt(i) + " x" + this.counts[i]);
			}
		}

		return sj.toString();
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(counts);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MultiSet)) {
			return false;
		}
		MultiSet other = (MultiSet) obj;
		return Arrays.equals(counts, other.counts);
	}

}
