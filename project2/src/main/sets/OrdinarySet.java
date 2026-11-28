/*
 * Name:        Elli Steck
 * Course:      CS-2430, Section 501
 * Project:     Programming Project 2 – Summer 2026
 * File:        OrdinarySet.java
 * Description: Represents an ordinary set as a Boolean array of the universal
 *              set, and implements complement, union, intersection, difference,
 *              and symmetric difference as operations on the set.
 */
package main.sets;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * An subset of the universal set, stored as a Boolean array.
 * <p>
 * There is one entry per element of the universal set, where the entry at index
 * {@code i} is {@code true} when an element of this set is found within
 * {@code UniversalSet} and {@code false} otherwise.
 * <p>
 * Instances are immutable and operations return a new {@code OrdinarySet} for
 * every result.
 */
public final class OrdinarySet {

	private final boolean[] bools;

	/**
	 * Creates a set containing the named elements. Calling with no arguments
	 * creates an empty set.
	 * <p>
	 * Names are matched on {@code UniversalSet.indexOf()}, which ignores letter
	 * casing and strips whitespace.
	 * 
	 * @param names the names of the elements to include in this new set
	 * @throws IllegalArgumentException if any name is {@code null}, blank, or not
	 *                                  an element of the universal set
	 */
	public OrdinarySet(String... names) {
		this.bools = new boolean[UniversalSet.size()];

		for (String name : names) {
			int index = UniversalSet.indexOf(name);

			if (index == -1) {
				throw new IllegalArgumentException("Not an element of the universal set: " + name);
			}

			this.bools[index] = true;
		}
	}

	/**
	 * Wraps a trusted array computed by one of this class's operations.
	 * <p>
	 * This constructor is private because it trusts its argument: it does not check
	 * the array's length or contents, or copy it.
	 * 
	 * @param bools the array to wrap
	 */
	private OrdinarySet(boolean[] bools) {
		this.bools = bools;
	}

	/**
	 * Returns the complement of this set compared to the universal set: every
	 * element of the universal set that is not part of this set.
	 * 
	 * @return a new set containing the complement
	 */
	public OrdinarySet complement() {
		boolean[] result = new boolean[UniversalSet.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = !this.bools[i];
		}

		return new OrdinarySet(result);
	}

	/**
	 * Returns the union of this set and {@code other}: every element that is in
	 * either set.
	 * 
	 * @param other the set to combine with this one
	 * @return a new set containing the union
	 */
	public OrdinarySet union(OrdinarySet other) {
		boolean[] result = new boolean[UniversalSet.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = this.bools[i] || other.bools[i];
		}

		return new OrdinarySet(result);
	}

	/**
	 * Returns the intersection of this set and {@code other}: every element that is
	 * in both this set and {@code other}.
	 * 
	 * @param other the set to combine with this one
	 * @return a new set containing the intersection
	 */
	public OrdinarySet intersection(OrdinarySet other) {
		boolean[] result = new boolean[UniversalSet.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = this.bools[i] && other.bools[i];
		}

		return new OrdinarySet(result);
	}

	/**
	 * Returns the difference of this set and {@code other}: every element that is
	 * in this set but not in {@code other}.
	 * 
	 * @param other the set whose elements are removed from this one
	 * @return a new set containing the difference
	 */
	public OrdinarySet difference(OrdinarySet other) {
		boolean[] result = new boolean[UniversalSet.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = this.bools[i] && !other.bools[i];
		}

		return new OrdinarySet(result);
	}

	/**
	 * Returns the symmetric difference of this set and {@code other}: every element
	 * that is in exactly one of the two sets, but not both. This operation is the
	 * equivalent of (A - B) ∪ (B - A).
	 * 
	 * @param other the set to combine with this one
	 * @return a new set containing the symmetric difference
	 */
	public OrdinarySet symmetricDifference(OrdinarySet other) {
		boolean[] result = new boolean[UniversalSet.size()];

		for (int i = 0; i < result.length; i++) {
			result[i] = this.bools[i] != other.bools[i];
		}

		return new OrdinarySet(result);
	}

	/**
	 * Returns this set's representation as a bit string: a 1 or a 0 per element of
	 * the universal set, in index order, where 1 represents that the element is
	 * present within this set and 0 represents its absence.
	 * 
	 * @return the bit string of length {@code UniversalSet.size()}
	 */
	public String toBitString() {
		StringBuilder sb = new StringBuilder();

		for (boolean el : this.bools) {
			sb.append(el ? 1 : 0);
		}

		return sb.toString();
	}

	/**
	 * Returns the names of the elements in this set, in universal set index order,
	 * formatted as {@code { A, B, C }}.
	 * 
	 * @return the element-name listing of this set
	 */
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "{ ", " }");

		for (int i = 0; i < UniversalSet.size(); i++) {
			if (this.bools[i]) {
				sj.add(UniversalSet.elementAt(i));
			}
		}

		return sj.toString();
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(bools);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OrdinarySet)) {
			return false;
		}
		OrdinarySet other = (OrdinarySet) obj;
		return Arrays.equals(bools, other.bools);
	}

}
