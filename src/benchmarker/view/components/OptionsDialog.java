package benchmarker.view.components;

import benchmarker.utils.layout.SpringUtilities;
import benchmarker.utils.options.Option;
import benchmarker.utils.options.Options;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class OptionsDialog extends JFrame {

    /**
     * Öffnet ein Dialogfenster wo alle Optionen dynamisch angezeigt werden und editierbar sind.
     *
     * @param title         Titel des Dialog-Fensters
     * @param buttonText    Text des Knopfes im Dialog-Fenster
     * @param options       Options Instanz, wo alle zu editierenden Optionen gespeichert sind
     * @param addRunnable   Runnable was nach dem Drücken des Knopfes ausgeführt wird
     */
    public OptionsDialog(String title, String buttonText, Options options, Runnable addRunnable) {
        setLocation(MouseInfo.getPointerInfo().getLocation());
        setTitle(title);
        setAlwaysOnTop(true);
        setResizable(false);
        setAlwaysOnTop(true);

        List<Option> optionList = options.getOptionList();

        init(optionList, addRunnable, buttonText);
    }

    /**
     * @param options       Options Instanz, wo alle zu editierenden Optionen gespeichert sind
     * @param addRunnable   Runnable was nach dem Drücken des Knopfes ausgeführt wird
     * @param buttonText    Text des Knopfes im Dialog-Fenster
     */
    private void init(List<Option> options, Runnable addRunnable, String buttonText) {
        JPanel p = new JPanel(new SpringLayout());

        int dynamicHeight = 40; // Größe für Knopf (wurde experimentell bestimmt)

        HashMap<String, JComponent> inputs = new HashMap<>();

        for (Option option : options) {
            JLabel l = new JLabel(option.getOption() + ":", JLabel.TRAILING);
            p.add(l);
            // Je nach Option wird ein anderer Component dem Panel hinzugefügt
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
                    SpinnerNumberModel spinnerModel = new SpinnerNumberModel(((Integer) option.getValue()).intValue(), option.getMin(), option.getMax(), option.getStep());
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

        JButton addBtn = new JButton(buttonText);
        addBtn.addActionListener(e -> {
            for (Option option : options) {
                // Je nach Option werden die Daten anders aus dem Component ausgelesen
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
                options.size() + 1, 2, // Reihen, Spalten
                6, 6, // Versatz X, Versatz Y
                15, 6); // Differenz X, Differenz Y

        add(p);

        // Bevor getInsets() valide Werte liefert muss erst visisble auf true gesetzt werden
        setVisible(true);
        dynamicHeight += getInsets().top + getInsets().bottom;
        setSize(400, dynamicHeight);
    }
}