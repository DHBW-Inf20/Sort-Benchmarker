package sort.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class MergeSort
{
    public List<Integer> GetSortedList (List<Integer> unsortedList)
    {
        List<Integer> unsortedLeftPart = new ArrayList<>();
        List<Integer> unsortedRightPart = new ArrayList<>();
        List<Integer> sortedLeftPart = new ArrayList<>();
        List<Integer> sortedRightPart = new ArrayList<>();
        List<Integer> sortedList = new ArrayList<>();

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
