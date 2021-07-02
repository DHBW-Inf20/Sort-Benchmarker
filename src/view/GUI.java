package view;

import view.components.SortSelection;

import javax.swing.*;

public class GUI extends JFrame {

    public GUI() {
        super("Test GUI");
        this.initFrame();
        this.setVisible(true);
    }

    private void initFrame() {
        this.setSize(500, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        this.initPanel(panel);
        this.add(panel);
    }

    private void initPanel(JPanel panel) {
        panel.setLayout(null);

        JLabel test = new JLabel("Testttttttttttttttttttttttttttttttttttttttttttttttttttt");
        test.setSize(100, 100);
        test.setLocation(50, 50);
        test.setVisible(true);
        panel.add(test);

        panel.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
