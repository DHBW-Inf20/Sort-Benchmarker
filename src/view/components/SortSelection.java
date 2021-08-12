package view.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SortSelection extends JPanel {

    public SortSelection() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        init(panel);
        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void init(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (int i = 0; i < 5; i++) {
            panel.add(getAlgorithmComponent("Algorithmus " + i));
        }
        panel.setVisible(true);
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
