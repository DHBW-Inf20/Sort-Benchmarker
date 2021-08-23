package logic.benchmarks;

import logic.Benchmark;
import sort.Sorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DurationBenchmark extends Benchmark {

    private int arraySize = 5;
    private int iterations = 1;

    @Override
    public HashMap<String, Object> benchmark(List<Sorter> sortPool) {
        HashMap<String, Object> result = new HashMap<>();
        int[][] arr = new int[iterations][];
        for (int i = 0; i < iterations; i++) {
            arr[i] = new int[arraySize];
            getArray(arr[i]);
        }
        ArrayList<Long> tempResults = new ArrayList<>();
        for (Sorter sorter : sortPool) {
            tempResults.clear();
            for (int i = 0; i < iterations; i++) {
                int[] toSort = Arrays.copyOf(arr[i], arr[i].length);
                long start = System.currentTimeMillis();
                sorter.sort(toSort);
                long stop = System.currentTimeMillis();
                tempResults.add(stop - start);
            }
            long sum = 0;
            for (long l : tempResults) {
                sum += l;
            }
            result.put(sorter.getDisplayName(), sum / tempResults.size());
        }
        return result;
    }

    public int getArraySize() {
        return arraySize;
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
