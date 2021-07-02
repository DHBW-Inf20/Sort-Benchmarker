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

    private Box boxName = Box.createHorizontalBox();
    private Box boxThreads = Box.createHorizontalBox();

    private JButton btnDone;

    private JLabel name;
    private JLabel threads;

    private JPanel contents;
    private JPanel panelCenter;
    private JPanel panelSouth;

    private JTextField algName;


    public AlgorithmOptions() {
        setTitle("hier name des Algorithmus " + "custom options");

        //content region
        contents = new JPanel();
        contents.setBorder(borderContents);
        contents.setLayout(new BoxLayout(contents,BoxLayout.Y_AXIS));
        setContentPane(contents);

        panelCenter = new JPanel();
        panelCenter.setBorder(borderCenter);

        name = new JLabel("Name:");

        algName = new JTextField(20);
        algName.setText("Hier den Namen als Vorschlag rein");

        threads = new JLabel("Threads:");

        panelCenter.add(name);
        panelCenter.add(algName);

      //panelCenter.add(Box.createRigidArea(new Dimension(10,1)));

        panelCenter.add(threads);
        contents.add(panelCenter);
        panelCenter.add(Box.createRigidArea(new Dimension(10,1)));

        //bottom region
        panelSouth = new JPanel();
        panelSouth.setBorder(borderCenter);
        btnDone= new JButton("Done");
        panelSouth.add(btnDone);
        contents.add(panelSouth, BorderLayout.SOUTH);

        //add event handlers
        btnDone.addActionListener(this);

        //size and display window
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
            Object source = e.getSource();
            if (source == btnDone) {
                //fensterschließen
                return;
            }
        }

        @Override
        public void itemStateChanged (ItemEvent e){

        }

        @Override
        public void valueChanged (ListSelectionEvent e){


    }
}
