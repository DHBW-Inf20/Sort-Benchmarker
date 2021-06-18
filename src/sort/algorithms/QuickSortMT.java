package sort.algorithms;

import sort.Sorter;

import java.io.*;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class QuickSortMT extends RecursiveTask<Integer>, Sorter {

    int start, end;
    int[] arr;

    private int partion(int start, int end, int[] arr) {

        int i = start, j = end;

        // Decide random pivot
        int pivote = new Random()
                .nextInt(j - i)
                + i;

        // Swap the pivote with end
        // element of array;
        int t = arr[j];
        arr[j] = arr[pivote];
        arr[pivote] = t;
        j--;

        // Start partioning
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

        // Swap pivote to its
        // correct position
        t = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = t;
        return j + 1;
    }

    // Function to implement
    // QuickSort method
    public QuickSortMT(int start, int end, int[] arr)
    {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute()
    {
        // Base case
        if (start >= end)
            return null;

        // Find partion
        int p = partion(start, end, arr);

        // Divide array
        QuickSortMT left
                = new QuickSortMT(start, p - 1, arr);

        QuickSortMT right
                = new QuickSortMT(p + 1, end, arr);

        // Left subproblem as separate thread
        left.fork();
        right.compute();

        // Wait untill left thread complete
        left.join();

        // We don't want anything as return
        return null;
    }

    // Driver Code
    public static void main(String args[])
    {
        int n = 7;
        int[] arr = { 54, 64, 95, 82, 12, 32, 63 };

        // Forkjoin ThreadPool to keep
        // thread creation as per resources
        ForkJoinPool pool
                = ForkJoinPool.commonPool();

        // Start the first thread in fork
        // join pool for range 0, n-1
        pool.invoke(
                new QuickSortMT(0, n - 1, arr));

        // Print shorted elements
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int[] sort(int[] toSort) {
        return new int[0];
    }
}