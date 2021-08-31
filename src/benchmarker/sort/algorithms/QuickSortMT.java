package benchmarker.sort.algorithms;

import benchmarker.sort.Sorter;
import benchmarker.utils.options.OptionType;
import benchmarker.utils.options.Option;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class QuickSortMT extends Sorter {

    private ForkJoinPool pool;

    public QuickSortMT() {
        addOption(new Option("Threads", OptionType.NUMBER, 8));
        addOption(new Option("commonPool", OptionType.BOOL, false));
    }

    /**
     *
     * @return          Name des Sortieralgorithmus
     */
    @Override
    public String getName() {
        return "QuickSort (Multithreaded)";
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
     * @param toSort    Zu sortierendes Array
     * @return          Zu sortierendes Array
     */
    @Override
    public int[] sort(int[] toSort) {
        pool.invoke(new QuickSortMT.Sort(0, toSort.length - 1, toSort));
        return toSort;
    }

    public static class Sort extends RecursiveTask<Integer> {

        int start, end;
        int[] arr;

        /**
         *
         * @param unsortedLeftPart    unsortierter linker Teil des Arrays
         * @param unsortedRightPart   unsortierter rechter Teil des Arrays
         * @param arr                 Hilfsarray
         */
        private int partion(int unsortedLeftPart, int unsortedRightPart, int[] arr) {

            int i = unsortedLeftPart, j = unsortedRightPart;

            int pivote = new Random().nextInt(j - i) + i;

            int t = arr[j];
            arr[j] = arr[pivote];
            arr[pivote] = t;
            j--;

            while (i <= j) {

                if (arr[i] <= arr[unsortedRightPart]) {
                    i++;
                    continue;
                }

                if (arr[j] >= arr[unsortedRightPart]) {
                    j--;
                    continue;
                }

                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
                j--;
                i++;
            }

            t = arr[j + 1];
            arr[j + 1] = arr[unsortedRightPart];
            arr[unsortedRightPart] = t;
            return j + 1;
        }

        /**
         *
         * @param unsortedLeftPart    unsortierter linker Teil des Arrays
         * @param unsortedRightPart   unsortierter rechter Teil des Arrays
         * @param arr                 Hilfsarray
         */
        public Sort(int unsortedLeftPart, int unsortedRightPart, int[] arr) {
            this.arr = arr;
            this.start = unsortedLeftPart;
            this.end = unsortedRightPart;
        }

        /**
         *
         * @return          null zurückgeben
         */
        @Override
        protected Integer compute() {

            if (start >= end)
                return null;

            int p = partion(start, end, arr);

            QuickSortMT.Sort left = new QuickSortMT.Sort(start, p - 1, arr);

            QuickSortMT.Sort right = new QuickSortMT.Sort(p + 1, end, arr);

            left.fork();
            right.compute();

            left.join();

            return null;
        }
    }
}