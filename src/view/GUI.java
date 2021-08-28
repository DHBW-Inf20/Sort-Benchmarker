package view;

import logic.Benchmarker;
import logic.benchmarks.DeviationBenchmark;
import logic.benchmarks.RuntimeBenchmark;
import sort.algorithms.*;
import view.components.SortHeader;
import view.components.SortSelection;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private final Benchmarker benchmarker;

    public GUI(Benchmarker benchmarker) {
        super("Sort-Benchmarker");
        this.benchmarker = benchmarker;
        this.initFrame();
        this.setVisible(true);
    }

    private void initFrame() {
        this.setSize(1400, 800);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        this.initPanel(panel);
        this.add(panel);
    }

    private void initPanel(JPanel panel) {
        panel.setLayout(new BorderLayout());

        SortSelection sortSelection = new SortSelection(benchmarker);
        // Set width for sort selection panel
        sortSelection.setPreferredSize(new Dimension(350, 0));
        panel.add(sortSelection, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        JPanel sortHeader = new SortHeader(benchmarker);
        centerPanel.add(sortHeader, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

//        JButton btn = new JButton("Start");
//        btn.addActionListener(e -> {
//            new Thread(() -> {
//                benchmarker.testAlgorithms();
//                Benchmark bm = new DurationBenchmark();
//                HashMap<Sorter, Object> result = benchmarker.benchmark(bm);
//                for (Sorter sorter : result.keySet()) {
//                    System.out.println(sorter.getDisplayName() + ": " + result.get(sorter));
//                }
//            }).start();
//        });
//        panel.add(btn, BorderLayout.CENTER);

        panel.setVisible(true);
    }

    public static void main(String[] args) {
        Benchmarker benchmarker = new Benchmarker();

        benchmarker.addSorterClass(QuickSort.class);
        benchmarker.addSorterClass(QuickSortMT.class);
        benchmarker.addSorterClass(MergeSort.class);
//        benchmarker.addSorterClass(MergeSortMT.class);

        benchmarker.addSorterClass(BubbleSort.class);
        benchmarker.addSorterClass(InsertionSort.class);

        benchmarker.addBenchmark(new RuntimeBenchmark());
        benchmarker.addBenchmark(new DeviationBenchmark());

        new GUI(benchmarker);
    }
}
