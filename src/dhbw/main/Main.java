package dhbw.main;

public class Main
{
    public static void main(String[] args) throws InterruptedException {



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

        Thread.sleep(10000);


    }
}
