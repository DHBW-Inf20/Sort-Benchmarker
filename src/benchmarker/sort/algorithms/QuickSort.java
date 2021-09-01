package benchmarker.sort.algorithms;

import benchmarker.sort.Sorter;

public class QuickSort extends Sorter {

    public static int[] array;

    /**
     *
     * @param unsortedLeftPart      unsortiertes Teilarray links vom Pivotelement
     * @param unsortedRightPart     unsortiertes Teilarray rechts vom Pivotelement
     * @return                      Rückgabe teilsortiertes Array
     */
    public int[] sort(int unsortedLeftPart, int unsortedRightPart) {
        int dividedArray;
        if (unsortedLeftPart < unsortedRightPart) {
            dividedArray = partition(unsortedLeftPart, unsortedRightPart);
            sort(unsortedLeftPart, dividedArray); //rekursiver Aufruf für unsortiertes linkes Teilarray
            sort(dividedArray + 1, unsortedRightPart); //rekursiver Aufruf für unsortiertes rechtes Teilarray
        }
        return array;
    }

    /**
     * Das unsortierte Array wird in zwei Teilfelder aufgeteilt und das mittlere Element wird als Pivotelement ausgewählt.
     * @param unsortedLeftPart      unsortiertes Teilarray links vom Pivotelement
     * @param unsortedRightPart     unsortiertes Teilarray rechts vom Pivotelement
     * @return                      sortierter Teil des Arrays
     */
    int partition(int unsortedLeftPart, int unsortedRightPart) {

        int sortedLeftPart, sortedRightPart, pivot = array[(unsortedLeftPart + unsortedRightPart) / 2]; //pivot element in der Mitte wählen
        sortedLeftPart = unsortedLeftPart - 1;
        sortedRightPart = unsortedRightPart + 1;
        while (true) {
            do {
                sortedLeftPart++;
            } while (array[sortedLeftPart] < pivot); //solange die Elemente kleiner sind als das Pivotelement

            do {
                sortedRightPart--;
            } while (array[sortedRightPart] > pivot); //solange die Elemente größer sind als das Pivotelement

            if (sortedLeftPart < sortedRightPart) { //zwei Elemente tauschen
                int k = array[sortedLeftPart];
                array[sortedLeftPart] = array[sortedRightPart];
                array[sortedRightPart] = k;
            } else {
                return sortedRightPart;
            }
        }
    }

    /**
     *
     * @return                      Name des Sortieralgorithmus
     */
    @Override
    public String getName() {
        return "QuickSort";
    }

    /**
     *
     * @param toSort                Zu sortierendes Array
     * @return                      sortiertes Array
     */
    @Override
    public int[] sort(int[] toSort) {
        array = toSort;
        this.sort(0, array.length - 1);
        return array;
    }
}