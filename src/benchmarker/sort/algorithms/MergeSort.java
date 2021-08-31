package benchmarker.sort.algorithms;

import benchmarker.sort.Sorter;

public class MergeSort extends Sorter
{
    /**
     *
     * @return          Name des Sortieralgorithmus
     */
    @Override
    public String getName() {
        return "MergeSort";
    }

    private int[] leftarray;
    private int[] tempArray;    // Hilfsarray
    private int length;

    /**
     * Das unsortierte Array wird rekursiv in zwei Teile aufgeteilt, die getrennt sortiert und anschließend wieder zusammengefügt werden.
     * @param toSort    Zu sortierendes Array
     * @return
     */
    @Override
    public int[] sort(int[] toSort) {

        this.leftarray =toSort;
        length =toSort.length;
        tempArray =new int[length];
        mergesort(0, length -1);
        return toSort;
    }

    /**
     *
     * @param low
     * @param high
     */
    private void mergesort(int low, int high)
    {
        if (low<high) //if there is more than 1 element
        {
            int m=low+(high-low)/2;
            mergesort(low, m);
            mergesort(m+1, high);
            merge(low, m, high);
        }
    }

    /**
     *
     * @param momentanerPunkt
     * @param rest
     * @param until
     */
    void merge(int momentanerPunkt, int rest, int until) //bitte auf englisch machen kuss kuss
    {
        int leftSide, rightSide, leftAndRightSide;

        //copy both halfs
        for (leftSide=momentanerPunkt; leftSide<=until; leftSide++)
            tempArray[leftSide]= leftarray[leftSide];

        leftSide=momentanerPunkt; rightSide=rest+1; leftAndRightSide=momentanerPunkt;
        // jeweils das nächstgrößte Element zurückkopieren
        while (leftSide<=rest && rightSide<=until)
            if (tempArray[leftSide]<= tempArray[rightSide])
                leftarray[leftAndRightSide++]= tempArray[leftSide++];
            else
                leftarray[leftAndRightSide++]= tempArray[rightSide++];

        // Rest der vorderen Hälfte falls vorhanden zurückkopieren
        while (leftSide<=rest)
            leftarray[leftAndRightSide++]= tempArray[leftSide++];
    }
}
