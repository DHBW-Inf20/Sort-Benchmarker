package tests;

import benchmarker.sort.algorithms.InsertionSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InsertionSortTest {

    @Test
    void insertionSortTest() {
        InsertionSort insertionSort = new InsertionSort();
        assertNotNull(insertionSort);
        assertEquals("InsertionSort", insertionSort.getName());
        int[] unsortedArray = {2, 4, 1, 5, 3};
        int[] sortedArray   = {1, 2, 3, 4, 5};

        int[] array = insertionSort.sort(unsortedArray);

        for (int i = 0; i < unsortedArray.length; i++) {
            assertEquals(sortedArray[i], array[i]);
        }
    }
}
