package generator;


/**
 * Team Members: Colin Nykanen, Yakez Owens, Rowan Bailey, Cassandra Keiley 
 * Course: CS 2430, Instructor Jon McGowan, Fall Semester 2026
 * Programming Project 1
 * 
 * Primary Author: Cassandra Keiley
 * 
 * Generates all permutations of integers 0 through n-1 in lexicographic order
 * 
 * Adapted from: 
 * "PermutationsLex.java" created by Frank Ruskey and Joe Sawada;
 * Modified for java by Robert Sedgewick and Kevin Wayne.
 * 
 * https://introcs.cs.princeton.edu/java/23recursion/PermutationsLex.java.html
 * 
 * Modified for this project to store the permutations rather than print them
 * 
 */
public class PermutationGenerator {
	
	public static int[][] generatePermutations(int n) {

		int totalPermutations = factorial(n);
		
		int[][] permutations = new int[totalPermutations][n];
		
		int[] current = new int[n];
		
		for (int i = 0; i < n; i++) {
			current[i] = i;
		}
		
		int index = 0;
		permutations[index] = current.clone();
		index++;
		
		while (hasNext(current)) {
			permutations[index] = current.clone();
			index++;
		}
		
		return permutations;
	}

	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static boolean hasNext(int[] a) {
		int n = a.length;

		// find rightmost element a[k] that is smaller than element to its right
		int k;
		for (k = n - 2; k >= 0; k--)
			if (a[k] < a[k + 1])
				break;
		if (k == -1)
			return false;

		// find rightmost element a[j] that is larger than a[k]
		int j = n - 1;
		while (a[k] > a[j])
			j--;
		swap(a, j, k);

		for (int r = n - 1, s = k + 1; r > s; r--, s++)
			swap(a, r, s);

		return true;
	}
	
	public static int factorial(int n) {
		int result = 1;
		
		for (int i = 2; i <=n; i++) result *=i;
		
		return result;
	}

}
