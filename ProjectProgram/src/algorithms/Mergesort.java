package algorithms;

import java.util.Arrays;

/**
 * Sorts integer arrays with merge sort and counts ordering comparisons.
 *
 * <p>A comparison is counted only when two array elements are compared inside
 * the merge step. Loop conditions and index checks are not counted.</p>
 */
public final class Mergesort {

    private Mergesort() {
        // Utility class: do not create Mergesort objects.
    }

    /**
     * Returns a sorted copy of {@code input} and its comparison count.
     * The original input array is not changed.
     *
     * @param input array to sort
     * @return the sorted array and number of element-to-element comparisons
     * @throws IllegalArgumentException if {@code input} is null
     */
    public static SortResult sort(int[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }

        int[] sortedArray = Arrays.copyOf(input, input.length);
        int[] temporaryArray = new int[sortedArray.length];

        long comparisons = mergeSort(
            sortedArray,
            temporaryArray,
            0,
            sortedArray.length - 1
        );

        return new SortResult(sortedArray, comparisons);
    }

    private static long mergeSort(
        int[] array,
        int[] temporaryArray,
        int left,
        int right
    ) {
        if (left >= right) {
            return 0;
        }

        int middle = left + (right - left) / 2;
        long comparisons = 0;

        comparisons += mergeSort(array, temporaryArray, left, middle);
        comparisons += mergeSort(array, temporaryArray, middle + 1, right);
        comparisons += merge(array, temporaryArray, left, middle, right);

        return comparisons;
    }

    private static long merge(
        int[] array,
        int[] temporaryArray,
        int left,
        int middle,
        int right
    ) {
        int leftIndex = left;
        int rightIndex = middle + 1;
        int temporaryIndex = left;
        long comparisons = 0;

        while (leftIndex <= middle && rightIndex <= right) {
            // This is the only ordering comparison counted by this algorithm.
            comparisons++;

            if (array[leftIndex] <= array[rightIndex]) {
                temporaryArray[temporaryIndex] = array[leftIndex];
                leftIndex++;
            } else {
                temporaryArray[temporaryIndex] = array[rightIndex];
                rightIndex++;
            }

            temporaryIndex++;
        }

        while (leftIndex <= middle) {
            temporaryArray[temporaryIndex] = array[leftIndex];
            leftIndex++;
            temporaryIndex++;
        }

        while (rightIndex <= right) {
            temporaryArray[temporaryIndex] = array[rightIndex];
            rightIndex++;
            temporaryIndex++;
        }

        for (int index = left; index <= right; index++) {
            array[index] = temporaryArray[index];
        }

        return comparisons;
    }

    /** Stores both values that the experiment needs from one sorting run. */
    public static final class SortResult {

        private final int[] sortedArray;
        private final long comparisons;

        private SortResult(int[] sortedArray, long comparisons) {
            this.sortedArray = sortedArray;
            this.comparisons = comparisons;
        }

        /**
         * @return a copy of the sorted array
         */
        public int[] getSortedArray() {
            return Arrays.copyOf(sortedArray, sortedArray.length);
        }

        /**
         * @return number of element-to-element ordering comparisons
         */
        public long getComparisons() {
            return comparisons;
        }
    }
}
