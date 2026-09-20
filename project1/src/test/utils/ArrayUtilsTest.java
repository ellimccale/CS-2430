package test.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.utils.ArrayUtils;

class ArrayUtilsTest {

	private Integer[] array;
	private List<Integer> list;

	@Nested
	@DisplayName("generic array swap")
	class GenericArraySwap {

		@BeforeEach
		public void setUp() {
			array = new Integer[] {1, 2, 3, 4, 5};
		}

		@Test
	    @DisplayName("should swap two elements in an array")
	    void swap_array_swapsTwoElements() {
	        ArrayUtils.swap(array, 0, 4);
	        assertArrayEquals(new Integer[]{5, 2, 3, 4, 1}, array);
	    }
	
	    @Test
	    @DisplayName("should be a no-op when x equals y")
	    void swap_array_sameIndex_noOp() {
	        ArrayUtils.swap(array, 2, 2);
	        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
	    }
	
	    @Test
	    @DisplayName("should throw on an out-of-bounds when index does not exist")
	    void swap_array_throws_outOfBounds() {
	        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
	            ArrayUtils.swap(array, 0, 10);
	        });
	    }

	}

    @Nested
    @DisplayName("list swap")
    class ListSwap {

    	@BeforeEach
    	public void setUp() {
    		list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    	}

	    @Test
	    @DisplayName("should swap two elements in a list")
	    void swap_list_swapsTwoElements() {
	        ArrayUtils.swap(list, 1, 3);
	        assertEquals(List.of(1, 4, 3, 2, 5), list);
	    }
	
	    @Test
	    @DisplayName("should be a no-op when x equals y")
	    void swap_list_sameIndex_noOp() {
	        ArrayUtils.swap(list, 2, 2);
	        assertEquals(List.of(1, 2, 3, 4, 5), list);
	    }
	
	    @Test
	    @DisplayName("should throw on an out-of-bounds when index does not exist")
	    void swap_list_throws_outOfBounds() {
	        assertThrows(IndexOutOfBoundsException.class, () -> {
	            ArrayUtils.swap(list, 0, 10);
	        });
	    }

    }

}