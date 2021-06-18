package sort.algorithms;

import java.util.ArrayList;
import java.util.List;

public class QuickSort
{
    public List<Integer> GetSortedList (List<Integer> unsortedList, int pivotID)
    {
        int pivotElement;
        List<Integer> unsortedLeftPart = new ArrayList<>();
        List<Integer> unsortedRightPart = new ArrayList<>();
        List<Integer> sortedLeftPart = new ArrayList<>();
        List<Integer> sortedRightPart = new ArrayList<>();
        List<Integer> sortedList = new ArrayList<>();

        if (unsortedList.size() == 1)
        {
            return unsortedList;
        }

        if (pivotID == 0)
        {
            pivotElement = unsortedList.get(0);

            for (int i = 1; i < unsortedList.size(); i++)
            {
                if (unsortedList.get(i) <= pivotElement)
                {
                    unsortedLeftPart.add(unsortedList.get(i));
                }
                else
                {
                    unsortedRightPart.add(unsortedList.get(i));
                }

                sortedLeftPart = GetSortedList(unsortedLeftPart, pivotID);
                sortedRightPart = GetSortedList(unsortedRightPart, pivotID);
            }
        }
        else if (pivotID == 1)
        {
            pivotElement = unsortedList.get(unsortedList.size() - 1);

            for (int i = 0; i < unsortedList.size() - 1; i++)
            {
                if (unsortedList.get(i) <= pivotElement)
                {
                    unsortedLeftPart.add(unsortedList.get(i));
                } else {
                    unsortedRightPart.add(unsortedList.get(i));
                }

                sortedLeftPart = GetSortedList(unsortedLeftPart, pivotID);
                sortedRightPart = GetSortedList(unsortedRightPart, pivotID);
            }
        }
        else
        {
            pivotElement = unsortedList.get(unsortedList.size() / 2);
            int indexOfPivotElement = unsortedList.indexOf(unsortedList.get(unsortedList.size() / 2));

            for (int i = 0; i < unsortedList.size(); i++)
            {
                if (i == indexOfPivotElement)
                {
                    continue;
                }

                if (unsortedList.get(i) <= pivotElement)
                {
                    unsortedLeftPart.add(unsortedList.get(i));
                } else {
                    unsortedRightPart.add(unsortedList.get(i));
                }

                sortedLeftPart = GetSortedList(unsortedLeftPart, pivotID);
                sortedRightPart = GetSortedList(unsortedRightPart, pivotID);
            }
        }

        sortedList.addAll(unsortedLeftPart);
        sortedList.add(pivotElement);
        sortedList.addAll(unsortedRightPart);

        return sortedList;
    }
}
