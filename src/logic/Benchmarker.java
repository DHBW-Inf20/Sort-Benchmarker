package logic;

import logic.benchmarks.DeviationBenchmark;
import logic.benchmarks.RuntimeBenchmark;
import sort.Sorter;
import sort.algorithms.QuickSort;
import sort.algorithms.QuickSortMT;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Benchmarker {

    private final HashMap<String, Benchmark> benchmarks;

    private final HashMap<String, Class<? extends Sorter>> sortClasses;
    private final List<Sorter> sortPool;

    public Benchmarker() {
        benchmarks = new HashMap<>();
        sortClasses = new HashMap<>();
        sortPool = new ArrayList<>();
    }


    public void addBenchmark(Benchmark benchmark) {
        String name = benchmark.getName();
        benchmarks.put(name, benchmark);
    }

    public List<String> getAvailableBenchmarks() {
        return new ArrayList<>(benchmarks.keySet());
    }

    public Benchmark getBenchmark(String benchmark) {
        return benchmarks.get(benchmark);
    }


    public List<String> getAvailableSorter() {
        return new ArrayList<>(sortClasses.keySet());
    }

    public List<Sorter> getSortPool() {
        return sortPool;
    }

    public void addSorterClass(Class<? extends Sorter> sorter) {
        String name = sorter.getSimpleName();
        sortClasses.put(name, sorter);
    }

    public Sorter initOne(String name) {
        return initOne(name, true);
    }

    public Sorter initOne(String name, boolean addToSortPool) {
        Class<? extends Sorter> sorterClass = sortClasses.get(name);
        try {
            Sorter sorter = sorterClass.getDeclaredConstructor().newInstance();
            if (addToSortPool) {
                sortPool.add(sorter);
                sorter.initSorting();
            }
            return sorter;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addToSortPool(Sorter sorter) {
        this.sortPool.add(sorter);
        sorter.initSorting();
    }

    public void removeSorter(Sorter sorter) {
        sortPool.remove(sorter);
    }

    public void initAll() {
        for (String name : sortClasses.keySet()) {
            Class<? extends Sorter> sorterClass = sortClasses.get(name);
            try {
                Sorter sorter = sorterClass.getDeclaredConstructor().newInstance();
                addToSortPool(sorter);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public void testAlgorithms() {
        int[] arr = new int [100000];
        for (int test = 0; test < 3; test++) {
            switch (test) {
                case 0:
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = (int) (Math.random() * 100);
                    }
                    break;
                case 1:
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = i;
                    }
                    break;
                case 2:
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = arr.length - i - 1;
                    }
                    break;
            }
            for (Sorter sorter : sortPool) {
                int[] toSort = Arrays.copyOf(arr, arr.length);
                int[] sorted = sorter.sort(toSort);
                if (isSorted(sorted, toSort.length)) {
                    System.out.println(sorter.getDisplayName() + ": Test-" + (test+1) + " succeeded");
                    // TODO: sort succeeded
                } else {
                    System.out.println(sorter.getDisplayName() + ": Test-" + (test+1) + " not succeeded");
                    // TODO: sort not succeeded
                }
            }
        }
    }

    private boolean isSorted(int[] arr, int length) {
        if (arr.length != length) return false;
        int direction = 0; // 1 = ASC; 2 = DESC
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                if (direction == 2) {
                    return false;
                }
                direction = 1;
            } else if (arr[i] > arr[i + 1]) {
                if (direction == 1) {
                    return false;
                }
                direction = 2;
            }
        }
        return true;
    }

    public void sort(int[] array) {
        for (Sorter sorter : sortPool) {
            int[] toSort = Arrays.copyOf(array, array.length);
            System.out.println("Soter: " + sorter.getName());
            long start = System.currentTimeMillis();
            sorter.sort(toSort);
            long stop = System.currentTimeMillis();
            System.out.println("Duration: " + (stop - start) + " ms");
            System.out.println();
        }
    }

    public HashMap<Sorter, Object> benchmark(String benchmark) {
        if (benchmarks.get(benchmark) != null) {
            return this.benchmark(benchmarks.get(benchmark));
        }
        return null;
    }

    public HashMap<Sorter, Object> benchmark(Benchmark benchmark) {
        return benchmark.benchmark(sortPool);
    }

    public static void main(String[] args) {
        Benchmarker benchmarker = new Benchmarker();

        benchmarker.addSorterClass(QuickSort.class);
        benchmarker.addSorterClass(QuickSortMT.class);

        benchmarker.initAll();

        benchmarker.testAlgorithms();

        System.out.println("\n\n\n");

        Benchmark bm = new RuntimeBenchmark();
        bm.setArrayType(Benchmark.ArrayType.RANDOM);
        HashMap<Sorter, Object> result = benchmarker.benchmark(bm);
        for (Sorter sorter : result.keySet()) {
            System.out.println(sorter.getDisplayName() + ": " + result.get(sorter));
        }
//        Benchmarker bench = new Benchmarker();
//        System.out.println(bench.isSorted(new int[] {1, 2, 3, 3, 4}, 5));
//        System.out.println(bench.isSorted(new int[] {5, 4, 4, 3 ,2}, 5));
//        System.out.println(bench.isSorted(new int[] {5, 4, 5, 3 ,2}, 5));
//        System.out.println(bench.isSorted(new int[] {1, 2, 3, 1, 4}, 5));
    }
}
