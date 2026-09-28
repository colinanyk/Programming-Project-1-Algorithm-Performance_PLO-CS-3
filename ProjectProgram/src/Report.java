import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/** 
 * Team Members: Colin Nykanen, Yakez Owens, Rowan Bailey, Cassandra Keiley 
 * Course: CS 2430, Instructor Jon McGowan, Fall Semester 2026
 * Prints the best, worst, and average for one experiment. */
public final class Report {

    private Report() { }

    /** Prints the summary and ten cases at each end. */
    public static void print(Metric metric) {
        List<Metric.Case> cases = new ArrayList<>(metric.getCases());
        cases.sort((a, b) -> {
            int countOrder = Long.compare(a.getComparisons(), b.getComparisons());
            if (countOrder != 0) {
                return countOrder;
            }
            int[] first = a.getInput();
            int[] second = b.getInput();
            for (int i = 0; i < first.length; i++) {
                int order = Integer.compare(first[i], second[i]);
                if (order != 0) {
                    return order;
                }
            }
            return 0;
        });

        int count = cases.size();
        System.out.printf(Locale.US,
                "%n%s | n=%d | permutations=%d | best=%d | worst=%d | average=%.2f%n",
                metric.getName(), metric.getSize(), count,
                cases.get(0).getComparisons(), cases.get(count - 1).getComparisons(),
                metric.getAverage());

        System.out.println("Best 10 (comparisons : original input)");
        for (int i = 0; i < Math.min(10, count); i++) {
            printCase(cases.get(i));
        }

        System.out.println("Worst 10 (comparisons : original input)");
        for (int i = count - 1; i >= Math.max(0, count - 10); i--) {
            printCase(cases.get(i));
        }
    }

    private static void printCase(Metric.Case oneCase) {
        System.out.printf("  %d : %s%n", oneCase.getComparisons(),
                Arrays.toString(oneCase.getInput()));
    }
}
