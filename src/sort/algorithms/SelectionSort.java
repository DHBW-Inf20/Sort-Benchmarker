package sort.algorithms;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort
{
    private List<Integer> sortedList = new ArrayList<>();

    public List<Integer> GetSortedList (List<Integer> unsortedList)
    {
        int firstElement = unsortedList.get(0);
        int minimum = firstElement;
        int indexOfMinimum = 0;
        List<Integer> partList = new ArrayList<>();

        if (unsortedList.size() == 1)
        {
            return unsortedList;
        }

        for (int i = 1; i < unsortedList.size() + 1; i++)
        {
            if (i == unsortedList.size())
            {
                unsortedList.set(0, minimum);
                unsortedList.set(indexOfMinimum, firstElement);

                partList.subList(1, unsortedList.size() - 1);

                partList = GetSortedList(partList);
            }

        }

        sortedList.add(minimum);
        sortedList.addAll(partList);

        return sortedList;
    }
}
