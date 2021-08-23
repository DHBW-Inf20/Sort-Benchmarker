package logic.benchmarks;

import logic.Benchmark;
import sort.Sorter;

import java.util.*;

public class DeviationBenchmark extends Benchmark {
    private int arraySize = 1000000;
    private int iterations = 100;

    @Override
    public HashMap<String, Object> benchmark(List<Sorter> sortPool) {
        HashMap<String, Object> result = new HashMap<>();
        int[] arr = new int[arraySize];
        getArray(arr);
        ArrayList<Long> tempResults = new ArrayList<>();
        for (Sorter sorter : sortPool) {
            tempResults.clear();
            for (int i = 0; i < iterations; i++) {
                int[] toSort = Arrays.copyOf(arr, arr.length);
                long start = System.currentTimeMillis();
                sorter.sort(toSort);
                long stop = System.currentTimeMillis();
                tempResults.add(stop - start);
            }

            long min = Long.MAX_VALUE;
            long max = Long.MIN_VALUE;

            for (long value : tempResults) {
                if (value < min) min = value;
                if (value > max) max = value;
            }

            long range = max - min;
            double standardDeviation = calculateSD(tempResults);

            result.put(sorter.getDisplayName(), standardDeviation);
        }
        return result;
    }

    // Standard Deviation / Standardabweichung
    private double calculateSD(ArrayList<Long> list) {
        long sum = 0;
        for (long l : list) {
            sum += l;
        }
        double mean = sum / (double) list.size();
        double variance = 0;
        for (long l : list) {
            variance += Math.pow(l - mean, 2);
        }
        variance /= list.size();
        return Math.sqrt(variance);
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
