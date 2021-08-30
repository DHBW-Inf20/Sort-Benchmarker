package view;

import logic.Benchmarker;
import logic.benchmarks.DeviationBenchmark;
import logic.benchmarks.RuntimeBenchmark;
import sort.algorithms.*;
import view.components.ResultPanel;
import view.components.SortHeader;
import view.components.SortSelection;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

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
        this.setMinimumSize(new Dimension(1250, 400));
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

        ResultPanel resultPanel = new ResultPanel(benchmarker);

        SortHeader sortHeader = new SortHeader(benchmarker, resultPanel, sortSelection);

        panel.add(sortSelection, BorderLayout.WEST);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(sortHeader, BorderLayout.NORTH);
        mainPanel.add(resultPanel, BorderLayout.CENTER);
        panel.add(mainPanel, BorderLayout.CENTER);

        panel.setVisible(true);
    }
}
