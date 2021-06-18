package sort;

import sort.options.SortOption;
import sort.options.SortOptions;

public abstract class Sorter {

    private SortOptions sortOptions;

    public Sorter() {
        this.sortOptions = new SortOptions();
        System.out.println("TEST");
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

    public abstract String getName();
    public abstract int[] sort(int[] toSort);
}
