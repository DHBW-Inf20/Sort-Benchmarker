package logic;

import sort.Sorter;
import utils.Settings;
import utils.options.Option;
import utils.options.Options;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public abstract class Benchmark {

    private final Options options;
    private ArrayType arrayType;

    private HashMap<Sorter, JPanel> panels;

    public Benchmark() {
        options = new Options();
        arrayType = ArrayType.RANDOM;
    }

    public void initPanels(HashMap<Sorter, JPanel> panels) {
        this.panels = panels;
    }

    protected void updateResult(Sorter sorter, Object data) {
        if (panels != null && panels.get(sorter) != null) {
            JPanel panel = panels.get(sorter);
            panel.removeAll();
            updateResult(panel, data);
            revalidatePanel(panel);
        }
    }

    public void addOption(Option option) {
        options.addOption(option);
    }

    public Object getValue(String option) {
        return options.getOption(option).getValue();
    }

    public void setValue(String option, Object value) {
        options.setValue(option, value);
    }

    public Options getOptions() {
        return options;
    }

    public void getArray(int[] arr) {
        getArray(arr, 0);
    }

    public void getArray(int[] arr, long seed) {
        Random random;
        if (seed != 0) {
            random = new Random(seed);
        } else {
            random = new Random();
        }
        switch (arrayType) {
            case RANDOM:
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = random.nextInt();
                }
                break;
            case ASC:
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = i;
                }
                break;
            case DESC:
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = arr.length - i - 1;
                }
                break;
        }
    }

    public ArrayType getArrayType() {
        return arrayType;
    }

    public void setArrayType(ArrayType arrayType) {
        this.arrayType = arrayType;
    }

    public abstract String getName();

    public void beforeBenchmark() {
        if (panels != null) {
            for (JPanel panel : panels.values()) {
                panel.setLayout(new BorderLayout());
                JLabel runningLabel = new JLabel("Benchmark läuft...");
                runningLabel.setFont(Settings.font);
                panel.removeAll();
                panel.add(runningLabel);
                revalidatePanel(panel);
            }
        }
    }

    public void benchmarkStopped() {
        if (panels != null) {
            for (JPanel panel : panels.values()) {
                Component[] components = panel.getComponents();
                if (components.length == 1) {
                    if (components[0] instanceof JLabel) {
                        if (((JLabel) components[0]).getText().equals("Benchmark läuft...")) {
                            panel.setLayout(new BorderLayout());
                            JLabel runningLabel = new JLabel("Benchmark wurde abgebrochen.");
                            runningLabel.setFont(Settings.font);
                            panel.removeAll();
                            panel.add(runningLabel);
                            revalidatePanel(panel);
                        }
                    }
                }
            }
        }
    }

    private void revalidatePanel(JPanel panel) {
        if (panel.getParent() != null && panel.getParent().getParent() != null) {
            JComponent parent = (JComponent) panel.getParent().getParent();
            parent.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) parent.getPreferredSize().getHeight()));
            parent.revalidate();
            parent.repaint();
        }
    }

    public abstract void benchmark(List<Sorter> sortPool);

    protected abstract void updateResult(JPanel panel, Object data);

    public enum ArrayType {
        RANDOM, ASC, DESC;
    }
}
