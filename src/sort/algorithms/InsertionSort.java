package sort.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InsertionSort
{
    public List<Integer> GetSortedList (List<Integer> sortedList, List<Integer> unsortedList)
    {
        if (unsortedList.size() == 0)
        {
            return sortedList;
        }

        if (sortedList == null)
        {
            sortedList.add(unsortedList.get(0));
            unsortedList.remove(0);
        }
        else
        {
            if (sortedList.get(-1) >= unsortedList.get(0))
            {
                sortedList.add(unsortedList.get(0));
                unsortedList.remove(0);
            }
            else
            {
                int lastSortedElement = sortedList.get(-1);
                List<Integer> tempList = new ArrayList<>();
                boolean searching = true;

                while (searching)
                {
                    if (sortedList.get(-1) > unsortedList.get(0))
                    {
                        tempList.add(0, sortedList.get(-1));
                        sortedList.remove(-1);
                    }
                    else
                    {
                        sortedList.add(unsortedList.get(0));
                        sortedList.addAll(tempList);
                        unsortedList.remove(0);

                        searching = false;
                    }
                }
            }
        }

        return GetSortedList(sortedList, unsortedList);
    }
}
