package sort;

import utils.options.OptionType;
import utils.options.Option;
import utils.options.Options;

public abstract class Sorter {

    private final Options options;
    private boolean testsPassed;

    /**
     * Sorter Klasse von dem alle Sortieralgorithmen erben.
     */
    public Sorter() {
        this.options = new Options();
        testsPassed = false;
        addOption(new Option("Name", OptionType.STRING, getName()));
    }

    /**
     * Man kann eine {@link Option} mit einem Namen, {@link utils.options.OptionType} und Standardwert hinzufügen.
     * Diese Optionen werden bei dem Hinzufügen eines Algorithmus in einem Dialog aufgelistet.
     * Hierbei können die Optionen vom Benutzer editiert werden und im Programm mit {@link #getValue(String)}
     * wieder abgerufen werden.
     * <br>
     * Ein Beispiel für eine gängige Optionen wäre die Thread-Anzahl bei einem multithreaded Algorithmus.
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
     * @param testsPassed {@link Boolean}, ob alle Tests bestanden wurden
     */
    public void setTestsPassed(boolean testsPassed) {
        this.testsPassed = testsPassed;
    }

    /**
     * @return      Gibt zurück, ob der Algorithmus alle Tests bestanden hat
     */
    public boolean passedTests() {
        return testsPassed;
    }

    /**
     * Gibt den Namen des Algorithmus der bei den hinzugefügten Algorithmen und in der Auswertung eines Benchmarks
     * angezeigt wird.
     *
     * @return      Anzeigename eines {@link Sorter}
     */
    public String getDisplayName() {
        return (String) getValue("Name");
    }

    /**
     * Optionale Funktion, welche ausgeführt wird, wenn ein Algorithmus hinzugefügt wurde.
     */
    public void initSorter() {}

    /**
     * @return      Standardname, welcher beim Hinzufügen eines Algorithmus bearbeitet werden kann.
     */
    public abstract String getName();


    /**
     * In dieser Funktion wird ein zu sortierendes Array übergeben, welches anschließend von dem jeweiligen Algorithmus
     * sortiert werden soll und wieder zurückgegeben werden soll.
     * Auf der Dauer zwischen Ausführen der Methode und der Rückgabe des sortierten Arrays basieren die Benchmarks.
     *
     * @param toSort    Zu sortierendes Array
     * @return          sortiertes Array
     */
    public abstract int[] sort(int[] toSort);
}
