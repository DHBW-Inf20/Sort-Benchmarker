package view.components;

import logic.Benchmark;
import logic.Benchmarker;
import sort.Sorter;
import utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class SortHeader extends JPanel {

    private final Benchmarker benchmarker;

    public SortHeader(Benchmarker benchmarker) {
        this.benchmarker = benchmarker;

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
            new Thread(() -> {
                benchmarker.testAlgorithms();
                Benchmark bm = benchmarker.getBenchmark((String) benchmarks.getSelectedItem());
                Benchmark.ArrayType at = (Benchmark.ArrayType) arrayType.getSelectedItem();
                bm.setArrayType(at);
                HashMap<Sorter, Object> result = benchmarker.benchmark(bm);
                for (Sorter sorter : result.keySet()) {
                    System.out.println(sorter.getDisplayName() + ": " + result.get(sorter));
                }
            }).start();
        });

        west.add(benchmarkLabel);
        west.add(benchmarks);
        west.add(options);
        west.add(Box.createRigidArea(new Dimension(10, 0)));
        west.add(arrayTypeLabel);
        west.add(arrayType);

        east.add(start);


        add(east, BorderLayout.EAST);
        add(west, BorderLayout.WEST);
    }
}
