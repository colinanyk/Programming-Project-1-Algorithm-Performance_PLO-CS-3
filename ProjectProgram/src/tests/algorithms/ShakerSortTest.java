package tests.algorithms;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import algorithms.ShakerSort;

class ShakerSortTest {

    @Test
    void sortsMixedValuesAndCountsAdjacentComparisons() {
        ShakerSort.SortResult result = ShakerSort.sort(
            new int[] {3, 1, 4, 2}
        );

        assertArrayEquals(
            new int[] {1, 2, 3, 4},
            result.getSortedArray()
        );
        assertEquals(6, result.getComparisons());
    }

    @Test
    void sortedArrayStopsAfterOneForwardPass() {
        ShakerSort.SortResult result = ShakerSort.sort(
            new int[] {1, 2, 3, 4, 5}
        );

        assertArrayEquals(
            new int[] {1, 2, 3, 4, 5},
            result.getSortedArray()
        );
        assertEquals(4, result.getComparisons());
    }

    @Test
    void reverseSortedArrayUsesTenComparisons() {
        ShakerSort.SortResult result = ShakerSort.sort(
            new int[] {5, 4, 3, 2, 1}
        );

        assertArrayEquals(
            new int[] {1, 2, 3, 4, 5},
            result.getSortedArray()
        );
        assertEquals(10, result.getComparisons());
    }

    @Test
    void backwardPassMovesSmallValueToFront() {
        ShakerSort.SortResult result = ShakerSort.sort(
            new int[] {2, 3, 4, 5, 1}
        );

        assertArrayEquals(
            new int[] {1, 2, 3, 4, 5},
            result.getSortedArray()
        );
        assertEquals(9, result.getComparisons());
    }

    @Test
    void sortsNegativeAndDuplicateValues() {
        ShakerSort.SortResult result = ShakerSort.sort(
            new int[] {3, -1, 3, -5, 0, -1}
        );

        assertArrayEquals(
            new int[] {-5, -1, -1, 0, 3, 3},
            result.getSortedArray()
        );
    }

    @Test
    void emptyAndSingleValueArraysUseZeroComparisons() {
        ShakerSort.SortResult emptyResult =
            ShakerSort.sort(new int[] {});
        ShakerSort.SortResult singleResult =
            ShakerSort.sort(new int[] {9});

        assertArrayEquals(new int[] {}, emptyResult.getSortedArray());
        assertEquals(0, emptyResult.getComparisons());
        assertArrayEquals(new int[] {9}, singleResult.getSortedArray());
        assertEquals(0, singleResult.getComparisons());
    }

    @Test
    void twoValuesUseOneComparison() {
        ShakerSort.SortResult sortedResult =
            ShakerSort.sort(new int[] {1, 2});
        ShakerSort.SortResult reversedResult =
            ShakerSort.sort(new int[] {2, 1});

        assertArrayEquals(new int[] {1, 2}, sortedResult.getSortedArray());
        assertEquals(1, sortedResult.getComparisons());
        assertArrayEquals(new int[] {1, 2}, reversedResult.getSortedArray());
        assertEquals(1, reversedResult.getComparisons());
    }

    @Test
    void doesNotChangeOriginalArray() {
        int[] input = {4, 1, 3, 2};

        ShakerSort.sort(input);

        assertArrayEquals(new int[] {4, 1, 3, 2}, input);
    }

    @Test
    void returnedArrayCannotChangeStoredResult() {
        ShakerSort.SortResult result = ShakerSort.sort(
            new int[] {3, 2, 1}
        );
        int[] firstCopy = result.getSortedArray();

        firstCopy[0] = 99;

        assertArrayEquals(
            new int[] {1, 2, 3},
            result.getSortedArray()
        );
    }

    @Test
    void rejectsNullInput() {
        assertThrows(
            IllegalArgumentException.class,
            () -> ShakerSort.sort(null)
        );
    }
}