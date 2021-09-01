package tests;

import benchmarker.sort.algorithms.QuickSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuickSortTest {

    @Test
    void quickSortTest() {
        QuickSort quickSort = new QuickSort();
        assertNotNull(quickSort);
        assertEquals("QuickSort", quickSort.getName());
        int[] unsortedArray = {2, 4, 1, 5, 3};
        int[] sortedArray   = {1, 2, 3, 4, 5};

        int[] array = quickSort.sort(unsortedArray);

        for (int i = 0; i < unsortedArray.length; i++) {
            assertEquals(sortedArray[i], array[i]);
        }
    }
}
