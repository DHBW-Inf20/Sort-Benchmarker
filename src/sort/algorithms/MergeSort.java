package sort.algorithms;

public class MergeSort {

    public static int[] unsortedArray = { 16, 23, 14, 7, 21, 20, 6, 1, 17, 13, 12, 9, 3, 19 };

    public int[] sort(int leftPart, int rightPart) {

        if (leftPart < rightPart) {
            int dividedList = (leftPart + rightPart) / 2;

            sort(leftPart, dividedList);
            sort(dividedList + 1, rightPart);
            merge(leftPart, dividedList, rightPart);
        }
        return unsortedArray;
    }

    private void merge(int leftPart, int dividedList, int rightPart) {
        int[] sortedArray = new int[unsortedArray.length];
        int sortedLeftPart, sortedRightPart;
        for (sortedLeftPart = leftPart; sortedLeftPart <= dividedList; sortedLeftPart++) {
            sortedArray[sortedLeftPart] = unsortedArray[sortedLeftPart];
        }
        for (sortedRightPart = dividedList + 1; sortedRightPart <= rightPart; sortedRightPart++) {
            sortedArray[rightPart + dividedList + 1 - sortedRightPart] = unsortedArray[sortedRightPart];
        }
        sortedLeftPart = leftPart;
        sortedRightPart = rightPart;
        for (int k = leftPart; k <= rightPart; k++) {
            if (sortedArray[sortedLeftPart] <= sortedArray[sortedRightPart]) {
                unsortedArray[k] = sortedArray[sortedLeftPart];
                sortedLeftPart++;
            } else {
                unsortedArray[k] = sortedArray[sortedRightPart];
                sortedRightPart--;
            }
        }
    }
}