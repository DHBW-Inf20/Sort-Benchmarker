package sort;

import sort.options.OptionType;
import sort.options.SortOption;
import sort.options.SortOptions;

public abstract class Sorter {

    private SortOptions sortOptions;

    public Sorter() {
        this.sortOptions = new SortOptions();
        addOption(new SortOption("Name", OptionType.STRING, getName()));
    }

    public void addOption(SortOption option) {
        sortOptions.addOption(option);
    }

    public Object getValue(String option) {
        return sortOptions.getOption(option).getValue();
    }

    public void setValue(String option, Object value) {
        sortOptions.setValue(option, value);
    }

    public SortOptions getSortOptions() {
        return sortOptions;
    }

    public String getDisplayName() {
        return (String) getValue("Name");
    }

    public abstract String getName();

    public abstract int[] sort(int[] toSort);
}
