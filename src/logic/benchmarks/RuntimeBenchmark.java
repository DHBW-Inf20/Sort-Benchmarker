package logic.benchmarks;

import logic.Benchmark;
import sort.Sorter;
import utils.options.Option;
import utils.options.OptionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RuntimeBenchmark extends Benchmark {

    public RuntimeBenchmark() {
        addOption(new Option("Listengröße", OptionType.NUMBER, 5000000));
        addOption(new Option("Iterationen", OptionType.NUMBER, 10));
    }

    @Override
    public String getName() {
        return "Laufzeit Benchmark";
    }

    @Override
    public HashMap<Sorter, Object> benchmark(List<Sorter> sortPool) {
        int arraySize = (int) getValue("Listengröße");
        int iterations = (int) getValue("Iterationen");

        HashMap<Sorter, Object> result = new HashMap<>();
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
            result.put(sorter, sum / tempResults.size());
        }
        return result;
    }
}
