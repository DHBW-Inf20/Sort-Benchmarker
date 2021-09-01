package benchmarker.view.components;

import benchmarker.logic.Benchmark;
import benchmarker.logic.Benchmarker;
import benchmarker.sort.Sorter;
import benchmarker.utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class ResultPanel extends JPanel {

    private final Benchmarker benchmarker;

    private final JPanel panel;

    private JButton exportBtn;

    private final HashMap<Sorter, JPanel> sortResultPanels;
    public HashMap<Sorter, Integer> testResult;

    /**
     * Im ResultPanel werden die Ergebnisse des Benchmarks angezeigt
     *
     * @param benchmarker   Benchmarker Instanz
     */
    public ResultPanel(Benchmarker benchmarker) {
        this.benchmarker = benchmarker;

        this.sortResultPanels = new HashMap<>();

        setLayout(new BorderLayout());

        panel = new JPanel();
        initPanel();
        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);

        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exportBtn = new JButton("Exportieren (CSV)");
        exportBtn.setFont(Settings.font);
        exportBtn.setEnabled(false); // Wird initial deaktiviert und nach dem Benchmark aktiviert
        exportBtn.addActionListener(e -> {
            try {
                if (benchmarker.exportResults()) {
                    JOptionPane.showMessageDialog(this, "Erfolgreich exportiert!\n Die Datei befindet sich im \"exports\" Ordner.", "Export", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Beim exportieren ist ein Fehler unterlaufen.", "Export", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        south.add(exportBtn);

        add(Box.createHorizontalStrut(5), BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
    }

    /**
     * Aktiviert den Exportknopf (später evtl. mehr Knöpfe)
     */
    public void enableButtons() {
        if (exportBtn != null) {
            exportBtn.setEnabled(true);
        }
    }

    /**
     * Deaktiviert den Exportknopf (später evtl. mehr Knöpfe)
     */
    public void disableButtons() {
        if (exportBtn != null) {
            exportBtn.setEnabled(false);
        }
    }

    /**
     * @param testResult    Testergebnisse vom Benchmarker
     */
    public void setTestResult(HashMap<Sorter, Integer> testResult) {
        this.testResult = testResult;
    }

    /**
     * initialisiere Panel
     */
    private void initPanel() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    /**
     * Initialisiere Panels von allen zu testenden Sortieralgorithmen
     *
     * @param benchmark     Benchmarker Instanz
     */
    public void initPanels(Benchmark benchmark) {
        panel.removeAll();
        sortResultPanels.clear();

        for (Sorter sorter : benchmarker.getSortPool()) {
            int testsPassed = 0;
            if (testResult != null && testResult.get(sorter) != null) {
                testsPassed = testResult.get(sorter);
            }
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

            JPanel sortResultPanel = new JPanel();

            if (testsPassed == Benchmarker.testsCount) {
                sortResultPanels.put(sorter, sortResultPanel);
            }

            panel.add(Box.createVerticalStrut(10));
            panel.add(new AlgorithmComponent(sorter.getDisplayName(), testsPassed, sortResultPanel));
            panel.add(Box.createVerticalStrut(10));
            panel.add(separator);
        }

        benchmark.initPanels(sortResultPanels);
        revalidate();
        repaint();
    }

    private static class AlgorithmComponent extends JPanel {
        /**
         * @param name              Name des Sortieralgorithmus
         * @param testsPassed       Wie viele Tests bestanden wurden
         * @param sortResultPanel   Das zu dem Algorithmus gehörenden Panel um die Ergebnisse anzuzeigen
         */
        public AlgorithmComponent(String name, int testsPassed, JPanel sortResultPanel) {
            setLayout(new BorderLayout());

            JPanel west = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel east = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JPanel south = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel sorterName = new JLabel(name);
            sorterName.setFont(Settings.fontBold);

            JLabel passedTextLabel = new JLabel("Tests bestanden: ");
            passedTextLabel.setFont(Settings.font);

            JLabel passedLabel = new JLabel(testsPassed + " / " + Benchmarker.testsCount);
            if (testsPassed >= Benchmarker.testsCount) {
                Color DARK_GREEN = new Color(0, 192, 0);
                passedLabel.setForeground(DARK_GREEN);
            } else {
                passedLabel.setForeground(Color.RED);

                sortResultPanel.setLayout(new BorderLayout());

                JLabel errorLabel = new JLabel("Algorithmus ist fehlerhaft!");
                errorLabel.setFont(Settings.font);
                errorLabel.setForeground(Color.red);

                sortResultPanel.add(errorLabel);
            }
            passedLabel.setFont(Settings.font);

            west.add(sorterName);
            east.add(passedTextLabel);
            east.add(passedLabel);
            south.add(sortResultPanel);

            add(east, BorderLayout.EAST);
            add(west, BorderLayout.WEST);
            add(south, BorderLayout.SOUTH);

            setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) getPreferredSize().getHeight()));
        }
    }
}
