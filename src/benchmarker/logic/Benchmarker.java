package benchmarker.logic;

import benchmarker.sort.Sorter;
import benchmarker.utils.CSVUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Benchmarker {

    public static final int testsCount = 5;

    private Benchmark currentBenchmark;
    private final HashMap<String, Benchmark> benchmarks;

    private final HashMap<String, Class<? extends Sorter>> sortClasses;
    private final List<Sorter> sortPool;


    public Benchmarker() {
        benchmarks = new HashMap<>();
        sortClasses = new HashMap<>();
        sortPool = new ArrayList<>();
    }


    /**
     * @param benchmark Benchmark, der hinzugefügt werden soll
     */
    public void addBenchmark(Benchmark benchmark) {
        String name = benchmark.getName();
        benchmarks.put(name, benchmark);
    }

    /**
     * @return          Alle verfügbaren Benchmarks
     */
    public List<String> getAvailableBenchmarks() {
        return new ArrayList<>(benchmarks.keySet());
    }

    /**
     * @param benchmark Benchmark Name
     * @return          Benchmark
     */
    public Benchmark getBenchmark(String benchmark) {
        return benchmarks.get(benchmark);
    }


    /**
     * @return          Alle verfügbaren Sortieralgorithmen
     */
    public List<String> getAvailableSorter() {
        return new ArrayList<>(sortClasses.keySet());
    }

    /**
     * @return          Alle ausgewählten Algorithmen
     */
    public List<Sorter> getSortPool() {
        return sortPool;
    }

    /**
     * @param sorter    Klasse von einem Sorter
     */
    public void addSorterClass(Class<? extends Sorter> sorter) {
        String name = sorter.getSimpleName();
        sortClasses.put(name, sorter);
    }

    /**
     * Von dem jeweiligen Sortieralgorithmus wird eine Instanz erstellt und automatisch zum SortPool hinzugefügt
     *
     * @param name      Name des Sortieralgorithmus
     * @return          Instanz des Sortieralgorithmus
     */
    public Sorter initOne(String name) {
        return initOne(name, true);
    }


    /**
     *  Von dem jeweiligen Sortieralgorithmus wird eine Instanz und zurückgegeben.
     *  Man kann entscheiden, ob der Sorter automatisch dem SortPool hinzugefügt werden soll
     *
     * @param name          Name des Sortieralgorithmus
     * @param addToSortPool ob der Algorithmus dem SortPool hinzugefügt werden soll
     * @return              Instanz des Sortieralgorithmus
     */
    public Sorter initOne(String name, boolean addToSortPool) {
        Class<? extends Sorter> sorterClass = sortClasses.get(name);
        try {
            Sorter sorter = sorterClass.getDeclaredConstructor().newInstance();
            if (addToSortPool) {
                sortPool.add(sorter);
                sorter.initSorter();
            }
            return sorter;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param sorter    Sorter, der dem SortPool hinzugefügt werden soll
     */
    public void addToSortPool(Sorter sorter) {
        try {
            sorter.initSorter();
            this.sortPool.add(sorter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param sorter    Sorter, der aus dem SortPool entfernt werden soll
     */
    public void removeSorter(Sorter sorter) {
        sortPool.remove(sorter);
    }

    /**
     * Von allen verfügbaren Sortieralgorithmen wird eine Instanz erstellt und dem SortPool hinzugefügt
     */
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

    /**
     * @return      HashMap<Sorter, Integer> Mit Anzahl der erfolgreichen Tests für jeden Sorter
     */
    public HashMap<Sorter, Integer> testAlgorithms() {
        HashMap<Sorter, Integer> result = new HashMap<>();

        for (Sorter sorter : sortPool) {
            result.put(sorter, 0);
        }

        int[] arr = null;
        for (int test = 0; test < testsCount; test++) {
            switch (test) {
                case 0:
                    arr = new int [1000];
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = i;
                    }
                    break;
                case 1:
                    arr = new int [1000];
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = arr.length - i - 1;
                    }
                    break;
                case 2:
                    arr = new int [1000];
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] = (int) (Math.random() * 100);
                    }
                    break;
                case 3:
                    arr = new int[] {0};
                    break;
                case 4:
                    arr = new int[0];
                    break;
            }
            for (Sorter sorter : sortPool) {
                int[] toSort = Arrays.copyOf(arr, arr.length);
                int[] sorted = sorter.sort(toSort);
                if (isSorted(sorted, toSort.length)) {
                    result.put(sorter, result.get(sorter) + 1);
                    if (result.get(sorter) == testsCount) {
                        sorter.setTestsPassed(true);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Testet, ob ein Array sortiert wurde (egal ob auf- oder absteigend)
     *
     * @param arr       Array, welches zu überprüfen gilt
     * @param length    länge des "Soll-Arrays"
     * @return          Ob das Array richtig Sortiert wurde
     */
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

    /**
     * @param benchmark     Name des Benchmarks, der durchgeführt werden soll
     */
    public void benchmark(String benchmark) {
        if (benchmarks.get(benchmark) != null) {
            benchmark(benchmarks.get(benchmark));
        }
    }

    /**
     * @param benchmark     Instanz des Benchmarks, der durchgeführt werden soll
     */
    public void benchmark(Benchmark benchmark) {
        currentBenchmark = benchmark;
        benchmark.beforeBenchmark();
        benchmark.benchmark(sortPool);
    }

    /**
     * @return              Name des Benchmarks, der gerade durchgeführt wird, oder als letztes durchgeführt wurde
     */
    public Benchmark getCurrentBenchmark() {
        return currentBenchmark;
    }

    /**
     * Exportiert das Ergebnis des Benchmarks als CSV-Datei
     *
     * @return              Ob der Export erfolgreich abgeschlossen wurde
     * @throws IOException
     */
    public boolean exportResults() throws IOException {
        if (getCurrentBenchmark() == null) {
            return false;
        }

        File exportFolder = new File("exports");
        if (!exportFolder.exists()) {
            exportFolder.mkdir();
        }

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdc = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String exportFileName = getCurrentBenchmark().getName() + " " + sdc.format(cal.getTime());

        File file = new File("exports/" + exportFileName + ".csv");

        CSVUtils csvUtils = new CSVUtils(file);
        getCurrentBenchmark().exportResults(csvUtils);
        return csvUtils.writeFile();
    }
}
