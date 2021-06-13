package sort.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class MergeSort
{
    private List<Integer> unsortedLeftPart = new ArrayList<>();
    private List<Integer> unsortedRightPart = new ArrayList<>();
    private List<Integer> sortedLeftPart = new ArrayList<>();
    private List<Integer> sortedRightPart = new ArrayList<>();
    private List<Integer> sortedList = new ArrayList<>();

    public List<Integer> GetSortedList (List<Integer> unsortedList)
    {
        if (unsortedList.size() == 1)
        {
            return unsortedList;
        }

        for (int i = 0; i < Math.floor(unsortedList.size() / 2); i++)
        {
            unsortedLeftPart.add(unsortedList.get(i));
        }
        for (int i = (int) Math.floor(unsortedList.size() / 2); i < unsortedList.size(); i++)
        {
            unsortedRightPart.add(unsortedList.get(i));
        }

        sortedLeftPart = GetSortedList(unsortedLeftPart);
        sortedRightPart = GetSortedList(unsortedRightPart);

        for (int i = 0; i < sortedLeftPart.size() + sortedRightPart.size() + 1; i++)
        {
            int firstElementOfLeftPart = sortedLeftPart.get(0);
            int firstElementOfRightPart = sortedRightPart.get(0);

            if (firstElementOfLeftPart <= sortedRightPart.get(0))
            {
                sortedList.add(firstElementOfLeftPart);
                sortedLeftPart.remove(0);
            }
            else
            {
                sortedList.add(firstElementOfRightPart);
                sortedRightPart.remove(0);
            }
        }

        return sortedList;
    }
}
