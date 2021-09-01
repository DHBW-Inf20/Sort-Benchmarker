package tests;

import benchmarker.sort.algorithms.MergeSortMT;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MergeSortMTTest {

    @Test
    void mergeSortMTTest() {
        MergeSortMT mergeSortMT = new MergeSortMT();
        mergeSortMT.initSorter();
        assertNotNull(mergeSortMT);
        assertEquals("MergeSort (Multithreaded)", mergeSortMT.getName());
        int[] unsortedArray = {2, 4, 1, 5, 3};
        int[] sortedArray   = {1, 2, 3, 4, 5};

        int[] array = mergeSortMT.sort(unsortedArray);

        for (int i = 0; i < unsortedArray.length; i++) {
            assertEquals(sortedArray[i], array[i]);
        }
    }
}
