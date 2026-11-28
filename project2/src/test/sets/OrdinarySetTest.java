/*
 * Name:        Elli Steck
 * Course:      CS-2430, Section 501
 * Project:     Programming Project 2 – Summer 2026
 * File:        OrdinarySetTest.java
 * Description: Unit tests for the OrdinarySet class.
 */
package test.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.sets.OrdinarySet;
import main.sets.UniversalSet;

class OrdinarySetTest {

	// -------------------------------------------------------------------------
	// Helpers
	// -------------------------------------------------------------------------

	private static OrdinarySet universe() {
		String[] names = new String[UniversalSet.size()];

		for (int i = 0; i < names.length; i++) {
			names[i] = UniversalSet.elementAt(i);
		}

		return new OrdinarySet(names);
	}

	@Test
	@DisplayName("should properly generate the universal set for other tests")
	void returns_expected_elements_from_universal_set() {
		assertEquals("111111111111", universe().toBitString());
	}

	// -------------------------------------------------------------------------
	// Constructor
	// -------------------------------------------------------------------------

	@ParameterizedTest
	@ValueSource(strings = { "Utah", "Stanford University", " ", "" })
	@DisplayName("should throw an exception when an element not belonging to the universal set is passed to the constructor")
	void throws_illegal_argument_exception_for_invalid_name(String name) {
		assertThrows(IllegalArgumentException.class, () -> new OrdinarySet(name));
	}

	@ParameterizedTest
	@ValueSource(strings = { "SALT LAKE COMMUNITY COLLEGE", " salt Lake community COLLEGE " })
	@DisplayName("should ignore string casing and trim whitespace on otherwise valid elements passed to the constructor")
	void ignores_string_casing_and_trims_whitespace_on_valid_elements(String name) {
		OrdinarySet expected = new OrdinarySet("Salt Lake Community College");
		OrdinarySet actual = new OrdinarySet(name);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should ignore duplicate names of the same element passed to the constructor")
	void ignores_duplicate_names_of_same_element() {
		OrdinarySet expected = new OrdinarySet("Snow College");
		OrdinarySet actual = new OrdinarySet("Snow College", "Snow College");
		assertEquals(expected, actual);
	}

	// -------------------------------------------------------------------------
	// complement method
	// -------------------------------------------------------------------------

	@Test
	@DisplayName("should return the expected complement of a set with one element")
	void returns_complement_of_single_element_set() {
		OrdinarySet a = new OrdinarySet("Salt Lake Community College");
		OrdinarySet expected = new OrdinarySet("Brigham Young University", "Eagle Gate College", "Ensign College",
				"Snow College", "Southern Utah University", "University of Utah", "Utah State University",
				"Utah Tech University", "Utah Valley University", "Weber State University", "Westminster University");
		OrdinarySet actual = a.complement();
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return the universal set as the complement of an empty set")
	void returns_universal_set_as_complement_of_empty_set() {
		OrdinarySet empty = new OrdinarySet();
		OrdinarySet expected = universe();
		OrdinarySet actual = empty.complement();
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return an empty set as the complement of the universal set")
	void returns_empty_set_as_complement_of_universal_set() {
		OrdinarySet u = universe();
		OrdinarySet expected = new OrdinarySet();
		OrdinarySet actual = u.complement();
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return the original set after calling complement twice")
	void returns_original_set_on_double_complement() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Salt Lake Community College",
				"Westminster University");
		OrdinarySet expected = new OrdinarySet("Brigham Young University", "Salt Lake Community College",
				"Westminster University");
		OrdinarySet actual = a.complement().complement();
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should not modify the original set after calling complement")
	void does_not_modify_original_set_after_complement() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet original = new OrdinarySet("Brigham Young University", "Snow College");
		a.complement();
		assertEquals(original, a);
	}

	// -------------------------------------------------------------------------
	// union method
	// -------------------------------------------------------------------------

	@Test
	@DisplayName("should return all elements of both sets from the union of two sets")
	void returns_all_elements_of_both_sets_from_union_of_two_sets() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Eagle Gate College");
		OrdinarySet b = new OrdinarySet("Eagle Gate College", "Westminster University");

		OrdinarySet expected = new OrdinarySet("Brigham Young University", "Eagle Gate College",
				"Westminster University");
		OrdinarySet actual = a.union(b);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return set A for the union of two duplicates of set A")
	void returns_set_a_for_union_of_two_duplicates_of_set_a() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Eagle Gate College");
		OrdinarySet b = new OrdinarySet("Brigham Young University", "Eagle Gate College");

		OrdinarySet expected = new OrdinarySet("Brigham Young University", "Eagle Gate College");
		OrdinarySet actual = a.union(b);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return set A for the union of set A and an empty set")
	void returns_set_a_for_union_of_set_a_and_empty_set() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Eagle Gate College");
		OrdinarySet empty = new OrdinarySet();

		OrdinarySet expected = new OrdinarySet("Brigham Young University", "Eagle Gate College");
		OrdinarySet actual = a.union(empty);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return the same result for A union B, as B union A")
	void returns_same_result_for_a_union_b_as_b_union_a() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Eagle Gate College");
		OrdinarySet b = new OrdinarySet("Eagle Gate College", "Westminster University");
		assertEquals(a.union(b), b.union(a));
	}

	@Test
	@DisplayName("should return an empty set for the union of two empty sets")
	void returns_empty_set_for_union_of_two_empty_sets() {
		OrdinarySet a = new OrdinarySet();
		OrdinarySet b = new OrdinarySet();

		OrdinarySet expected = new OrdinarySet();
		OrdinarySet actual = a.union(b);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should not modify either original set after calling union")
	void does_not_modify_either_original_set_after_union() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet b = new OrdinarySet("Snow College", "Westminster University");

		OrdinarySet originalA = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet originalB = new OrdinarySet("Snow College", "Westminster University");

		a.union(b);

		assertAll(() -> assertEquals(originalA, a, "Set A was modified"),
				() -> assertEquals(originalB, b, "Set B was modified"));
	}

	// -------------------------------------------------------------------------
	// intersection method
	// -------------------------------------------------------------------------

	@Test
	@DisplayName("should return the single common element from the intersection of two sets")
	void returns_single_common_element_from_intersection_of_two_sets() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet b = new OrdinarySet("Snow College", "Westminster University");

		OrdinarySet expected = new OrdinarySet("Snow College");
		OrdinarySet actual = a.intersection(b);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return an empty set from the intersection of two disjoint sets")
	void returns_empty_set_from_intersection_of_two_disjoint_sets() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet b = new OrdinarySet("University of Utah", "Westminster University");

		OrdinarySet expected = new OrdinarySet();
		OrdinarySet actual = a.intersection(b);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return set A from the intersection of set A and the universal set")
	void returns_set_a_from_intersection_of_set_a_and_universal_set() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet u = universe();

		OrdinarySet expected = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet actual = a.intersection(u);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return an empty set from the intersection of set A and an empty set")
	void returns_empty_set_from_intersection_of_set_a_and_empty_set() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet empty = new OrdinarySet();

		OrdinarySet expected = new OrdinarySet();
		OrdinarySet actual = a.intersection(empty);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should not modify either original set after calling intersection")
	void does_not_modify_either_original_set_after_intersection() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet b = new OrdinarySet("Snow College", "Westminster University");

		OrdinarySet originalA = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet originalB = new OrdinarySet("Snow College", "Westminster University");

		a.intersection(b);

		assertAll(() -> assertEquals(originalA, a, "Set A was modified"),
				() -> assertEquals(originalB, b, "Set B was modified"));
	}

