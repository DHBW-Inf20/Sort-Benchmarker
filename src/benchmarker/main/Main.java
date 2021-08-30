package benchmarker.main;

import logic.Benchmarker;
import logic.benchmarks.DeviationBenchmark;
import logic.benchmarks.RuntimeBenchmark;
import sort.algorithms.*;
import utils.Addons;
import view.GUI;

public class Main {

    public static void main(String[] args) {
        Benchmarker benchmarker = new Benchmarker();

        benchmarker.addSorterClass(QuickSort.class);
        benchmarker.addSorterClass(QuickSortMT.class);
        benchmarker.addSorterClass(MergeSort.class);
        benchmarker.addSorterClass(MergeSortMT.class);

        benchmarker.addSorterClass(BubbleSort.class);
        benchmarker.addSorterClass(InsertionSort.class);
        benchmarker.addSorterClass(SelectionSort.class);

        benchmarker.addBenchmark(new RuntimeBenchmark());
        benchmarker.addBenchmark(new DeviationBenchmark());

        Addons.loadAddons(benchmarker);

        new GUI(benchmarker);
    }
}
