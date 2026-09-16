import edu.princeton.cs.algs4.Quick;
import algorithms.Mergesort;
import algorithms.Quicksort;
import algorithms.Heapsort;
import algorithms.ShakerSort;

public class Main {
    public static void main(String[] args) {
        Integer[] values = {3, 1, 2};
        Quick.sort(values);
        System.out.println(values[0] + " " + values[1] + " " + values[2]);

        new Mergesort();
        new Quicksort();
        new Heapsort();
        new ShakerSort();
    }
}