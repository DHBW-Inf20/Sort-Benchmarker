package view.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SortSelection extends JPanel {

    public SortSelection() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        init(panel);
        add(panel, BorderLayout.CENTER);
    }

    private void init(JPanel panel) {

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;

        JPanel selectPanel = new JPanel();
        initSelectPane(selectPanel);
        JScrollPane selectPane = new JScrollPane(selectPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        selectPane.getVerticalScrollBar().setUnitIncrement(8);

        JPanel selectedPanel = new JPanel();
        // TODO: initSelectedPane
        JScrollPane selectedPane = new JScrollPane(selectedPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        selectedPane.getVerticalScrollBar().setUnitIncrement(8);

        panel.add(selectPane, c);
        c.gridy = 1;
        panel.add(selectedPane, c);
    }

    private void initSelectPane(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (int i = 0; i < 5; i++) {
            panel.add(getAlgorithmComponent("Algorithmus " + i));
        }
    }

    private JComponent getAlgorithmComponent(String name) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        panel.setBackground(Color.BLUE);

        Font font = new Font("Arial", Font.BOLD, 20);

        JButton btn = new JButton("+");
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
//        btn.setBorderPainted(false);
        btn.setFont(font);

        JLabel label = new JLabel(name);
        label.setFont(font);
//        label.setBackground(Color.RED);
//        label.setOpaque(true);
//
        panel.add(btn);
        panel.add(label);

        panel.setMaximumSize(new Dimension(10000, (int) panel.getPreferredSize().getHeight()));

        return panel;
    }
}