	// -------------------------------------------------------------------------
	// difference method
	// -------------------------------------------------------------------------

	@Test
	@DisplayName("should return the single element not shared in either direction of set A and set B")
	void returns_single_uncommon_element_from_set_a_and_set_b() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet b = new OrdinarySet("Snow College", "Westminster University");

		OrdinarySet expectedAMinusB = new OrdinarySet("Brigham Young University");
		OrdinarySet expectedBMinusA = new OrdinarySet("Westminster University");

		// Actual result of A - B
		OrdinarySet actualAMinusB = a.difference(b);
		assertEquals(expectedAMinusB, actualAMinusB);

		// Actual result of B - A
		OrdinarySet actualBMinusA = b.difference(a);
		assertEquals(expectedBMinusA, actualBMinusA);

		// Results should not be equal
		assertNotEquals(actualAMinusB, actualBMinusA);
	}

	@Test
	@DisplayName("should return an empty set for the difference of two duplicate sets")
	void returns_empty_set_for_difference_of_two_duplicate_sets() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet b = new OrdinarySet("Brigham Young University", "Snow College");

		OrdinarySet expected = new OrdinarySet();
		OrdinarySet actual = a.difference(b);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return set A for the difference of set A and an empty set")
	void returns_set_a_for_difference_of_set_a_and_empty_set() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet empty = new OrdinarySet();

		OrdinarySet expected = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet actual = a.difference(empty);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return an empty set for the difference of an empty set and set A")
	void returns_empty_set_for_difference_of_empty_set_and_set_a() {
		OrdinarySet empty = new OrdinarySet();
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");

		OrdinarySet expected = new OrdinarySet();
		OrdinarySet actual = empty.difference(a);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should not modify either original set after calling difference")
	void does_not_modify_either_original_set_after_difference() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet b = new OrdinarySet("Snow College", "Westminster University");

		OrdinarySet originalA = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet originalB = new OrdinarySet("Snow College", "Westminster University");

		a.difference(b);

		assertAll(() -> assertEquals(originalA, a, "Set A was modified"),
				() -> assertEquals(originalB, b, "Set B was modified"));
	}

	// -------------------------------------------------------------------------
	// symmetricDifference method
	// -------------------------------------------------------------------------

	@Test
	@DisplayName("should return the symmetric difference of two overlapping sets")
	void returns_symmetric_difference_of_two_overlapping_sets() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet b = new OrdinarySet("Snow College", "Westminster University");

		OrdinarySet expected = new OrdinarySet("Brigham Young University", "Westminster University");

		// Should be the equivalent of using difference and union together
		OrdinarySet duActual = a.difference(b).union(b.difference(a));
		assertEquals(expected, duActual);

		// Using symmetricDifference
		OrdinarySet sdActual = a.symmetricDifference(b);
		assertEquals(expected, sdActual);

		assertEquals(duActual, sdActual);
	}

	@Test
	@DisplayName("should return an empty set for the symmetric difference of two duplicate sets")
	void returns_empty_set_for_symmetric_difference_of_two_duplicate_sets() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet b = new OrdinarySet("Brigham Young University", "Snow College");

		OrdinarySet expected = new OrdinarySet();
		OrdinarySet actual = a.symmetricDifference(b);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return set A for the symmetric difference of set A and an empty set")
	void returns_set_a_for_symmetric_difference_of_set_a_and_empty_set() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet empty = new OrdinarySet();

		OrdinarySet expected = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet actual = a.symmetricDifference(empty);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should not modify either original set after calling symmetric difference")
	void does_not_modify_either_original_set_after_symmetric_difference() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet b = new OrdinarySet("Snow College", "Westminster University");

		OrdinarySet originalA = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet originalB = new OrdinarySet("Snow College", "Westminster University");

		a.symmetricDifference(b);

		assertAll(() -> assertEquals(originalA, a, "Set A was modified"),
				() -> assertEquals(originalB, b, "Set B was modified"));
	}

	// -------------------------------------------------------------------------
	// toBitString method
	// -------------------------------------------------------------------------

	@Test
	@DisplayName("should convert an empty set to all zeros")
	void converts_empty_set_to_all_zeros() {
		OrdinarySet a = new OrdinarySet();

		String expected = "000000000000";
		String actual = a.toBitString();
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should properly handle the beginning and end indices of the set")
	void handles_beginning_and_end_indices_of_sets() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Salt Lake Community College",
				"Westminster University");

		String expected = "100100000001";
		String actual = a.toBitString();
		assertEquals(expected, actual);
	}

	// -------------------------------------------------------------------------
	// toString override
	// -------------------------------------------------------------------------

	@Test
	@DisplayName("should return a populated set in the expected string output order")
	void returns_populated_set_as_expected_string_output_order() {
		OrdinarySet a = new OrdinarySet("Snow College", "Brigham Young University");

		String expected = "{ Brigham Young University, Snow College }";
		String actual = a.toString();
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return an empty set as a string of empty brackets")
	void returns_empty_set_as_string_of_empty_brackets() {
		OrdinarySet a = new OrdinarySet();

		String expected = "{  }";
		String actual = a.toString();
		assertEquals(expected, actual);
	}

	// -------------------------------------------------------------------------
	// equals and hashCode overrides
	// -------------------------------------------------------------------------

	@Test
	@DisplayName("should be equal to itself")
	void equals_is_reflexive() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		assertTrue(a.equals(a));
	}

	@Test
	@DisplayName("should be equal in both directions for sets with the same elements given in a different order")
	void equals_is_symmetric_for_same_elements_in_different_order() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet b = new OrdinarySet("Snow College", "Brigham Young University");

		assertAll(() -> assertTrue(a.equals(b), "a.equals(b)"), () -> assertTrue(b.equals(a), "b.equals(a)"));
	}

	@Test
	@DisplayName("should not be equal to a set with different elements")
	void not_equal_for_different_elements() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet b = new OrdinarySet("Brigham Young University", "Westminster University");
		assertFalse(a.equals(b));
	}

	@Test
	@DisplayName("should not be equal to null")
	void not_equal_to_null() {
		OrdinarySet a = new OrdinarySet("Snow College");
		assertFalse(a.equals(null));
	}

	@Test
	@DisplayName("should return equal hash codes for equal sets")
	void equal_sets_have_equal_hash_codes() {
		OrdinarySet a = new OrdinarySet("Brigham Young University", "Snow College");
		OrdinarySet b = new OrdinarySet("Snow College", "Brigham Young University");
		assertEquals(a.hashCode(), b.hashCode());
	}

}
