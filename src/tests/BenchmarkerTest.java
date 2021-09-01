package tests;

import benchmarker.logic.Benchmark;
import benchmarker.logic.Benchmarker;
import benchmarker.logic.benchmarks.RuntimeBenchmark;
import benchmarker.sort.Sorter;
import benchmarker.sort.algorithms.MergeSort;
import benchmarker.sort.algorithms.QuickSort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BenchmarkerTest {

    private static Benchmarker benchmarker;
    private static RuntimeBenchmark runtimeBenchmark;

    @BeforeAll
    static void setup() {
        benchmarker = new Benchmarker();
        runtimeBenchmark = new RuntimeBenchmark();
        benchmarker.addBenchmark(runtimeBenchmark);
        benchmarker.addSorterClass(QuickSort.class);
        benchmarker.addSorterClass(MergeSort.class);
    }

    /**
     * Testet, ob Benchmarks richtig hinzugefügt werden
     */
    @Test
    void addedBenchmarkTest() {
        assertEquals(1, benchmarker.getAvailableBenchmarks().size());
        assertEquals("Laufzeit Benchmark", benchmarker.getAvailableBenchmarks().get(0));
        assertEquals(runtimeBenchmark, benchmarker.getBenchmark("Laufzeit Benchmark"));
    }

    /**
     * Testet, ob Sortieralgorithmen richtig hinzugefügt werden
     */
    @Test
    void addedSortAlgorithmTest() {
        assertEquals(2, benchmarker.getAvailableSorter().size());
        assertEquals("QuickSort", benchmarker.getAvailableSorter().get(0));
    }

    /**
     * Testet ob Algorithmen richtig dem SortPool hinzugefügt werden
     */
    @Test
    void sortPoolTest() {
        benchmarker.initAll();
        assertEquals(2, benchmarker.getSortPool().size());
    }

    /**
     * Testet, ob Sortieralgorithmen richtig überprüft werden
     */
    @Test
    void testAlgorithmTest() {
        HashMap<Sorter, Integer> result = benchmarker.testAlgorithms();
        for (Sorter sorter : result.keySet()) {
            assertEquals(5, result.get(sorter));
        }
    }

    /**
     * Testet ob der Benchmark funktioniert
     */
    @Test
    void benchmarkerTest() {
        runtimeBenchmark.setValue("Iterationen", 2);
        runtimeBenchmark.setValue("Array-Größe", 100);
        benchmarker.benchmark(runtimeBenchmark);
        assertEquals(runtimeBenchmark, benchmarker.getCurrentBenchmark());
    }
}
