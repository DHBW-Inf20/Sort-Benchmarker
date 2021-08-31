package tests;

import benchmarker.sort.algorithms.QuickSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuickSortTest {

    @Test
    void QuickSortTest() {
        QuickSort quickSort = new QuickSort();
        assertNotNull(quickSort);
        assertEquals("QuickSort", quickSort.getName());
        int[] unsortedArray = {2, 4, 1, 5, 3};
        int[] sortedArray   = {1, 2, 3, 4, 5};
        assertEquals(String.valueOf(sortedArray[0]),
                String.valueOf(quickSort.sort(unsortedArray)[0]));
        assertEquals(String.valueOf(sortedArray[1]),
                String.valueOf(quickSort.sort(unsortedArray)[1]));
        assertEquals(String.valueOf(sortedArray[2]),
                String.valueOf(quickSort.sort(unsortedArray)[2]));
        assertEquals(String.valueOf(sortedArray[3]),
                String.valueOf(quickSort.sort(unsortedArray)[3]));
        assertEquals(String.valueOf(sortedArray[4]),
                String.valueOf(quickSort.sort(unsortedArray)[4]));
    }
}
