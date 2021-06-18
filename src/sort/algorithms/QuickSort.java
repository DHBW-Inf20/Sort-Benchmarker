package sort.algorithms;

import sort.Sorter;

public class QuickSort extends Sorter {

    public static int[] unsortedArray;

    public int[] sort(int unsortedLeftPart, int unsortedRightPart) {
        int dividedArray;
        if (unsortedLeftPart < unsortedRightPart) {
            dividedArray = partition(unsortedLeftPart, unsortedRightPart);
            sort(unsortedLeftPart, dividedArray);
            sort(dividedArray + 1, unsortedRightPart);
        }
        return unsortedArray;
    }

    int partition(int unsortedLeftPart, int unsortedRightPart) {

        int sortedLeftPart, sortedRightPart, pivot = unsortedArray[(unsortedLeftPart + unsortedRightPart) / 2];
        sortedLeftPart = unsortedLeftPart - 1;
        sortedRightPart = unsortedRightPart + 1;
        while (true) {
            do {
                sortedLeftPart++;
            } while (unsortedArray[sortedLeftPart] < pivot);

            do {
                sortedRightPart--;
            } while (unsortedArray[sortedRightPart] > pivot);

            if (sortedLeftPart < sortedRightPart) {
                int k = unsortedArray[sortedLeftPart];
                unsortedArray[sortedLeftPart] = unsortedArray[sortedRightPart];
                unsortedArray[sortedRightPart] = k;
            } else {
                return sortedRightPart;
            }
        }
    }

    @Override
    public String getName() {
        return "QuickSort";
    }

    @Override
    public int[] sort(int[] toSort) {
        unsortedArray = toSort;
        return new int[0];
    }
}