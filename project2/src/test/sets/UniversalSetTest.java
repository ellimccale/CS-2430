/*
 * Name:        Elli Steck
 * Course:      CS-2430, Section 501
 * Project:     Programming Project 2 – Summer 2026
 * File:        UniversalSetTest.java
 * Description: Unit tests for the Universal Set.
 */
package test.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.sets.UniversalSet;

class UniversalSetTest {

	// -------------------------------------------------------------------------
	// size method
	// -------------------------------------------------------------------------

	@Test
	@DisplayName("should correctly return the length of the universal set")
	void returns_length_of_universal_set() {
		int expected = 12;
		int actual = UniversalSet.size();
		assertEquals(expected, actual);
	}

	// -------------------------------------------------------------------------
	// indexOf method
	// -------------------------------------------------------------------------

	@Test
	@DisplayName("should map every element to the expected index")
	void every_element_expected_index() {
		for (int i = 0; i < UniversalSet.size(); i++) {
			assertEquals(i, UniversalSet.indexOf(UniversalSet.elementAt(i)));
		}
	}

	@ParameterizedTest
	@ValueSource(strings = { "SALT LAKE COMMUNITY COLLEGE", " salt Lake community COLLEGE " })
	@DisplayName("should ignore string casing and trim whitespace on the requested element")
	void ignores_string_casing_and_trims_whitespace_on_element(String element) {
		int expected = 3;
		int actual = UniversalSet.indexOf(element);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@ValueSource(strings = { "Utah", "Stanford University", " ", "" })
	@DisplayName("should return -1 if the requested element is partial or not present")
	void returns_negative_one_for_element_partial_or_not_present(String element) {
		int expected = -1;
		int actual = UniversalSet.indexOf(element);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("should return -1 if the requested element is null")
	void returns_negative_one_for_null_element() {
		int expected = -1;
		int actual = UniversalSet.indexOf(null);
		assertEquals(expected, actual);
	}

	// -------------------------------------------------------------------------
	// elementAt method
	// -------------------------------------------------------------------------

	@Test
	@DisplayName("should throw an exception if the index is less than 0")
	void throws_out_of_bounds_exception_for_index_less_than_zero() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			UniversalSet.elementAt(-1);
		});
	}

	@Test
	@DisplayName("should throw an exception if the index is off by one")
	void throws_out_of_bounds_exception_for_index_off_by_one() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			UniversalSet.elementAt(UniversalSet.size());
		});
	}

}
