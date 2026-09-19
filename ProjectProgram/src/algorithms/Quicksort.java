package algorithms;

/**
 * Implements the Quicksort sorting algorithm.
 * Counts element-to-element comparisons performed
 * during the sorting process.
 *
 * @author rBailey
 */
public class Quicksort {

    private int comparisons;

    /**
     * Sorts the given array using Quicksort.
     *
     * @param a the array to sort
     */
    public void sort(int[] a) {
        comparisons = 0;
        quicksort(a, 0, a.length - 1);
    }

    /**
     * Returns the number of element-to-element comparisons
     * performed during the most recent sort.
     *
     * @return the comparison count
     */
    public int getComparisons() {
        return comparisons;
    }

    /**
     * Recursively sorts the given portion of the array.
     *
     * @param a the array being sorted
     * @param low the starting index
     * @param high the ending index
     */
    private void quicksort(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivotIndex = partition(a, low, high);

        quicksort(a, low, pivotIndex - 1);
        quicksort(a, pivotIndex + 1, high);
    }

    /**
     * Partitions the array around a pivot.
     *
     * @param a the array being partitioned
     * @param low the starting index
     * @param high the ending index
     * @return the final position of the pivot
     */
    private int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparisons++;

            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }

        swap(a, i + 1, high);

        return i + 1;
    }

    /**
     * Swaps two elements in an array.
     *
     * @param a the array
     * @param i the first index
     * @param j the second index
     */
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}


