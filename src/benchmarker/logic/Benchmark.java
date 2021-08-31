package benchmarker.logic;

import benchmarker.sort.Sorter;
import benchmarker.utils.options.OptionType;
import benchmarker.utils.Settings;
import benchmarker.utils.options.Option;
import benchmarker.utils.options.Options;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public abstract class Benchmark {

    private final Options options;
    private ArrayType arrayType;

    private HashMap<Sorter, JPanel> panels;

    /**
     * Benchmark Klasse von der alle Benchmarks erben.
     */
    public Benchmark() {
        options = new Options();
        arrayType = ArrayType.RANDOM;
    }

    /**
     * @param panels    HashMap von {@link Sorter} mit deren zugehörigen {@link Panel}
     */
    public void initPanels(HashMap<Sorter, JPanel> panels) {
        this.panels = panels;
    }


    /**
     * Diese Methode kann aufgerufen werden, wenn ein Benchmark für einen Algorithmus durchlaufen ist.
     * Das Ergebnis, welches man als Object übergibt, wird in die Methode {@link #updateResult(JPanel, Object)}
     * mit dem dazugehörigen JPanel übergeben.
     * Nun können die Daten in beliebiger Form auf das JPanel hinzugefügt werden.
     *
     * @param sorter    Sorter Objekt, welches geupdated werden soll
     * @param data      Daten die an @see {@link #updateResult(JPanel, Object)} gesendet werden
     */
    protected void updateResult(Sorter sorter, Object data) {
        if (panels != null && panels.get(sorter) != null) {
            JPanel panel = panels.get(sorter);
            panel.removeAll();
            updateResult(panel, data);
            revalidatePanel(panel);
        }
    }

    /**
     * Man kann eine {@link Option} mit einem Namen, {@link OptionType} und Standardwert hinzufügen.
     * Diese Optionen werden in der Oberfläche unter Optionen neben der Benchmark-Auswahl in einem Dialog aufgelistet.
     * Hierbei können die Optionen vom Benutzer editiert werden und im Programm mit {@link #getValue(String)}
     * wieder abgerufen werden.
     * <br>
     * Beispiele für gängige Optionen wären Array-Größe oder Iteration (bzw. Durchläufe).
     *
     * @param option    {@link Option}, welche in der Oberfläche bearbeitet werden kann
     */
    public void addOption(Option option) {
        options.addOption(option);
    }

    /**
     * Mit dieser Methode bekommt man den Wert einer {@link Option} zurück.
     * Dieser Wird jedoch als {@link Object} übergeben und muss ggf. noch zu dem gewünschtem Ergebnis gecastet werden.
     *
     * @param option    Name der Option
     * @return          Standardwert oder vom Benutzer bearbeiteter Wert der Option
     */
    public Object getValue(String option) {
        return options.getOption(option).getValue();
    }

    /**
     * Mit dieser Funktion eine Option via Programmcode bearbeitet werden.
     *
     * @param option    Name der Option
     * @param value     Neuer Wert, der die Option annehmen soll
     */
    public void setValue(String option, Object value) {
        options.setValue(option, value);
    }

    /**
     * @return          Gibt {@link Options}, welches alle Optionen beinhaltet und verwaltet
     */
    public Options getOptions() {
        return options;
    }

    /**
     * Setzt je nach Auswahl des {@link ArrayType} in der Oberfläche Werte in das zu übergebende Array.
     * <br>
     * Bei "RANDOM" werden zufällige Werte in das Array eingetragen.
     * <br>
     * Bei "ASC" wird das Array aufsteigend vorsortiert befüllt.
     * <br>
     * Bei "DESC" wird das Array absteigend vorsortiert befüllt.
     *
     * @param arr       Array, welches befüllt werden soll
     */
    public void getArray(int[] arr) {
        getArray(arr, 0);
    }

    /**
     * Gleich wie bei {@link #getArray(int[])}, nur dass ein seed mit übergeben werden kann, welcher als Basis
     * für die {@link Random} Klasse dient.
     *
     * @param arr        Array, welches befüllt werden soll
     * @param seed       seed, welcher als Basis für die @{@link Random} Klasse dient
     *
     * @see #getArray(int[])
     */
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

    /**
     * @return          In der Oberfläche ausgewählter {@link ArrayType}
     */
    public ArrayType getArrayType() {
        return arrayType;
    }

    /**
     * @param arrayType {@link ArrayType}, welcher für die generierung des Arrays benutzt wird
     */
    public void setArrayType(ArrayType arrayType) {
        this.arrayType = arrayType;
    }

    /**
     * Methode wird vor dem Benchmark aufgerufen und setzt bei allen Panels (von validen Algorithmen) den Text auf
     * "Benchmark läuft...".
     */
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

    /**
     * Methode wird aufgerufen, falls der Benchmark gestoppt wird, oder beendet ist un ersetzt bei allen Panels den
     * Text "Benchmark läuft..." auf "Benchmark wurde abgebrochen.".
     */
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

    /**
     * Skaliert die größe des Panels von einem {@link Sorter}, damit es immer auf den Inhalt passend skaliert wird.
     *
     * @param panel     {@link JPanel} eines {@link Sorter}
     */
    private void revalidatePanel(JPanel panel) {
        if (panel.getParent() != null && panel.getParent().getParent() != null) {
            JComponent parent = (JComponent) panel.getParent().getParent();
            parent.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) parent.getPreferredSize().getHeight()));
            parent.revalidate();
            parent.repaint();
        }
    }

    /**
     * @return          Name der in der Auswahl der Benchmarks angezeigt wird
     */
    public abstract String getName();

    /**
     * wird bei dem ausgewählten Benchmark aufgerufen, wenn der Benchmark startet.
     *
     * @param sortPool  Liste an Objekten aller hinzugefügten {@link Sorter}
     */
    public abstract void benchmark(List<Sorter> sortPool);

    /**
     * wird mit {@link #updateResult(Sorter, Object)} aufgerufen und gibt das zu dem {@link Sorter} gehörende
     * Panel mit. In der Funktion können beliebig die Ergebnisse des Benchmarks auf dem {@link JPanel} hinzugefügt werden.
     *
     * @param panel     Panel eines {@link Sorter}
     * @param data      Daten, welche mit {@link #updateResult(Sorter, Object)} mitgesendet wurden
     */
    protected abstract void updateResult(JPanel panel, Object data);

    public enum ArrayType {
        RANDOM, ASC, DESC;
    }
}
