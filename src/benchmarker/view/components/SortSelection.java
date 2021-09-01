package benchmarker.view.components;

import benchmarker.logic.Benchmarker;
import benchmarker.sort.Sorter;
import benchmarker.utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

public class SortSelection extends JPanel {

    private final Benchmarker benchmarker;

    private JPanel selectPanel;
    private JPanel selectedPanel;

    /**
     * In SortSelection wird die Auswahl der Sortieralgorithmen angezeigt
     *
     * @param benchmarker   Benchmarker Instanz
     */
    public SortSelection(Benchmarker benchmarker) {
        this.benchmarker = benchmarker;
        init();
    }

    /**
     * Initialisiere SortSelection mit SelectPane und SelectedPane
     */
    private void init() {

        setLayout(new GridBagLayout());

        selectPanel = new JPanel();
        initSelectPanel();
        JScrollPane selectPane = new JScrollPane(selectPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        selectPane.getVerticalScrollBar().setUnitIncrement(8);


        selectedPanel = new JPanel();
        initSelectedPanel();
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

    /**
     * @param panel     Panel, dem ein Abstand hinzugefügt werden soll
     * @param height    Höhe/Größe des Abstandes
     * @param c         GridBagConstraints
     */
    private void addSpace(JPanel panel, int height, GridBagConstraints c) {
        double weighty = c.weighty;
        c.gridy++;
        c.weighty = 0;
        panel.add(Box.createVerticalStrut(height) , c);
        c.weighty = weighty;
    }


    /**
     * @param panel     Panel, dem ein Titel hinzugefügt werden soll
     * @param title     Titel, der hinzugefügt werden soll
     * @param c         GridBagConstraints
     */
    private void addTitle(JPanel panel, String title, GridBagConstraints c) {
        JLabel titleLabel = new JLabel(title);
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

    /**
     * Initialisiere SelectedPane mit allen verfügbaren Sortieralgorithmen
     */
    private void initSelectPanel() {
        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.Y_AXIS));

        for (String alg : benchmarker.getAvailableSorter()) {

            AlgorithmComponent algComponent = new AlgorithmComponent(alg, "+", e -> {
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
            });

            selectPanel.add(algComponent);
        }
    }

    /**
     * Initialisiere SelectedPanel
     */
    private void initSelectedPanel() {
        selectedPanel.setLayout(new BoxLayout(selectedPanel, BoxLayout.Y_AXIS));
    }

    /**
     * Update SeletectedPanel um die ausgewählten Algorithmen anzuzeigen
     */
    private void updateSelectedPanel() {
        selectedPanel.removeAll();

        for (Sorter sorter : benchmarker.getSortPool()) {
            AlgorithmComponent algComponent = new AlgorithmComponent(sorter.getDisplayName(), "-", e -> {
                benchmarker.removeSorter(sorter);
                updateSelectedPanel();
            });

            selectedPanel.add(algComponent);
        }

        revalidate();
        repaint();
    }

    /**
     * Aktiviert alle Knöpfe in der Sortier-Auswahl
     */
    public void enableButtons() {
        for (Component component : selectPanel.getComponents()) {
            if (component instanceof  AlgorithmComponent) {
                ((AlgorithmComponent) component).enableButton();
            }
        }
        for (Component component : selectedPanel.getComponents()) {
            if (component instanceof  AlgorithmComponent) {
                ((AlgorithmComponent) component).enableButton();
            }
        }
    }

    /**
     * Deaktiviert alle Knöpfe in der Sortier-Auswahl
     */
    public void disableButtons() {
        for (Component component : selectPanel.getComponents()) {
            if (component instanceof  AlgorithmComponent) {
                ((AlgorithmComponent) component).disableButton();
            }
        }
        for (Component component : selectedPanel.getComponents()) {
            if (component instanceof AlgorithmComponent) {
                ((AlgorithmComponent) component).disableButton();
            }
        }
    }

    private static class AlgorithmComponent extends JPanel {

        private final JButton btn;

        /**
         * @param name          Name des Algorithmus
         * @param buttonText    Text der auf dem Knopf angezeigt wird ("+"/"-")
         * @param listener      ActionListener von dem Knopf
         */
        public AlgorithmComponent(String name, String buttonText, ActionListener listener) {
            setLayout(new FlowLayout(FlowLayout.LEFT));

            btn = new JButton(buttonText);
            btn.setOpaque(false);
            btn.setContentAreaFilled(false);
//            btn.setBorderPainted(false);
            btn.setFont(Settings.font);

            btn.addActionListener(listener);

            JLabel label = new JLabel(name);
            label.setFont(Settings.font);

            add(btn);
            add(label);

            setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) getPreferredSize().getHeight()));
        }

        /**
         * Aktiviert Knopf
         */
        public void enableButton() {
            btn.setEnabled(true);
        }

        /**
         * Deaktiviert Knopf
         */
        public void disableButton() {
            btn.setEnabled(false);
        }
    }
}
