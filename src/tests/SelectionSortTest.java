package tests;

import benchmarker.sort.algorithms.SelectionSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SelectionSortTest {

    @Test
    void selectionSortTest() {
        SelectionSort selectionSort = new SelectionSort();
        assertNotNull(selectionSort);
        assertEquals("SelectionSort", selectionSort.getName());
        int[] unsortedArray = {2, 4, 1, 5, 3};
        int[] sortedArray   = {1, 2, 3, 4, 5};

        int[] array = selectionSort.sort(unsortedArray);

        for (int i = 0; i < unsortedArray.length; i++) {
            assertEquals(sortedArray[i], array[i]);
        }
    }
}
