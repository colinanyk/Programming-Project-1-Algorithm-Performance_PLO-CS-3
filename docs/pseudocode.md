# Sorting Algorithm Project Pseudocode

UML Diagram can be found in: `ProjectProgram/docs/project-uml.mmd`.

## `Main.java`

```text
CLASS Main

    PUBLIC STATIC METHOD main(args : String[])
        CREATE or obtain the integer arrays used by the experiment
        CREATE Quicksort and Heapsort objects
        USE the Mergesort and ShakerSort sorting utilities
        CREATE ResultsManager, Metric, and Reporting objects

        FOR EACH input array
            SAVE the input size and a copy of the original array

            RUN Mergesort on the input
            RECORD the algorithm name, input size, original array,
                and comparison count with ResultsManager

            RUN Quicksort on a separate copy of the input
            GET the Quicksort comparison count
            RECORD the result with ResultsManager

            RUN Heapsort on a separate copy of the input
            GET the comparison count returned by Heapsort
            RECORD the result with ResultsManager

            RUN ShakerSort on the input
            RECORD the algorithm name, input size, original array,
                and comparison count with ResultsManager
        END FOR

        FOR EACH sorting algorithm
            CREATE a summary with ResultsManager
            COLLECT the tested input sizes and comparison counts

            CALL Metric.determineBigO
            CALL Metric.determineBigOmega
            CALL Metric.determineBigTheta

            CALL Reporting.printResults with the summary and metrics
            CALL Reporting.saveResults with the file name, summary, and metrics
        END FOR
    END METHOD

END CLASS
```

## `SortingAlgorithm.java`

```text
INTERFACE SortingAlgorithm

    PUBLIC METHOD sort(inputArray : int[]) RETURNS SortResult
        ACCEPT an unsorted integer array
        RETURN its sorted values and comparison count
    END METHOD

    PUBLIC METHOD getName() RETURNS String
        RETURN the algorithm name
    END METHOD

END INTERFACE
```

## `SortResult.java`

```text
CLASS SortResult

    PRIVATE VARIABLE sortedArray : int[]
    PRIVATE VARIABLE comparisonCount : long

    CONSTRUCTOR SortResult(sortedArray, comparisonCount)
        STORE a copy of sortedArray
        STORE comparisonCount
    END CONSTRUCTOR

    PUBLIC METHOD getSortedArray() RETURNS int[]
        RETURN a copy of sortedArray
    END METHOD

    PUBLIC METHOD getComparisons() RETURNS long
        RETURN comparisonCount
    END METHOD

END CLASS
```

## `Mergesort.java`

```text
FINAL CLASS Mergesort IMPLEMENTS SortingAlgorithm

    PRIVATE CONSTRUCTOR Mergesort
        PREVENT creation of utility-class objects
    END CONSTRUCTOR

    PUBLIC STATIC METHOD sort(input : int[]) RETURNS SortResult
        IF input is null
            REPORT an invalid input error
        END IF

        COPY input into sortedArray
        CREATE temporaryArray with the same length
        SET comparisons to mergeSort(sortedArray, temporaryArray, 0,
            sortedArray length - 1)
        RETURN a result containing sortedArray and comparisons
    END METHOD

    PRIVATE STATIC METHOD mergeSort(array, temporaryArray, left, right)
        RETURNS long
        IF left is greater than or equal to right
            RETURN 0
        END IF

        SET middle to the midpoint between left and right
        SET comparisons to 0
        ADD comparisons from sorting the left half
        ADD comparisons from sorting the right half
        ADD comparisons from merging both halves
        RETURN comparisons
    END METHOD

    PRIVATE STATIC METHOD merge(array, temporaryArray, left, middle, right)
        RETURNS long
        SET indexes for the left half, right half, and temporary array
        SET comparisons to 0

        WHILE both halves contain unmerged values
            INCREASE comparisons
            COPY the smaller current value into temporaryArray
            ADVANCE the index for the selected value
        END WHILE

        COPY any remaining left-half values into temporaryArray
        COPY any remaining right-half values into temporaryArray
        COPY the merged range back into array
        RETURN comparisons
    END METHOD

END CLASS
```

