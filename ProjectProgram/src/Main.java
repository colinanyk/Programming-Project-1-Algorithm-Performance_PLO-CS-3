import java.util.Arrays;

import algorithms.Heapsort;
import algorithms.Mergesort;
import algorithms.Quicksort;
import algorithms.ShakerSort;
import generator.PermutationGenerator;

/*
 * Team Members: Colin Nykanen, Yakez Owens, Rowan Bailey, Cassandra Keiley 
 * Course: CS 2430, Instructor Jon McGowan, Fall Semester 2026
 * * Runs the sorting experiment for every required array size. */
public class Main {

    /** Starts the experiment and prints its results. */
    public static void main(String[] args) {
        int[] sizes = {4, 6, 8};

        for (int n : sizes) {
            Metric merge = new Metric("Mergesort", n);
            Metric quick = new Metric("Quicksort", n);
            Metric shaker = new Metric("Shaker sort", n);
            Metric heap = new Metric("Heapsort", n);

            for (int[] input : PermutationGenerator.generatePermutations(n)) {
                Mergesort.SortResult mergeResult = Mergesort.sort(input);
                merge.record(input, mergeResult.getSortedArray(),
                        mergeResult.getComparisons());

                int[] quickArray = Arrays.copyOf(input, n);
                Quicksort quicksort = new Quicksort();
                quicksort.sort(quickArray);
                quick.record(input, quickArray, quicksort.getComparisons());

                ShakerSort.SortResult shakerResult = ShakerSort.sort(input);
                shaker.record(input, shakerResult.getSortedArray(),
                        shakerResult.getComparisons());

                int[] heapArray = Arrays.copyOf(input, n);
                int heapComparisons = Heapsort.sort(heapArray);
                heap.record(input, heapArray, heapComparisons);
            }

            Report.print(merge);
            Report.print(quick);
            Report.print(shaker);
            Report.print(heap);
        }
    }
}
