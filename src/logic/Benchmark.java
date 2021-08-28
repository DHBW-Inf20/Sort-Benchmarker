package logic;

import sort.Sorter;
import utils.options.Option;
import utils.options.Options;

import java.util.HashMap;
import java.util.List;

public abstract class Benchmark {

    private final Options options;
    private ArrayType arrayType;

    public Benchmark() {
        options = new Options();
        arrayType = ArrayType.RANDOM;
    }

    public void addOption(Option option) {
        options.addOption(option);
    }

    public Object getValue(String option) {
        return options.getOption(option).getValue();
    }

    public void setValue(String option, Object value) {
        options.setValue(option, value);
    }

    public Options getSortOptions() {
        return options;
    }

    public void getArray(int[] arr) {
        switch (arrayType) {
            case RANDOM:
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = (int) (Math.random() * arr.length);
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

    public ArrayType getArrayType() {
        return arrayType;
    }

    public void setArrayType(ArrayType arrayType) {
        this.arrayType = arrayType;
    }

    public abstract String getName();

    public abstract HashMap<Sorter, Object> benchmark(List<Sorter> sortPool);

    public enum ArrayType {
        RANDOM, ASC, DESC;
    }
}
