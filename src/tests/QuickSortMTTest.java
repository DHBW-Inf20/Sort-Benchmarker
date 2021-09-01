package tests;

import benchmarker.sort.algorithms.QuickSortMT;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuickSortMTTest {

    @Test
    void quickSortMTTest() {
        QuickSortMT quickSortMT = new QuickSortMT();
        quickSortMT.initSorter();
        assertNotNull(quickSortMT);
        assertEquals("QuickSort (Multithreaded)", quickSortMT.getName());
        int[] unsortedArray = {2, 4, 1, 5, 3};
        int[] sortedArray   = {1, 2, 3, 4, 5};

        int[] array = quickSortMT.sort(unsortedArray);

        for (int i = 0; i < unsortedArray.length; i++) {
            assertEquals(sortedArray[i], array[i]);
        }
    }
}
