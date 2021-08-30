package dhbw.main;

import logic.Benchmarker;
import logic.DataSave;
import sort.algorithms.MergeSort;

import java.util.Arrays;

public class Main
{
    public static void main(String[] args) throws InterruptedException {

        DataSave.Save("Quick Sort",1000, 10.1f, 12.5f, 11.9f, 5.2f, 15.7f,true);

        /*
        int[] unsortedArrayList = {9,5,3,8,2,7,1};

        List<Integer> unsortedList = new ArrayList<>();

        for (int i = 0; i < unsortedArrayList.length; i++)
        {
            unsortedList.add(unsortedArrayList[i]);
        }
        System.out.println(unsortedList);

        MergeSort mergeSort = new MergeSort();
        System.out.println("Merge Sort:");
        //List<Integer> sortedListWithMergeSort = mergeSort.GetSortedList(unsortedList);
        //System.out.println(sortedListWithMergeSort);

        QuickSort quickSort = new QuickSort();
        System.out.println("Quick Sort:");
        //List<Integer> sortedListWithQuickSort = quickSort.GetSortedList(unsortedList, 0);
        //System.out.println(sortedListWithQuickSort);

        Thread.sleep(10000);*/

        Benchmarker benchmarker = new Benchmarker();

        benchmarker.addSorterClass(MergeSort.class);

        benchmarker.initAll();
        int[] unsortedArray = {9,5,3,8,2,7,1};
        int[] sortedArray = benchmarker.getSortPool().get(0).sort(unsortedArray);
        System.out.println(Arrays.toString(sortedArray));

    }
}
