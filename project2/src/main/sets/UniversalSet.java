/*
 * Name:        Elli Steck
 * Course:      CS-2430, Section 501
 * Project:     Programming Project 2 – Summer 2026
 * File:        UniversalSet.java
 * Description: Defines a fixed universal set (in this case, universities and
 *              colleges in Utah) as an array of Strings, with instance methods
 *              that expose array length, as well as element-to-index and
 *              index-to-element mapping.
 */
package main.sets;

/**
 * The universal set for this project: a fixed, ordered list of universities and
 * colleges in Utah. This class is not meant to be instantiated.
 */
public class UniversalSet {

	private static final String[] ELEMENTS = { "Brigham Young University", "Eagle Gate College", "Ensign College",
			"Salt Lake Community College", "Snow College", "Southern Utah University", "University of Utah",
			"Utah State University", "Utah Tech University", "Utah Valley University", "Weber State University",
			"Westminster University" };

	private UniversalSet() {
	}

	/**
	 * @return the number of elements in this universal set
	 */
	public static int size() {
		return ELEMENTS.length;
	}

	/**
	 * Returns the index of the given element in this universal set.
	 * 
	 * Matching ignores letter casing and leading or trailing whitespace. Partial
	 * element names do not match.
	 * 
	 * Callers of this method must check for -1 before using the result as an array
	 * index.
	 * 
	 * @param name the element name to look up (may be null)
	 * @return the zero-based index of the element, or -1 if {@code name} is null,
	 *         blank, or not an element of the set
	 */
	public static int indexOf(String name) {
		if (name == null) {
			return -1;
		}

		String trimmedName = name.trim();

		for (int i = 0; i < ELEMENTS.length; i++) {
			if (ELEMENTS[i].equalsIgnoreCase(trimmedName)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the element at the given index in this universal set, using the
	 * capitalization and spelling defined here.
	 * 
	 * @param index the zero-based index of the element
	 * @return the element name at {@code index}
	 * @throws IndexOutOfBoundsException if {@code index} is negative or greater
	 *                                   than or equal to {@code size()}
	 */
	public static String elementAt(int index) {
		int size = UniversalSet.size();

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(
					"Index " + index + " is out of bounds for universal set of size " + size);
		}

		return ELEMENTS[index];
	}

}
