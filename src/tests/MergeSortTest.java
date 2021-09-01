package tests;

import benchmarker.sort.algorithms.MergeSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MergeSortTest {

    @Test
    void mergeSortTest() {
        MergeSort mergeSort = new MergeSort();
        assertNotNull(mergeSort);
        assertEquals("MergeSort", mergeSort.getName());
        int[] unsortedArray = {2, 4, 1, 5, 3};
        int[] sortedArray   = {1, 2, 3, 4, 5};

        int[] array = mergeSort.sort(unsortedArray);

        for (int i = 0; i < unsortedArray.length; i++) {
            assertEquals(sortedArray[i], array[i]);
        }
    }
}
