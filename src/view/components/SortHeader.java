package view.components;

import logic.Benchmark;
import logic.Benchmarker;

import javax.swing.*;
import java.awt.*;
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

        Font font = new Font("Arial", Font.BOLD, 20);

        JLabel benchmarkLabel = new JLabel("Benchmark:");
        benchmarkLabel.setFont(font);
        List<String> availableBenchmarks = benchmarker.getAvailableBenchmarks();
        JComboBox<String> benchmarks = new JComboBox<>(availableBenchmarks.toArray(new String[0]));
        benchmarks.setFont(font);
        JButton options = new JButton("Optionen");
        options.setFont(font);
        JButton start = new JButton("Start Benchmark");
        start.setFont(font);

        west.add(benchmarkLabel);
        west.add(benchmarks);
        west.add(options);
        east.add(start);

        add(east, BorderLayout.EAST);
        add(west, BorderLayout.WEST);
    }
}
