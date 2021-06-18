package Surface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GUI extends JFrame implements ActionListener, ItemListener, ListSelectionListener {
    private JLabel label;

    //Borders:
    private Border borderContents =
            BorderFactory.createEmptyBorder(0,0,10,0);
    private Border borderCenter =
            BorderFactory.createEmptyBorder(10, 10,10,10);
    private Border borderList =
            BorderFactory.createLineBorder(Color.BLUE,1);

    // Containers:
    private Box boxButtons = Box.createVerticalBox();
    private Box boxListNotUsed = Box.createVerticalBox();
    private Box boxListUsed = Box.createVerticalBox();
    private JPanel panelCenter;
    private JPanel panelSouth;
    private JPanel contents;

    //Components:
    private JButton btnAdd;
    private JButton btnAddAll;
    private JButton btnRemove;
    private JButton btnRemoveAll;

    private JLabel labelListNotUsed;
    private JLabel labelListUsed;
    private JLabel labelSelectedNotUsed;
    private JLabel labelSelectedNotUsedLabel;
    private JLabel labelSelectedUsed;
    private JLabel labelSelectedUsedLabel;


    private JList<String> listNotUsed;
    private JList<String> listUsed;

    //Fonts?


    private String [] algorithms =
            {"d","d2","d3"};

    // List models:
    private DefaultListModel<String> listModelNotUsed = new DefaultListModel<>();
    private DefaultListModel<String> listModelUsed = new DefaultListModel<>();



    public GUI(){

        //setFonts(); not done yet

        JButton startButton = new JButton("Start");
        startButton.addActionListener(this);

        contents = new JPanel();
        contents.setBorder(borderContents);
        contents.setLayout(new BorderLayout());
        setContentPane(contents);


        //North region:
        setTitle("Sort algorithms comparison");


        //Center region:
        panelCenter = new JPanel();
        panelCenter.setBorder(borderCenter);


        // NotUsed List:
        JLabel labelListNotUsed = new JLabel("Not Used:");
        labelListNotUsed.setAlignmentX(LEFT_ALIGNMENT);

        initNotUsedModel();
        listNotUsed = new JList<>(listModelNotUsed);
        listNotUsed.setAlignmentX(LEFT_ALIGNMENT);
        listNotUsed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listNotUsed.setBorder(borderList);

        JScrollPane scrollListNotUsed = new JScrollPane(listNotUsed);
        scrollListNotUsed.setAlignmentX(LEFT_ALIGNMENT);
        scrollListNotUsed.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setSpecificSize(scrollListNotUsed, new Dimension(200,300));

        boxListNotUsed.add(labelListNotUsed);
        boxListNotUsed.add(scrollListNotUsed);
        panelCenter.add(boxListNotUsed);

        //  Spacer:
        panelCenter.add(Box.createRigidArea(new Dimension(10,1)));

        //  Add/Remove buttons:
        btnAdd = new JButton("Add >");
        btnAddAll = new JButton("Add All >>");
        btnRemove = new JButton("< Remove");
        btnRemoveAll = new JButton("<< Remove All");

        Dimension dimensionRemoveAll = btnRemoveAll.getPreferredSize();
        setSpecificSize(btnAdd, dimensionRemoveAll);
        setSpecificSize(btnAddAll, dimensionRemoveAll);
        setSpecificSize(btnRemove, dimensionRemoveAll);

        boxButtons.add(btnAdd);
        boxButtons.add(Box.createRigidArea(new Dimension(1,5)));
        boxButtons.add(btnAddAll);
        boxButtons.add(Box.createRigidArea(new Dimension(1,20)));
        boxButtons.add(btnRemove);
        boxButtons.add(Box.createRigidArea(new Dimension(1,5)));
        boxButtons.add(btnRemoveAll);
        boxButtons.add(Box.createRigidArea(new Dimension(1,20)));
        panelCenter.add(boxButtons);

        //  Spacer:
        panelCenter.add(Box.createRigidArea(new Dimension(10,1)));

        //  Used List
        labelListUsed = new JLabel("Used:");
        labelListUsed.setAlignmentX(LEFT_ALIGNMENT);

        listUsed = new JList<>(listModelUsed);
        listUsed.setAlignmentX(LEFT_ALIGNMENT);
        listUsed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listUsed.setBorder(borderList);

        JScrollPane scrollListUsed = new JScrollPane(listUsed);
        scrollListUsed.setAlignmentX(LEFT_ALIGNMENT);
        scrollListUsed.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setSpecificSize(scrollListUsed,new Dimension(200,300));

        boxListUsed.add(labelListUsed);
        boxListUsed.add(scrollListUsed);
        panelCenter.add(boxListUsed);

        contents.add(panelCenter, BorderLayout.CENTER);
        // Spacer:
        panelCenter.add(Box.createRigidArea(new Dimension(10,1)));

        //  South region:
        panelSouth = new JPanel();
        labelSelectedNotUsedLabel = new JLabel("Selected not used:");
        labelSelectedNotUsed = new JLabel();
        labelSelectedUsedLabel = new JLabel("Selected  used:");
        labelSelectedUsed = new JLabel();

        panelSouth.add(labelSelectedNotUsedLabel);
        panelSouth.add(labelSelectedNotUsed);
        panelSouth.add(Box.createRigidArea(new Dimension(100,1)));
        panelSouth.add(labelSelectedUsedLabel);
        panelSouth.add(labelSelectedUsed);
        contents.add(panelSouth, BorderLayout.SOUTH);

        // Register event handlers:
        btnAdd.addActionListener(this);
        btnAddAll.addActionListener(this);
        btnRemove.addActionListener(this);
        btnRemoveAll.addActionListener(this);
        listNotUsed.addListSelectionListener(this);
        listUsed.addListSelectionListener(this);

        // Size and display window:
        setSize(850, 460);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void setSpecificSize(JComponent component, Dimension dimension) {
        component.setMinimumSize(dimension);
        component.setPreferredSize(dimension);
        component.setMaximumSize(dimension);
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
   Object source = e.getSource();
   if (source == btnAdd) {
       addItem();
       return;
   }
   if (source == btnAddAll){
       addAllItems();
       return;
   }
   if (source == btnRemove) {
       removeItem();
       return;
   }
   if(source == btnRemoveAll){
       removeAllItems();
       return;
   }
    }
    private void addItem() {
        int iSelected = listNotUsed.getSelectedIndex();
        if (iSelected == -1) {
            return;
        }
        String addedItem = listNotUsed.getSelectedValue();
        //remove from left list:
        listModelNotUsed.remove(iSelected);
        displaySelectedItems();

        //Add to right list:
        int size = listModelUsed.getSize();
        if (size == 0) { // Empty list
            listModelUsed.addElement(addedItem);
            return;
        }
        // find a larger item:
        for (int i = 0; i < size; i++) {
            String item = listModelUsed.elementAt(i);
            int compare = addedItem.compareToIgnoreCase(item);
            if (compare < 0) {//added < item
                listModelUsed.add(i, addedItem);
                return;
            }
        }
            listModelUsed.addElement(addedItem);
        }

        private void addAllItems(){
        listModelUsed.clear();
        for (String s : algorithms){
            listModelUsed.addElement(s);
        }
        listModelNotUsed.clear();
        }

        private void removeItem() {
            int iSelected = listUsed.getSelectedIndex();
            if (iSelected == -1) {
                return;
            }
            String removedItem = listUsed.getSelectedValue();

            // Remove from right list:
            listModelUsed.remove(iSelected);
            displaySelectedItems();
            // Add to left list:
            int size = listModelNotUsed.getSize();
            if (size == 0) {// Empty list
                listModelNotUsed.addElement(removedItem);
                return;
            }
            // Find a larger Item:
            for (int i = 0; i < size; i++) {
                String item = listModelNotUsed.elementAt(i);
                int compare = removedItem.compareToIgnoreCase(item);
                if (compare < 0) {// removed < item
                    listModelNotUsed.add(i, removedItem);
                    return;
                }
            }
        listModelNotUsed.addElement(removedItem);
        }

        private  void removeAllItems(){
        listModelNotUsed.clear();
        initNotUsedModel();
        listModelUsed.clear();
        displaySelectedItems();
        }

        private void displaySelectedItems(){
        int iSelected;
        String itemName;

        iSelected = listNotUsed.getSelectedIndex();
        if (iSelected == -1) {
            labelSelectedNotUsed.setText("");
        }
        else {
            itemName = listNotUsed.getSelectedValue();
            labelSelectedNotUsed.setText(itemName);
        }

        iSelected = listUsed.getSelectedIndex();
        if (iSelected == -1){
            labelSelectedUsed.setText("");
        } else {
            itemName = listUsed.getSelectedValue();
            labelSelectedUsed.setText(itemName);
        }
    }
    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
    public void initNotUsedModel(){
        for (String s : algorithms){
            listModelNotUsed.addElement(s);
        }
    }
}
