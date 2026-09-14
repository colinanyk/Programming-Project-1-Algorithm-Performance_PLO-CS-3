# Sorting Algorithm Project Pseudocode
## `Client.java`

```text
CLASS Client

    MAIN METHOD
        SET constant N_VALUES to [4, 6, 8]
        CREATE the four sorting algorithm objects

        FOR EACH n in N_VALUES
            GENERATE every permutation of 0 through n - 1
            FOR EACH permutation
                FOR EACH sorting algorithm
                    COPY the permutation so every algorithm gets the same input
                    SORT the copy
                    RECORD algorithm name, original array, and comparison count
                END FOR
            END FOR
            FIND the best 10, worst 10, and average for each algorithm
            PRINT and save a clearly labeled summary
        END FOR
        IF an error occurs
            PRINT a helpful error message
        END IF
    END MAIN METHOD

END CLASS
```

## `PermutationGenerator.java`

```text
CLASS PermutationGenerator

    METHOD generatePermutations(n)
        CREATE starting array [0, 1, 2, ..., n - 1]
        REPEAT
            ADD a copy of the current array to the permutation list
            FIND the next array in lexicographic order
        UNTIL no next permutation exists
        RETURN the complete permutation list
    END METHOD

    METHOD nextPermutation(array)
        FIND the rightmost value that is smaller than the value after it
        IF none exists, RETURN false
        FIND the rightmost larger value and SWAP the two values
        REVERSE the values after the swapped position
        RETURN true
    END METHOD

END CLASS
```

## `SortingAlgorithm.java`

```text
INTERFACE SortingAlgorithm

    METHOD sort(inputArray)
        EXPECTED INPUT: unsorted integer array
        EXPECTED OUTPUT: SortResult containing the sorted array and comparison count
    END METHOD

    METHOD getName()
        RETURN the algorithm name
    END METHOD

END INTERFACE
```

## `SortResult.java`

```text
CLASS SortResult
    VARIABLE sortedArray
    VARIABLE comparisonCount
    CONSTRUCTOR receives sortedArray and comparisonCount
    METHODS return the stored array and count

END CLASS
```

## `MergeSort.java`

```text
CLASS MergeSort IMPLEMENTS SortingAlgorithm

    METHOD sort(inputArray)
        COPY the input array
        RECURSIVELY split the copy into halves
        MERGE the halves in sorted order
        INCREASE the counter for each comparison between two values
        RETURN the sorted copy and comparison count
    END METHOD

END CLASS
```

## `QuickSort.java`

```text
CLASS QuickSort IMPLEMENTS SortingAlgorithm

    METHOD sort(inputArray)
        COPY the input array
        USE the last value as the pivot
        COMPARE each remaining value with the pivot and increase the counter each time
        MOVE smaller values before the pivot and larger values after it
        REPEAT on the left and right sections
        RETURN the sorted copy and comparison count
    END METHOD

END CLASS
```

## `ShakerSort.java`

```text
CLASS ShakerSort IMPLEMENTS SortingAlgorithm

    METHOD sort(inputArray)
        COPY the input array
        REPEAT while a swap occurs
            MOVE forward, compare neighboring values, count, and swap if needed
            MOVE backward, compare neighboring values, count, and swap if needed
        END REPEAT
        RETURN the sorted copy and comparison count
    END METHOD

END CLASS
```

## `HeapSort.java`

```text
CLASS HeapSort IMPLEMENTS SortingAlgorithm

    METHOD sort(inputArray)
        COPY the input array
        BUILD a max heap
        REPEATEDLY
            MOVE the largest value to the end
            REBUILD the remaining heap
            INCREASE the counter for each comparison between heap values
        END REPEAT
        RETURN the sorted copy and comparison count
    END METHOD

END CLASS
```

## `ResultsManager.java`

```text
CLASS ResultsManager

    METHOD recordResult(algorithmName, n, originalArray, comparisonCount)
        STORE the result
    END METHOD

    METHOD createSummary(algorithmName, n)
        SORT matching results by comparison count
        SELECT the 10 lowest results as best cases
        SELECT the 10 highest results as worst cases
        CALCULATE average comparisons across every permutation
        RETURN the labeled summary
    END METHOD

    METHOD saveSummary(summary)
        WRITE the summary to an output file
    END METHOD

END CLASS
```
