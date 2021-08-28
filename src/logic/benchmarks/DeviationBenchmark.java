package logic.benchmarks;

import logic.Benchmark;
import sort.Sorter;
import utils.options.Option;
import utils.options.OptionType;

import java.util.*;

public class DeviationBenchmark extends Benchmark {

    public DeviationBenchmark() {
        addOption(new Option("Listengröße", OptionType.NUMBER, 1000000));
        addOption(new Option("Iterationen", OptionType.NUMBER, 100));
    }

    @Override
    public String getName() {
        return "Abweichung Benchmark";
    }

    @Override
    public HashMap<Sorter, Object> benchmark(List<Sorter> sortPool) {
        int arraySize = (int) getValue("Listengröße");
        int iterations = (int) getValue("Iterationen");

        HashMap<Sorter, Object> result = new HashMap<>();
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

            result.put(sorter, standardDeviation);
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
        variance /= list.size() - 1;
        return Math.sqrt(variance);
    }
}