## `MergesortSortResult`

```text
CLASS MergesortSortResult

    PRIVATE VARIABLE sortedArray : int[]
    PRIVATE VARIABLE comparisons : long

    PRIVATE CONSTRUCTOR SortResult(sortedArray, comparisons)
        STORE sortedArray and comparisons
    END CONSTRUCTOR

    PUBLIC METHOD getSortedArray() RETURNS int[]
        RETURN a copy of sortedArray
    END METHOD

    PUBLIC METHOD getComparisons() RETURNS long
        RETURN comparisons
    END METHOD

END CLASS
```

`MergesortSortResult` represents the `SortResult` nested inside `Mergesort` in
the current implementation.

## `Quicksort.java`

```text
CLASS Quicksort IMPLEMENTS SortingAlgorithm

    PRIVATE VARIABLE comparisons : int

    PUBLIC METHOD sort(a : int[]) RETURNS void
        SET comparisons to 0
        CALL quicksort(a, 0, a length - 1)
    END METHOD

    PUBLIC METHOD getComparisons() RETURNS int
        RETURN comparisons
    END METHOD

    PRIVATE METHOD quicksort(a, low, high)
        IF low is greater than or equal to high
            RETURN
        END IF

        SET pivotIndex to partition(a, low, high)
        CALL quicksort for the values before pivotIndex
        CALL quicksort for the values after pivotIndex
    END METHOD

    PRIVATE METHOD partition(a, low, high) RETURNS int
        SET pivot to a[high]
        SET smallerValueIndex to low - 1

        FOR each index from low through high - 1
            INCREASE comparisons
            IF a[index] is less than or equal to pivot
                ADVANCE smallerValueIndex
                SWAP a[smallerValueIndex] and a[index]
            END IF
        END FOR

        MOVE the pivot after the smaller values
        RETURN the pivot index
    END METHOD

    PRIVATE METHOD swap(a, i, j)
        EXCHANGE a[i] and a[j]
    END METHOD

END CLASS
```

## `Heapsort.java`

```text
CLASS Heapsort IMPLEMENTS SortingAlgorithm

    PUBLIC CONSTRUCTOR Heapsort
        INITIALIZE a Heapsort object
    END CONSTRUCTOR

    PUBLIC STATIC METHOD sort(pq : int[]) RETURNS int
        SET comparisons to 0
        BUILD a max heap by calling sink on each non-leaf value

        SET the active heap size to the array length
        WHILE the active heap contains more than one value
            EXCHANGE the maximum value with the final active value
            REDUCE the active heap size
            CALL sink to restore the heap
        END WHILE

        RETURN comparisons
    END METHOD

    PRIVATE STATIC METHOD sink(pq, k, n)
        WHILE the value at k has a child
            SELECT the larger child using less
            IF the parent is not less than that child
                STOP
            END IF
            EXCHANGE the parent and child
            CONTINUE from the child's position
        END WHILE
    END METHOD

    PRIVATE STATIC METHOD less(pq, i, j) RETURNS boolean
        INCREASE comparisons
        RETURN whether the value at i is less than the value at j
    END METHOD

    PRIVATE STATIC METHOD exch(pq, i, j)
        EXCHANGE the values at the one-based heap indexes i and j
    END METHOD

END CLASS
```

## `ShakerSort.java`

