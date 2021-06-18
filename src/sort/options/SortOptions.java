package sort.options;

import java.util.HashMap;

public class SortOptions {

    private final HashMap<String, SortOption> sortOptions;

    public SortOptions() {
        sortOptions = new HashMap<>();
    }

    public int getSize() {
        return sortOptions.size();
    }

    public SortOption[] getOptions() {
        return sortOptions.values().toArray(new SortOption[sortOptions.size()]);
    }

    public void addOption(SortOption option) {
        sortOptions.put(option.getOption(), option);
    }

    public SortOption getOption(String option) {
        return sortOptions.get(option);
    }

    public void setValue(String option, Object value) {
        sortOptions.get(option).setValue(value);
    }
}
