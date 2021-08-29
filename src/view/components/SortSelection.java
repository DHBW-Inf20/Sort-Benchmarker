package view.components;

import logic.Benchmarker;
import sort.Sorter;
import utils.Settings;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.geom.RoundRectangle2D;

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
        addSpace(this, 10, c);
        c.gridy++;
        add(selectPane, c);

        addSpace(this, 10, c);
        addTitle(this, "Hinzugefügte Algorithmen", c);
        addSpace(this, 10, c);
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
        JLabel titleLabel = new JLabel(" " + title);
        titleLabel.setFont(Settings.fontBold);

        int fill = c.fill;
        double weighty = c.weighty;
        c.fill = GridBagConstraints.CENTER;
        c.gridy++;
        c.weighty = 0;
        panel.add(titleLabel, c);
        c.weighty = weighty;
        c.fill = fill;
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
                    new OptionsDialog(sorter.getName() + " hinzufügen", "Hinzufügen", sorter.getOptions(), () -> {
                        benchmarker.addToSortPool(sorter);
                        updateSelectedPanel();
                    });
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

    private static class AlgorithmComponent extends JPanel {
        public AlgorithmComponent(String name, String buttonText, ActionListener listener) {
            setLayout(new FlowLayout(FlowLayout.LEFT));

            JButton btn = new JButton(buttonText);
            btn.setOpaque(false);
            btn.setContentAreaFilled(false);
//            btn.setBorderPainted(false);
            btn.setFont(Settings.font);

            btn.addActionListener(listener);

            JLabel label = new JLabel(name);
            label.setFont(Settings.font);

            add(btn);
            add(label);

            setMaximumSize(new Dimension(10000, (int) getPreferredSize().getHeight()));
        }
    }
}
