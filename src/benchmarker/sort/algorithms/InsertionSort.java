package benchmarker.sort.algorithms;

import benchmarker.sort.Sorter;

public class InsertionSort extends Sorter {

    public int[] unsortedArray;

    @Override
    public String getName() {
        return "InsertionSort";
    }

    @Override
    public int[] sort(int[] toSort) {
        unsortedArray = toSort;
        int temp;
        for (int i = 0; i < unsortedArray.length; i++) {
            for (int j = unsortedArray.length-1; j > 0; j--) {
                if (unsortedArray[j-1] > unsortedArray[j]) {
                    temp = unsortedArray[j];
                    unsortedArray[j] = unsortedArray[j - 1];
                    unsortedArray[j - 1] = temp;
                }
            }
        }
        return unsortedArray;
    }
}