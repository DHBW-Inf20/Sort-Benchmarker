package utils.options;

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

    public int getSize() {
        return options.size();
    }

    public ArrayList<Option> getOptionList() {
        return optionsList;
    }

    public void addOption(Option option) {
        options.put(option.getOption(), option);
        optionsList.add(option);
    }

    public Option getOption(String option) {
        return options.get(option);
    }

    public void setValue(String option, Object value) {
        options.get(option).setValue(value);
    }
}
