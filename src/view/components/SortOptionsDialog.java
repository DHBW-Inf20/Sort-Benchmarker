package view.components;

import utils.layout.SpringUtilities;
import sort.Sorter;
import utils.options.Option;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class SortOptionsDialog extends JFrame {

    public SortOptionsDialog(Sorter sorter, Runnable addRunnable, JComponent owner) {
        setLocationRelativeTo(owner);
        setTitle(sorter.getName() + " hinzufügen");
        setAlwaysOnTop(true);
        setResizable(false);
        setAlwaysOnTop(true);

        List<Option> options = sorter.getSortOptions().getOptionList();

        init(options, addRunnable);
    }

    private void init(List<Option> options, Runnable addRunnable) {
        JPanel p = new JPanel(new SpringLayout());

        int dynamicHeight = 40;

        HashMap<String, JComponent> inputs = new HashMap<>();

        for (Option option : options) {
            JLabel l = new JLabel(option.getOption() + ":", JLabel.TRAILING);
            p.add(l);
            switch (option.getOptionType()) {
                case STRING:
                    dynamicHeight += 35;
                    JTextField textField = new JTextField((String) option.getValue());
                    l.setLabelFor(textField);
                    p.add(textField);
                    inputs.put(option.getOption(), textField);
                    break;
                case NUMBER:
                    dynamicHeight += 35;
                    SpinnerModel spinnerModel = new SpinnerNumberModel(((Integer) option.getValue()).intValue(), Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
                    JSpinner spinner = new JSpinner(spinnerModel);
                    l.setLabelFor(spinner);
                    p.add(spinner);
                    inputs.put(option.getOption(), spinner);
                    break;
                case BOOL:
                    dynamicHeight += 25;
                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setSelected((Boolean) option.getValue());
                    p.add(checkBox);
                    inputs.put(option.getOption(), checkBox);
                    break;
            }
        }

        p.add(new JLabel(""));

        JButton addBtn = new JButton("Hinzufügen");
        addBtn.addActionListener(e -> {
            for (Option option : options) {
                switch (option.getOptionType()) {
                    case STRING:
                        String strValue = ((JTextField) inputs.get(option.getOption())).getText();
                        option.setValue(strValue);
                        break;
                    case NUMBER:
                        int intValue = (Integer) ((JSpinner) inputs.get(option.getOption())).getValue();
                        option.setValue(intValue);
                        break;
                    case BOOL:
                        boolean boolValue = ((JCheckBox) inputs.get(option.getOption())).isSelected();
                        option.setValue(boolValue);
                        break;
                }
            }
            addRunnable.run();
            dispose();
        });
        p.add(addBtn);

        SpringUtilities.makeCompactGrid(p,
                options.size() + 1, 2,        //rows, cols
                6, 6,                          //initX, initY
                15, 6);                           //xPad, yPad

        add(p);

        setVisible(true);
        dynamicHeight += getInsets().top + getInsets().bottom;
        setSize(500, dynamicHeight);
    }
}