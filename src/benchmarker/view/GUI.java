package benchmarker.view;

import benchmarker.logic.Benchmarker;
import benchmarker.view.components.ResultPanel;
import benchmarker.view.components.SortHeader;
import benchmarker.view.components.SortSelection;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private final Benchmarker benchmarker;

    /**
     * @param benchmarker   Benchmarker Instanz
     */
    public GUI(Benchmarker benchmarker) {
        super("Sort-Benchmarker");
        this.benchmarker = benchmarker;
        this.initFrame();
    }

    /**
     * Initialisiere Frame
     */
    private void initFrame() {
        this.setSize(1400, 800);
        this.setMinimumSize(new Dimension(1250, 400));
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        this.initPanel(panel);
        this.add(panel);
    }

    /**
     * initialisiert SortSelection, ResultPanel und SortHeader und fügt sie dem MainPanel hinzu
     *
     * @param panel     MainPanel des Frames
     */
    private void initPanel(JPanel panel) {
        panel.setLayout(new BorderLayout());

        SortSelection sortSelection = new SortSelection(benchmarker);
        sortSelection.setPreferredSize(new Dimension(350, 0)); // Setzt Breite des SortSelection Panels

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
