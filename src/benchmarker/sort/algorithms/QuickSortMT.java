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

    @Override
    public int[] sort(int[] toSort) {
        pool.invoke(new QuickSortMT.Sort(0, toSort.length - 1, toSort));
        return toSort;
    }

    public static class Sort extends RecursiveTask<Integer> {

        int start, end;
        int[] arr;

        private int partion(int start, int end, int[] arr) {

            int i = start, j = end;

            int pivote = new Random().nextInt(j - i) + i;

            int t = arr[j];
            arr[j] = arr[pivote];
            arr[pivote] = t;
            j--;

            while (i <= j) {

                if (arr[i] <= arr[end]) {
                    i++;
                    continue;
                }

                if (arr[j] >= arr[end]) {
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
            arr[j + 1] = arr[end];
            arr[end] = t;
            return j + 1;
        }

        public Sort(int start, int end, int[] arr) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

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