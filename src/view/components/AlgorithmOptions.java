package view.components;

import Surface.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AlgorithmOptions  extends JFrame implements ActionListener, ItemListener, ListSelectionListener {

    private Border borderContents =
            BorderFactory.createEmptyBorder(0,0,10,0);
    private Border borderCenter =
            BorderFactory.createEmptyBorder(10, 10,10,10);

    private JPanel contents;
    private JPanel panelCenter;

    private JTextField algName;

    private JLabel name;

    public AlgorithmOptions() {
        setTitle("hier name des Algorithmus " + "custom options");

        contents = new JPanel();
        contents.setBorder(borderContents);
        contents.setLayout(new BorderLayout());
        setContentPane(contents);

        panelCenter = new JPanel();
        panelCenter.setBorder(borderCenter);

        name = new JLabel("Name:");

        algName = new JTextField(20);
        algName.setText("Hier den Namen als Vorschlag rein");
        algName.addActionListener(this); //hier bin ich

        panelCenter.add(name);
        panelCenter.add(algName);

        contents.add(panelCenter);
        panelCenter.add(Box.createRigidArea(new Dimension(10,1)));


        setSize(850, 460);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        AlgorithmOptions algorithmOptions = new AlgorithmOptions();
        algorithmOptions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

        @Override
        public void actionPerformed (ActionEvent e){

        }

        @Override
        public void itemStateChanged (ItemEvent e){

        }

        @Override
        public void valueChanged (ListSelectionEvent e){


    }
}
