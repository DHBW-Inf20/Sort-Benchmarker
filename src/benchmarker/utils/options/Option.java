package benchmarker.utils.options;

public class Option {

    private final String option;
    private final OptionType optionType;
    private final Object defaultValue;

    private final int min;
    private final int max;
    private final int step;

    private Object value;

    /**
     * @param option        Name der Option
     * @param optionType    Typ der Option
     * @param defaultValue  Standardwert der Option
     */
    public Option(String option, OptionType optionType, Object defaultValue) {
        this.option = option;
        this.optionType = optionType;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.min = Integer.MIN_VALUE;
        this.max = Integer.MAX_VALUE;
        this.step = 0;
    }

    /**
     * @param option        Name der Option
     * @param optionType    Typ der Option
     * @param defaultValue  Standardwert der Option
     * @param min           minimal einstellbarer Wert (Nur bei ArrayType.NUMBER)
     * @param max           maximal einstellbarer Wert (Nur bei ArrayType.NUMBER)
     */
    public Option(String option, OptionType optionType, Object defaultValue, int min, int max) {
        this.option = option;
        this.optionType = optionType;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.step = 1;
    }

    /**
     * @param option        Name der Option
     * @param optionType    Typ der Option
     * @param defaultValue  Standardwert der Option
     * @param min           minimal einstellbarer Wert (Nur bei ArrayType.NUMBER)
     * @param max           maximal einstellbarer Wert (Nur bei ArrayType.NUMBER)
     * @param step          Schrittgröße (Nur bei ArrayType.NUMBER)
     */
    public Option(String option, OptionType optionType, Object defaultValue, int min, int max, int step) {
        this.option = option;
        this.optionType = optionType;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.step = step;
    }

    /**
     * @return      maximal einstellbarer Wert
     */
    public int getMax() {
        return max;
    }

    /**
     * @return      minimal einstellbarer Wert
     */
    public int getMin() {
        return min;
    }

    /**
     * @return      Schrittgröße
     */
    public int getStep() {
        return step;
    }

    /**
     * @return      Name der Option
     */
    public String getOption() {
        return option;
    }

    /**
     * @return      Typ der Option
     */
    public OptionType getOptionType() {
        return optionType;
    }

    /**
     * @return      Standardwert
     */
    public Object getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param value neuer Wert
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return      aktueller Wert
     */
    public Object getValue() {
        return value;
    }
}
