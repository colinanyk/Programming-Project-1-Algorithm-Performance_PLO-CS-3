package algorithms;

/**
 * Team Members: Colin Nykanen, Yakez Owens, Rowan Bailey, Cassandra Keiley 
 * Course: CS 2430, Instructor Jon McGowan, Fall Semester 2026
 * Programming Project 1
 * 
 * Primary Author: Cassandra Keiley
 * 
 * Implements heapsort algorithm for integer arrays that counts comparisons
 * during sorting
 * 
 * Adapted from: "Heap.java" created by Robert Sedgewick and Kevin Wayne
 * 
 * Source: Princeton Library algs4 (included in project)
 * 
 * Modifications for project included changing input type to int[] and adding counter for
 * comparison totals (and modified method sort() to return the count)
 * 
 */
public class Heapsort {
    public Heapsort () {
        System.out.println("Heapsort");
    }
    
    private static int comparisons = 0;

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param pq the array to be sorted
     */
    public static int sort(int[] pq) {
    	comparisons = 0;
        int n = pq.length;

        // heapify phase
        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);

        // sortdown phase
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
        
        return comparisons;
    }
    
    // Helper functions to restore the heap invariant.
    private static void sink(int[] pq, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    // Helper functions for comparisons and swaps.
    // Indices are "off-by-one" to support 1-based indexing.
    private static boolean less(int[] pq, int i, int j) {
    	comparisons++;
        return pq[i-1] < pq[j-1];
    }

    private static void exch(int[] pq, int i, int j) {
        int swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

}
