package view.components;

import logic.Benchmark;
import logic.Benchmarker;
import sort.Sorter;
import utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

public class SortHeader extends JPanel {

    private final Benchmarker benchmarker;
    private final ResultPanel resultPanel;
    private final SortSelection sortSelection;

    public SortHeader(Benchmarker benchmarker, ResultPanel resultPanel, SortSelection sortSelection) {
        this.benchmarker = benchmarker;
        this.resultPanel = resultPanel;
        this.sortSelection = sortSelection;

        setLayout(new BorderLayout());
        init();

    }

    public void init() {
        JPanel west = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel east = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JLabel benchmarkLabel = new JLabel("Benchmark:");
        benchmarkLabel.setFont(Settings.fontBold);

        List<String> availableBenchmarks = benchmarker.getAvailableBenchmarks();
        JComboBox<String> benchmarks = new JComboBox<>(availableBenchmarks.toArray(new String[0]));
        benchmarks.setFont(Settings.font);

        JLabel arrayTypeLabel = new JLabel("Array-Typ:");
        arrayTypeLabel.setFont(Settings.fontBold);

        Benchmark.ArrayType[] types = Benchmark.ArrayType.class.getEnumConstants();
        JComboBox<Benchmark.ArrayType> arrayType = new JComboBox<>(types);
        arrayType.setFont(Settings.font);

        JButton options = new JButton("Optionen");
        options.setFont(Settings.font);
        options.addActionListener(e -> {
            Benchmark bm = benchmarker.getBenchmark((String) benchmarks.getSelectedItem());
            new OptionsDialog(bm.getName() + " - Optionen", "Übernehmen", bm.getOptions(), () -> {});
        });

        JButton start = new JButton("Start Benchmark");
        start.setFont(Settings.fontBold);
        start.addActionListener(e -> {
            start.setEnabled(false);
            benchmarks.setEnabled(false);
            options.setEnabled(false);
            arrayType.setEnabled(false);
            sortSelection.disableButtons();
            startBenchmark((String) benchmarks.getSelectedItem(), (Benchmark.ArrayType) arrayType.getSelectedItem(), () -> {
                start.setEnabled(true);
                benchmarks.setEnabled(true);
                options.setEnabled(true);
                arrayType.setEnabled(true);
                sortSelection.enableButtons();
            });
        });

        west.add(benchmarkLabel);
        west.add(benchmarks);
        west.add(options);
        west.add(Box.createHorizontalStrut(10));
        west.add(arrayTypeLabel);
        west.add(arrayType);

        east.add(start);


        add(east, BorderLayout.EAST);
        add(west, BorderLayout.WEST);
    }

    private void startBenchmark(String benchmark, Benchmark.ArrayType arrayType, Runnable finish) {
        HashMap<Sorter, Integer> testResult = benchmarker.testAlgorithms();
        resultPanel.setTestResult(testResult);

        Benchmark bm = benchmarker.getBenchmark(benchmark);
        resultPanel.initPanels(bm);

        bm.setArrayType(arrayType);

        new Thread(() -> {
            benchmarker.benchmark(bm);
            finish.run();
        }).start();
    }
}
