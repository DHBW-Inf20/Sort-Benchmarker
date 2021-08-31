package benchmarker.main;

import benchmarker.logic.Benchmarker;
import benchmarker.logic.benchmarks.DeviationBenchmark;
import benchmarker.logic.benchmarks.RuntimeBenchmark;
import benchmarker.sort.algorithms.*;
import benchmarker.utils.Addons;
import benchmarker.view.GUI;

public class Main {

    public static void main(String[] args) {
        Benchmarker benchmarker = new Benchmarker();

        benchmarker.addSorterClass(QuickSort.class);
        benchmarker.addSorterClass(QuickSortMT.class);
        benchmarker.addSorterClass(MergeSort.class);
        benchmarker.addSorterClass(MergeSortMT.class);

        benchmarker.addSorterClass(InsertionSort.class);
        benchmarker.addSorterClass(SelectionSort.class);

        benchmarker.addBenchmark(new RuntimeBenchmark());
        benchmarker.addBenchmark(new DeviationBenchmark());

        Addons.loadAddons(benchmarker);

        new GUI(benchmarker);
    }
}
