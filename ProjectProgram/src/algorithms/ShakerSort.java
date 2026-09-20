package algorithms;

import java.util.Arrays;

/**
 * Sorts integer arrays with bidirectional bubble sort (shaker sort) and
 * counts ordering comparisons.
 */
public final class ShakerSort {

    private ShakerSort() {}

    /**
     * Returns a sorted copy of the provided array  and its comparison count.
     *
     * @param input array to sort
     * @return the sorted array and number of adjacent-element comparisons
     * @throws IllegalArgumentException if array is null
     */
    public static SortResult sort(int[] input) {
        if (input == null) {
            throw new IllegalArgumentException("Array is null.");
        }

        int[] sortedArray = Arrays.copyOf(input, input.length);
        long comparisons = shakerSort(sortedArray);

        return new SortResult(sortedArray, comparisons);
    }

    private static long shakerSort(int[] array) {
        int left = 0;
        int right = array.length - 1;
        long comparisons = 0;
        boolean swapped = true;

        while (swapped && left < right) {
            swapped = false;

            for (int index = left; index < right; index++) {
                comparisons++;

                if (array[index] > array[index + 1]) {
                    swap(array, index, index + 1);
                    swapped = true;
                }
            }

            right--;

            if (!swapped) {
                break;
            }

            swapped = false;

            for (int index = right; index > left; index--) {
                comparisons++;

                if (array[index - 1] > array[index]) {
                    swap(array, index - 1, index);
                    swapped = true;
                }
            }

            left++;
        }

        return comparisons;
    }

    private static void swap(int[] array, int first, int second) {
        int temporaryValue = array[first];
        array[first] = array[second];
        array[second] = temporaryValue;
    }

    public static final class SortResult {

        private final int[] sortedArray;
        private final long comparisons;

        private SortResult(int[] sortedArray, long comparisons) {
            this.sortedArray = sortedArray;
            this.comparisons = comparisons;
        }

        public int[] getSortedArray() {
            return Arrays.copyOf(sortedArray, sortedArray.length);
        }

        public long getComparisons() {
            return comparisons;
        }
    }
}
