package logic;

import sort.Sorter;

import java.util.HashMap;
import java.util.List;

public abstract class Benchmark {
    private ArrayType arrayType;

    public Benchmark() {
        arrayType = ArrayType.RANDOM;
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

    public abstract HashMap<String, Object> benchmark(List<Sorter> sortPool);

    public enum ArrayType {
        RANDOM, ASC, DESC;
    }
}
