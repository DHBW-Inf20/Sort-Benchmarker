package view;

import view.components.SortSelection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GUI extends JFrame {

    public GUI() {
        super("Sort-Benchmarker");
        this.initFrame();
        this.setVisible(true);
    }

    private void initFrame() {
        this.setSize(800, 800);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        this.initPanel(panel);
        this.add(panel);
    }

    private void initPanel(JPanel panel) {
        panel.setLayout(new BorderLayout());

//        JLabel test = new JLabel("Testttttttttttttttttttttttttttttttttttttttttttttttttttt");
//        test.setSize(100, 100);
//        test.setLocation(50, 50);
//        panel.add(test);

        SortSelection sortSelection = new SortSelection();
//        sortSelection.setBackground(Color.BLUE);
        // Set width for sort selection panel
        sortSelection.setPreferredSize(new Dimension(400, 0));
        panel.add(sortSelection, BorderLayout.WEST);

        panel.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
