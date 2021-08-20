package view;

import logic.Benchmarker;
import sort.algorithms.*;
import view.components.SortSelection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {

    private Benchmarker benchmarker;

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

        new GUI(benchmarker);
    }
}
