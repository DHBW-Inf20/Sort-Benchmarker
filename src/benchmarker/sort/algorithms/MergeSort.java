package benchmarker.sort.algorithms;

import benchmarker.sort.Sorter;

public class MergeSort extends Sorter
{

    @Override
    public String getName() {
        return "MergeSort";
    }
    private int[] leftarray;
    private int[] tempArray;    // Hilfsarray
    private int length;

    @Override
    public int[] sort(int[] toSort) {

        this.leftarray =toSort;
        length =toSort.length;
        tempArray =new int[length];
        mergesort(0, length -1);
        return toSort;
    }

    private void mergesort(int lo, int hi)
    {
        if (lo<hi) //if there is more than 1 element
        {
            int m=lo+(hi-lo)/2;
            mergesort(lo, m);
            mergesort(m+1, hi);
            merge(lo, m, hi);
        }
    }

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
