package view.components;

import logic.Benchmarker;
import sort.Sorter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

public class SortSelection extends JPanel {

    private final Benchmarker benchmarker;

    private JPanel selectPanel;
    private JPanel selectedPanel;

    public SortSelection(Benchmarker benchmarker) {
        this.benchmarker = benchmarker;
        init();
    }

    private void init() {

        setLayout(new GridBagLayout());

        selectPanel = new JPanel();
        initSelectPane();
        JScrollPane selectPane = new JScrollPane(selectPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        selectPane.getVerticalScrollBar().setUnitIncrement(8);


        selectedPanel = new JPanel();
        JScrollPane selectedPane = new JScrollPane(selectedPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        selectedPane.getVerticalScrollBar().setUnitIncrement(8);

        // Fix, dass beide Pane's gleich groß sind
        selectPane.setPreferredSize(new Dimension(0, 10000));
        selectedPane.setPreferredSize(new Dimension(0, 10000));

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;

        addSpace(this, 10, c);
        addTitle(this, "Algorithmen hinzufügen", c);
        c.gridy++;
        add(selectPane, c);

        addSpace(this, 5, c);
        addTitle(this, "Hinzugefügte Algorithmen", c);
        c.gridy++;
        add(selectedPane, c);
    }

    private void addSpace(JPanel panel, int height, GridBagConstraints c) {
        double weighty = c.weighty;
        c.gridy++;
        c.weighty = 0;
        panel.add(Box.createRigidArea(new Dimension(0, height)) , c);
        c.weighty = weighty;
    }
    private void addTitle(JPanel panel, String title, GridBagConstraints c) {
        Font font = new Font("Arial", Font.BOLD, 20);
        JLabel titleLabel = new JLabel(" " + title);
        titleLabel.setFont(font);

        double weighty = c.weighty;
        c.gridy++;
        c.weighty = 0;
        panel.add(titleLabel, c);
        c.weighty = weighty;
    }

    private void initSelectPane() {
        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.Y_AXIS));

        for (String alg : benchmarker.getAvailableSorter()) {
            selectPanel.add(new AlgorithmComponent(alg, "+", e -> {
                Sorter sorter = benchmarker.initOne(alg, false);
                if ((e.getModifiers() & InputEvent.SHIFT_MASK) != 0) {
                    benchmarker.addToSortPool(sorter);
                    updateSelectedPanel();
                } else {
                    new SortOptionsDialog(sorter, () -> {
                        benchmarker.addToSortPool(sorter);
                        updateSelectedPanel();
                    }, this);
                }
            }));
        }
    }

    private void updateSelectedPanel() {
        selectedPanel.removeAll();

        selectedPanel.setLayout(new BoxLayout(selectedPanel, BoxLayout.Y_AXIS));

        for (Sorter sorter : benchmarker.getSortPool()) {
            System.out.println("SORTER: " + sorter.getDisplayName());
            selectedPanel.add(new AlgorithmComponent(sorter.getDisplayName(), "-", e -> {
                benchmarker.removeSorter(sorter);
                System.out.println(sorter.getDisplayName());
                updateSelectedPanel();
            }));
        }

        revalidate();
        repaint();
    }

    private static class AlgorithmComponent extends JComponent {
        public AlgorithmComponent(String name, String buttonText, ActionListener listener) {
            setLayout(new FlowLayout(FlowLayout.LEFT));

            Font font = new Font("Arial", Font.PLAIN, 20);

            JButton btn = new JButton(buttonText);
            btn.setOpaque(false);
            btn.setContentAreaFilled(false);
//            btn.setBorderPainted(false);
            btn.setFont(font);

            btn.addActionListener(listener);

            JLabel label = new JLabel(name);
            label.setFont(font);

            add(btn);
            add(label);

            setMaximumSize(new Dimension(10000, (int) getPreferredSize().getHeight()));
        }
    }
}
