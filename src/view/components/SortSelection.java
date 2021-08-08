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
        add(scrollPane, BorderLayout.CENTER);
    }

    private void init(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (int i = 0; i < 20; i++) {
            JLabel test = new JLabel("Testtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
            panel.add(test);
        }

        panel.setVisible(true);
    }
}
