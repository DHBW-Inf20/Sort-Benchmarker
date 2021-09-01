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

        // Sortieralgorithmen Klassen hinzugügen
        benchmarker.addSorterClass(QuickSort.class);
        benchmarker.addSorterClass(QuickSortMT.class);
        benchmarker.addSorterClass(MergeSort.class);
        benchmarker.addSorterClass(MergeSortMT.class);

        // Benchmarker Instanzen hinzufügen
        benchmarker.addBenchmark(new RuntimeBenchmark());
        benchmarker.addBenchmark(new DeviationBenchmark());

        // lade externe Sortieralgorithmen und Benchmarks
        Addons.loadAddons(benchmarker);

        // Erstelle GUI
        GUI gui = new GUI(benchmarker);
        gui.setVisible(true);
    }
}
