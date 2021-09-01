package benchmarker.sort.algorithms;

import benchmarker.sort.Sorter;
import benchmarker.utils.options.OptionType;
import benchmarker.utils.options.Option;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MergeSortMT extends Sorter {

    private ForkJoinPool pool;

    public MergeSortMT() {
        addOption(new Option("Threads", OptionType.NUMBER, 8, 1, Integer.MAX_VALUE));
        addOption(new Option("commonPool", OptionType.BOOL, false));

    }

    /**
     *
     * @return              Name des Sortieralgorithmus
     */
    @Override
    public String getName() {
        return "MergeSort (Multithreaded)";
    }

    @Override
    public void initSorter() {
        if ((boolean) getValue("commonPool")) {
            pool = ForkJoinPool.commonPool();
        } else {
            pool = new ForkJoinPool((int) getValue("Threads"));
        }
    }

    /**
     *
     * @param toSort        Zu sortierendes Array
     * @return              Zu sortierendes Array
     */
    @Override
    public int[] sort(int[] toSort) {
        pool.invoke(new MergeSortMT.Sort(toSort));
        return toSort;
    }

    /**
     *
     * @param firstHalf     erste Hälfte von zu sortierendem Array
     * @param secondHalf    zweite Hälfte von zu sortierendem Array
     * @param array         Hilfsarray
     */
    public static void merge(int[] firstHalf, int[] secondHalf, int[] array) {
        int startFirst = 0;
        int startSecond = 0;
        int currentIndexArray = 0;

        //setzt das kleinere Element auf das Array
        while (startFirst < firstHalf.length && startSecond < secondHalf.length) {
            if (firstHalf[startFirst] < secondHalf[startSecond]) {
                array[currentIndexArray] = firstHalf[startFirst];
                currentIndexArray++;
                startFirst++;
            } else {
                array[currentIndexArray] = secondHalf[startSecond];
                currentIndexArray++;
                startSecond++;
            }
        }

        //Die Elemente werden richtig sortiert zurück in startFirst geschrieben
        while (startFirst < firstHalf.length) {
            array[currentIndexArray] = firstHalf[startFirst];
            currentIndexArray++;
            startFirst++;
        }

        //Die Elemente werden richtig sortiert zurück in startSecond geschrieben
        while (startSecond < secondHalf.length) {
            array[currentIndexArray] = secondHalf[startSecond];
            currentIndexArray++;
            startSecond++;
        }
    }

    public static class Sort extends RecursiveTask<int[]> {
        private int[] array;

        public Sort(int[] array) {
            this.array = array;
        }

        /**
         *
         * @return          leeres Array
         */
        @Override
        protected int[] compute() {

            if (array.length > 1) {
                int middle = array.length / 2;

                // Obtain the first half
                int[] firstHalf = new int[middle];
                System.arraycopy(array, 0, firstHalf, 0, middle);

                // Obtain the second half
                int[] secondHalf = new int[array.length - middle];
                System.arraycopy(array, middle, secondHalf, 0, array.length - middle);

                // Recursively sort the two halves
                Sort firstHalfSort = new Sort(firstHalf);
                Sort secondHalfSort = new Sort(secondHalf);
                // Invoke declared tasks
                invokeAll(firstHalfSort, secondHalfSort);

                //Merge firstHalf with secondHalf into our array
                MergeSortMT.merge(firstHalf, secondHalf, array);

            }
            return new int[0];
        }
    }
}

