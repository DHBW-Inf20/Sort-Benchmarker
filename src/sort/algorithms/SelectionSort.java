package sort.algorithms;

import sort.Sorter;

public class SelectionSort extends Sorter {

    public int[] unsortedArray;

    public int[] sort() {
        int q, temp;
        for (int i = unsortedArray.length - 1; i >= 1; i--) {
            q = 0;
            for (int j = 1; j <= i; j++) {
                if (unsortedArray[j] > unsortedArray[q]) {
                    q = j;
                }
            }
            temp = unsortedArray[q];
            unsortedArray[q] = unsortedArray[i];
            unsortedArray[i] = temp;
        }
        return unsortedArray;
    }

    @Override
    public String getName() {
        return "SelectionSort";
    }

    @Override
    public int[] sort(int[] toSort) {
        unsortedArray = toSort;
        return new int[0];
    }
}