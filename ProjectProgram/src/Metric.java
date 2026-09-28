import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** 
 * Team Members: Colin Nykanen, Yakez Owens, Rowan Bailey, Cassandra Keiley 
 * Course: CS 2430, Instructor Jon McGowan, Fall Semester 2026
 * Holds the inputs and comparison counts for one algorithm and size. */
public final class Metric {

    private final String name;
    private final int size;
    private final List<Case> cases = new ArrayList<>();
    private long totalComparisons;

    /** Creates a set of measurements. */
    public Metric(String name, int size) {
        this.name = name;
        this.size = size;
    }

    /** Saves one run and checks that the output is correctly sorted. */
    public void record(int[] input, int[] sorted, long comparisons) {
        if (input == null || sorted == null || input.length != size
                || comparisons < 0) {
            throw new IllegalArgumentException("Invalid sorting result.");
        }
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        if (!Arrays.equals(expected, sorted)) {
            throw new IllegalStateException(name + " sorted an array incorrectly: "
                    + Arrays.toString(input));
        }

        cases.add(new Case(input, comparisons));
        totalComparisons += comparisons;
    }

    public String getName() { return name; }

    public int getSize() { return size; }

    public List<Case> getCases() { return Collections.unmodifiableList(cases); }

    /** Returns the average comparison count over all saved runs. */
    public double getAverage() {
        return cases.isEmpty() ? 0.0 : (double) totalComparisons / cases.size();
    }

    /** One original array and the comparisons needed to sort it. */
    public static final class Case {
        private final int[] input;
        private final long comparisons;

        private Case(int[] input, long comparisons) {
            this.input = Arrays.copyOf(input, input.length);
            this.comparisons = comparisons;
        }

        public int[] getInput() { return Arrays.copyOf(input, input.length); }

        public long getComparisons() { return comparisons; }
    }
}
