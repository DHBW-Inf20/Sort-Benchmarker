package tests;

import benchmarker.sort.algorithms.MergeSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MergeSortTest {

    @Test
    void MergeSortTest() {
        MergeSort mergeSort = new MergeSort();
        assertNotNull(mergeSort);
        assertEquals("MergeSort", mergeSort.getName());
        int[] unsortedArray = {2, 4, 1, 5, 3};
        int[] sortedArray   = {1, 2, 3, 4, 5};
        assertEquals(String.valueOf(sortedArray[0]),
                String.valueOf(mergeSort.sort(unsortedArray)[0]));
        assertEquals(String.valueOf(sortedArray[1]),
                String.valueOf(mergeSort.sort(unsortedArray)[1]));
        assertEquals(String.valueOf(sortedArray[2]),
                String.valueOf(mergeSort.sort(unsortedArray)[2]));
        assertEquals(String.valueOf(sortedArray[3]),
                String.valueOf(mergeSort.sort(unsortedArray)[3]));
        assertEquals(String.valueOf(sortedArray[4]),
                String.valueOf(mergeSort.sort(unsortedArray)[4]));
    }
}
