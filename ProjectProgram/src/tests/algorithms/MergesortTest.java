package tests.algorithms;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import algorithms.Mergesort;

/** Simple correctness and comparison-count tests for Mergesort. */
class MergesortTest {

    @Test
    void sortsFourValuesAndCountsOnlyElementComparisons() {
        int[] input = {3, 1, 4, 2};

        Mergesort.SortResult result = Mergesort.sort(input);

        assertArrayEquals(
            new int[] {1, 2, 3, 4},
            result.getSortedArray()
        );

        assertEquals(5, result.getComparisons());
    }

    @Test
    void sortsSixValues() {
        int[] input = {5, 1, 4, 2, 8, 0};

        Mergesort.SortResult result = Mergesort.sort(input);

        assertArrayEquals(
            new int[] {0, 1, 2, 4, 5, 8},
            result.getSortedArray()
        );

        assertEquals(10, result.getComparisons());
    }

    @Test
    void sortsEightValues() {
        int[] input = {7, 3, 6, 1, 5, 0, 4, 2};

        Mergesort.SortResult result = Mergesort.sort(input);

        assertArrayEquals(
            new int[] {0, 1, 2, 3, 4, 5, 6, 7},
            result.getSortedArray()
        );

        assertEquals(16, result.getComparisons());
    }

    @Test
    void sortsNegativeNumbers() {
        int[] input = {-3, 5, -1, 0, -8};

        Mergesort.SortResult result = Mergesort.sort(input);

        assertArrayEquals(
            new int[] {-8, -3, -1, 0, 5},
            result.getSortedArray()
        );
    }

    @Test
    void doesNotChangeOriginalArray() {
        int[] input = {2, 0, 1};

        Mergesort.sort(input);

        assertArrayEquals(new int[] {2, 0, 1}, input);
    }

    @Test
    void emptyAndSingleValueArraysUseZeroComparisons() {
        Mergesort.SortResult emptyResult =
            Mergesort.sort(new int[] {});

        Mergesort.SortResult singleResult =
            Mergesort.sort(new int[] {9});

        assertArrayEquals(
            new int[] {},
            emptyResult.getSortedArray()
        );

        assertEquals(0, emptyResult.getComparisons());

        assertArrayEquals(
            new int[] {9},
            singleResult.getSortedArray()
        );

        assertEquals(0, singleResult.getComparisons());
    }

    @Test
    void rejectsNullInput() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Mergesort.sort(null)
        );
    }
}