```text
FINAL CLASS ShakerSort IMPLEMENTS SortingAlgorithm

    PRIVATE CONSTRUCTOR ShakerSort
        PREVENT creation of utility-class objects
    END CONSTRUCTOR

    PUBLIC STATIC METHOD sort(input : int[]) RETURNS ShakerSortSortResult
        IF input is null
            REPORT an invalid input error
        END IF

        COPY input into sortedArray
        SET comparisons to shakerSort(sortedArray)
        RETURN a result containing sortedArray and comparisons
    END METHOD

    PRIVATE STATIC METHOD shakerSort(array : int[]) RETURNS long
        SET left boundary to 0
        SET right boundary to array length - 1
        SET comparisons to 0
        SET swapped to true

        WHILE swapped is true AND left boundary is less than right boundary
            SET swapped to false

            FOR index from left boundary through right boundary - 1
                INCREASE comparisons
                IF array[index] is greater than array[index + 1]
                    CALL swap for the neighboring values
                    SET swapped to true
                END IF
            END FOR

            DECREASE the right boundary
            IF no values were swapped
                STOP
            END IF

            SET swapped to false
            FOR index from right boundary down through left boundary + 1
                INCREASE comparisons
                IF array[index - 1] is greater than array[index]
                    CALL swap for the neighboring values
                    SET swapped to true
                END IF
            END FOR

            INCREASE the left boundary
        END WHILE

        RETURN comparisons
    END METHOD

    PRIVATE STATIC METHOD swap(array, first, second)
        EXCHANGE array[first] and array[second]
    END METHOD

END CLASS
```

## `ShakerSortSortResult`

```text
CLASS ShakerSortSortResult

    PRIVATE VARIABLE sortedArray : int[]
    PRIVATE VARIABLE comparisons : long

    PRIVATE CONSTRUCTOR SortResult(sortedArray, comparisons)
        STORE sortedArray and comparisons
    END CONSTRUCTOR

    PUBLIC METHOD getSortedArray() RETURNS int[]
        RETURN a copy of sortedArray
    END METHOD

    PUBLIC METHOD getComparisons() RETURNS long
        RETURN comparisons
    END METHOD

END CLASS
```

`ShakerSortSortResult` represents the result type owned and returned by the
assumed `ShakerSort` implementation.

## `ResultsManager.java`

```text
CLASS ResultsManager

    PUBLIC METHOD recordResult(algorithmName, n, originalArray, comparisonCount)
        STORE the algorithm name
        STORE n and a copy of originalArray
        STORE comparisonCount
    END METHOD

    PUBLIC METHOD createSummary(algorithmName, n) RETURNS String
        FIND stored results matching algorithmName and n
        ORDER the matches by comparison count
        SELECT up to 10 lowest comparison counts as best cases
        SELECT up to 10 highest comparison counts as worst cases
        CALCULATE the average comparison count
        RETURN a labeled summary
    END METHOD

END CLASS
```

## `Metric.java`

```text
CLASS Metric

    PUBLIC METHOD determineBigO(inputSizes : int[], comparisonCounts : long[])
        RETURNS String
        COMPARE the observed growth with common complexity growth functions
        DETERMINE an asymptotic upper bound
        RETURN the Big-O label
    END METHOD

    PUBLIC METHOD determineBigOmega(inputSizes : int[], comparisonCounts : long[])
        RETURNS String
        COMPARE the observed growth with common complexity growth functions
        DETERMINE an asymptotic lower bound
        RETURN the Big-Omega label
    END METHOD

    PUBLIC METHOD determineBigTheta(inputSizes : int[], comparisonCounts : long[])
        RETURNS String
        COMPARE the Big-O upper bound and Big-Omega lower bound
        IF the bounds have the same growth rate
            RETURN the matching Big-Theta label
        END IF
        RETURN a label indicating that no tight bound was identified
    END METHOD

END CLASS
```

## `Reporting.java`

```text
CLASS Reporting

    PUBLIC METHOD printResults(summary, bigO, bigOmega, bigTheta)
        PRINT the summary with a clear heading
        PRINT the Big-O result
        PRINT the Big-Omega result
        PRINT the Big-Theta result
    END METHOD

    PUBLIC METHOD saveResults(fileName, summary, bigO, bigOmega, bigTheta)
        OPEN fileName for output
        WRITE the summary and all three complexity results
        CLOSE the output file
        IF writing fails
            REPORT a helpful output error
        END IF
    END METHOD

END CLASS
```
