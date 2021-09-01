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
        for (int i = 0; i < unsortedArray.length; i++) { //Einmal durch das Array
            for (int j = unsortedArray.length-1; j > 0; j--) { //Rückwärts durch das Array
                if (unsortedArray[j-1] > unsortedArray[j]) { //Wenn das  linkere Element kleiner ist als das rechtere wird es getauscht
                    temp = unsortedArray[j];
                    unsortedArray[j] = unsortedArray[j - 1];
                    unsortedArray[j - 1] = temp;
                }
            }
        }
        return unsortedArray;
    }
}