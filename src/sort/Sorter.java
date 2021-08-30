package sort;

import utils.options.OptionType;
import utils.options.Option;
import utils.options.Options;

public abstract class Sorter {

    private final Options options;
    private boolean testsPassed;

    public Sorter() {
        this.options = new Options();
        testsPassed = false;
        addOption(new Option("Name", OptionType.STRING, getName()));
    }

    public void addOption(Option option) {
        options.addOption(option);
    }

    public void setTestsPassed(boolean testsPassed) {
        this.testsPassed = testsPassed;
    }

    public boolean passedTests() {
        return testsPassed;
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

    public String getDisplayName() {
        return (String) getValue("Name");
    }

    public abstract String getName();

    public void initSorting() {}

    public abstract int[] sort(int[] toSort);
}
