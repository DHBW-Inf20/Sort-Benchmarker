package logic;

import sort.Sorter;
import sort.algorithms.QuickSort;
import sort.algorithms.QuickSortMT;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Benchmarker {

    private HashMap<String, Class<? extends Sorter>> sortClasses;
    private List<Sorter> sortPool;

    public Benchmarker() {
        sortClasses = new HashMap<>();
        sortPool = new ArrayList<>();
    }

    public List<Sorter> getSortPool() {
        return sortPool;
    }

    public void addSorterClass(Class<? extends Sorter> sorter) {
        String name = sorter.getSimpleName();
        sortClasses.put(name, sorter);
    }

    public void sort(int[] array) {
        for (String name : sortClasses.keySet()) {
            int[] toSort = Arrays.copyOf(array, array.length);
            Class<? extends Sorter> sorterClass = sortClasses.get(name);
            try {
                Sorter sorter = sorterClass.getDeclaredConstructor().newInstance();
                System.out.println("Soter: " + name);
                long start = System.currentTimeMillis();
                sorter.sort(toSort);
                long stop = System.currentTimeMillis();
                System.out.println("Duration: " + (stop - start) + " ms");
                System.out.println();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = new int [100000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100000000);
        }

        Benchmarker benchmarker = new Benchmarker();

        benchmarker.addSorterClass(QuickSort.class);
        benchmarker.addSorterClass(QuickSortMT.class);

        benchmarker.sort(arr);
    }
}
