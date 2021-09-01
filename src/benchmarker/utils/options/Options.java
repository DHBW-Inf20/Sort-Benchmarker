package benchmarker.utils.options;

import java.util.ArrayList;
import java.util.HashMap;

public class Options {

    private final HashMap<String, Option> options;

    // Damit die Reihenfolge bei dem Options Dialog eingehalten werden kann
    private final ArrayList<Option> optionsList;

    public Options() {
        options = new HashMap<>();
        optionsList = new ArrayList<>();
    }

    /**
     * @return          Anzahl der verfügbaren Optionen
     */
    public int getSize() {
        return options.size();
    }

    /**
     * @return          Liste aller verfügbaren Optionen
     */
    public ArrayList<Option> getOptionList() {
        return optionsList;
    }

    /**
     * @param option    Option, welche hinzugefügt werden soll
     */
    public void addOption(Option option) {
        options.put(option.getOption(), option);
        optionsList.add(option);
    }

    /**
     * @param option    Name der Option
     * @return          Option
     */
    public Option getOption(String option) {
        return options.get(option);
    }

    /**
     * @param option    Name der Option
     * @param value     aktueller Wert der Option
     */
    public void setValue(String option, Object value) {
        options.get(option).setValue(value);
    }
}
