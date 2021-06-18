package sort.algorithms;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort
{

    public List<Integer> GetSortedList (List<Integer> list, boolean swapped)
    {
        List<Integer> sortedList = new ArrayList<>();
        int firstElement;
        int secondElement;

        if (swapped)
        {
            swapped = false;

            for (int i = 0; i <list.size() - 1; i++)
            {
                firstElement = list.get(i);
                secondElement = list.get(i + 1);

                if (firstElement > secondElement)
                {
                    list.set(i, secondElement);
                    list.set(i + 1, firstElement);

                    swapped = true;
                }
            }

            GetSortedList(list, swapped);
        }

        return list;
    }
}
