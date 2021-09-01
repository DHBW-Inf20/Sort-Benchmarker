package benchmarker.utils.options;

public class Option {

    private String option;
    private OptionType optionType;
    private Object defaultValue;

    private Object value;

    private int min;
    private int max;
    private int step;

    public Option(String option, OptionType optionType, Object defaultValue) {
        this.option = option;
        this.optionType = optionType;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.min = Integer.MIN_VALUE;
        this.max = Integer.MAX_VALUE;
        this.step = 0;
    }

    public Option(String option, OptionType optionType, Object defaultValue, int min, int max) {
        this.option = option;
        this.optionType = optionType;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.step = 1;
    }

    public Option(String option, OptionType optionType, Object defaultValue, int min, int max, int step) {
        this.option = option;
        this.optionType = optionType;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.step = step;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getStep() {
        return step;
    }

    public String getOption() {
        return option;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
