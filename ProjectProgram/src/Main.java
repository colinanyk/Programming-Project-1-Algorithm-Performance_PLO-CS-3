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

        Mergesort.sort(new int[] {3, 1, 2});
        new Quicksort();
        new Heapsort();
        ShakerSort.sort(new int[] {3, 1, 2});
        
        System.out.println("test");
    }
}