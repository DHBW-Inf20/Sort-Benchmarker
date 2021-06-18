package sort.algorithms;

import sort.Sorter;

public class BubbleSort extends Sorter {

    public int[] unsortedArray;

    public int[] sort() {
        int temp;
        for (int i = 0; i < unsortedArray.length - 1; i++) {
            if (unsortedArray[i] < unsortedArray[i + 1]) {
                continue;
            }
            temp = unsortedArray[i];
            unsortedArray[i] = unsortedArray[i + 1];
            unsortedArray[i + 1] = temp;
            sort();
        }
        return unsortedArray;
    }

    @Override
    public String getName() {
        return "BubbleSort";
    }

    @Override
    public int[] sort(int[] toSort) {
        unsortedArray = toSort;
        return new int[0];
    }
}