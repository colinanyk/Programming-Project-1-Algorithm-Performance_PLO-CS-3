package tests.algorithms;


import org.junit.jupiter.api.Test;

import algorithms.Heapsort;

import static org.junit.jupiter.api.Assertions.*;

class HeapsortTest {

	@Test
	public void testEmptyArray() {
		int[] array = {};
		int comparisons = Heapsort.sort(array);
		
		assertArrayEquals(new int[]{}, array);
		assertEquals(0, comparisons);
	}
	
	@Test
	public void testSingleElement() {
		int[] array = {5};
		int comparisons = Heapsort.sort(array);
		
		assertArrayEquals(new int[]{5}, array);
		assertEquals(0, comparisons);
	}
	
	@Test
	public void testPresorted() {
		int[] array = {0, 1, 2, 3};
		int comparisons = Heapsort.sort(array);
		
		assertArrayEquals(new int[]{0, 1, 2, 3}, array);
		assertTrue(comparisons > 0);
	}
	
	@Test
	public void testReverseSorted() {
		int[] array = {3, 2, 1, 0};
		int comparisons = Heapsort.sort(array);
		
		assertArrayEquals(new int[]{0, 1, 2, 3}, array);
		assertTrue(comparisons > 0);
	}
	
	@Test
	public void testUnsorted() {
		int[] array = {2, 3, 0, 1};
		int comparisons = Heapsort.sort(array);
		
		assertArrayEquals(new int[]{0, 1, 2, 3}, array);
		assertTrue(comparisons > 0);
	}
	
	@Test
	public void testComparisonCountReset() {
		int[] first = {3, 2, 1, 0};
		int[] second = {3, 2, 1, 0};
		
		int firstComparisons = Heapsort.sort(first);
		int secondComparisons = Heapsort.sort(second);
		
		assertEquals(firstComparisons, secondComparisons);
	}

}