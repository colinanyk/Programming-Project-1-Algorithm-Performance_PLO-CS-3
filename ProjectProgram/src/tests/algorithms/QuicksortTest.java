package tests.algorithms;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import algorithms.Quicksort;

/**
 * Tests the QuickSort algorithm 
 * 
 * @author rBailey
 */
class QuicksortTest {

    @Test
    void testUnsortedArray() {
        Quicksort quicksort = new Quicksort();

        int[] a = {5, 2, 8, 1, 3};
        int[] expected = {1, 2, 3, 5, 8};

        quicksort.sort(a);

        assertArrayEquals(expected, a);
    }

    @Test
    void testAlreadySortedArray() {
        Quicksort quicksort = new Quicksort();

        int[] a = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        quicksort.sort(a);

        assertArrayEquals(expected, a);
    }

    @Test
    void testReverseSortedArray() {
        Quicksort quicksort = new Quicksort();

        int[] a = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};

        quicksort.sort(a);

        assertArrayEquals(expected, a);
    }

    @Test
    void testSingleElement() {
        Quicksort quicksort = new Quicksort();

        int[] a = {10};
        int[] expected = {10};

        quicksort.sort(a);

        assertArrayEquals(expected, a);
        assertEquals(0, quicksort.getComparisons());
    }

    @Test
    void testEmptyArray() {
        Quicksort quicksort = new Quicksort();

        int[] a = {};
        int[] expected = {};

        quicksort.sort(a);

        assertArrayEquals(expected, a);
        assertEquals(0, quicksort.getComparisons());
    }

    @Test
    void testDuplicates() {
        Quicksort quicksort = new Quicksort();

        int[] a = {4, 2, 4, 1, 2};
        int[] expected = {1, 2, 2, 4, 4};

        quicksort.sort(a);

        assertArrayEquals(expected, a);
    }

    @Test
    void testNegativeNumbers() {
        Quicksort quicksort = new Quicksort();

        int[] a = {-3, 5, -1, 2, -7};
        int[] expected = {-7, -3, -1, 2, 5};

        quicksort.sort(a);

        assertArrayEquals(expected, a);
    }

    @Test
    void testComparisonCount() {
        Quicksort quicksort = new Quicksort();

        int[] a = {1, 2, 3};

        quicksort.sort(a);

        assertEquals(3, quicksort.getComparisons());
    }

    @Test
    void testComparisonCountResets() {
        Quicksort quicksort = new Quicksort();

        int[] a = {3, 1, 2};
        quicksort.sort(a);

        int firstCount = quicksort.getComparisons();

        int[] b = {2, 1};
        quicksort.sort(b);

        int secondCount = quicksort.getComparisons();

        assertEquals(1, secondCount);
        assertNotEquals(firstCount, secondCount);
    }
}