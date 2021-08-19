package view.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortSelection extends JPanel {

    public SortSelection() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        init(panel);
        add(panel, BorderLayout.CENTER);
    }

    private void init(JPanel panel) {

        panel.setLayout(new GridBagLayout());

        JPanel selectPanel = new JPanel();
        initSelectPane(selectPanel);
        JScrollPane selectPane = new JScrollPane(selectPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        selectPane.getVerticalScrollBar().setUnitIncrement(8);

        JPanel selectedPanel = new JPanel();
        initSelectedPane(selectedPanel);
        JScrollPane selectedPane = new JScrollPane(selectedPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        selectedPane.getVerticalScrollBar().setUnitIncrement(8);

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;

        addSpace(panel, 10, c);
        addTitle(panel, "Algorithmen hinzufügen", c);
        c.gridy++;
        panel.add(selectPane, c);

        addSpace(panel, 5, c);
        addTitle(panel, "Hinzugefügte Algorithmen", c);
        c.gridy++;
        panel.add(selectedPane, c);
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

    private void initSelectPane(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (int i = 0; i < 5; i++) {
            panel.add(new AlgorithmComponent("Algorithmus " + i, true));
        }
    }

    private void initSelectedPane(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (int i = 0; i < 3; i++) {
            panel.add(new AlgorithmComponent("Algorithmus " + i, false));
        }
    }

    private static class AlgorithmComponent extends JPanel {
        public AlgorithmComponent(String name, boolean add_remove) {
            setLayout(new FlowLayout(FlowLayout.LEFT));

            Font font = new Font("Arial", Font.PLAIN, 20);

            JButton btn = new JButton(add_remove ? "+" : "-");
            btn.setOpaque(false);
            btn.setContentAreaFilled(false);
//            btn.setBorderPainted(false);
            btn.setFont(font);

            btn.addActionListener(e -> {
                System.out.println(name);
            });

            JLabel label = new JLabel(name);
            label.setFont(font);

            add(btn);
            add(label);

            setMaximumSize(new Dimension(10000, (int) getPreferredSize().getHeight()));
        }
    }
}
