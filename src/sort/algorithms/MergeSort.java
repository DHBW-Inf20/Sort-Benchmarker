package sort.algorithms;

import sort.Sorter;

public class MergeSort extends Sorter{

    public static int[] unsortedArray;

    public int[] sort(int unsortedLeftPart, int unsortedRightPart) {

        if (unsortedLeftPart < unsortedRightPart) {
            int dividedArray = (unsortedLeftPart + unsortedRightPart) / 2;

            sort(unsortedLeftPart, dividedArray);
            sort(dividedArray + 1, unsortedRightPart);
            merge(unsortedLeftPart, dividedArray, unsortedRightPart);
        }
        return unsortedArray;
    }

    private void merge(int leftPart, int dividedList, int unsortedRightPart) {
        int[] sortedArray = new int[unsortedArray.length];
        int sortedLeftPart, sortedRightPart;
        for (sortedLeftPart = leftPart; sortedLeftPart <= dividedList; sortedLeftPart++) {
            sortedArray[sortedLeftPart] = unsortedArray[sortedLeftPart];
        }
        for (sortedRightPart = dividedList + 1; sortedRightPart <= unsortedRightPart; sortedRightPart++) {
            sortedArray[unsortedRightPart + dividedList + 1 - sortedRightPart] = unsortedArray[sortedRightPart];
        }
        sortedLeftPart = leftPart;
        sortedRightPart = unsortedRightPart;
        for (int k = leftPart; k <= unsortedRightPart; k++) {
            if (sortedArray[sortedLeftPart] <= sortedArray[sortedRightPart]) {
                unsortedArray[k] = sortedArray[sortedLeftPart];
                sortedLeftPart++;
            } else {
                unsortedArray[k] = sortedArray[sortedRightPart];
                sortedRightPart--;
            }
        }
    }

    @Override
    public String getName() {
        return "MergeSort";
    }

    @Override
    public int[] sort(int[] toSort) {
        unsortedArray = toSort;
        return new int[0];
    }
}