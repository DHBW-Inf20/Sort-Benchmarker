package tests;

import benchmarker.sort.algorithms.InsertionSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InsertionSortTest {

    @Test
    void InsertionSortTest() {
        InsertionSort insertionSort = new InsertionSort();
        assertNotNull(insertionSort);
        assertEquals("InsertionSort", insertionSort.getName());
        int[] unsortedArray = {2, 4, 1, 5, 3};
        int[] sortedArray   = {1, 2, 3, 4, 5};
        assertEquals(String.valueOf(sortedArray[0]),
                String.valueOf(insertionSort.sort(unsortedArray)[0]));
        assertEquals(String.valueOf(sortedArray[1]),
                String.valueOf(insertionSort.sort(unsortedArray)[1]));
        assertEquals(String.valueOf(sortedArray[2]),
                String.valueOf(insertionSort.sort(unsortedArray)[2]));
        assertEquals(String.valueOf(sortedArray[3]),
                String.valueOf(insertionSort.sort(unsortedArray)[3]));
        assertEquals(String.valueOf(sortedArray[4]),
                String.valueOf(insertionSort.sort(unsortedArray)[4]));
    }
}
