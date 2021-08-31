package benchmarker.logic.benchmarks;

import benchmarker.logic.Benchmark;
import benchmarker.sort.Sorter;
import benchmarker.utils.CSVUtils;
import benchmarker.utils.Settings;
import benchmarker.utils.options.Option;
import benchmarker.utils.options.OptionType;

import javax.swing.*;
import java.util.*;

public class RuntimeBenchmark extends Benchmark {

    private final ArrayList<String[]> contents;

    public RuntimeBenchmark() {
        contents = new ArrayList<>();

        addOption(new Option("Array-Größe", OptionType.NUMBER, 1000000));
        addOption(new Option("Iterationen", OptionType.NUMBER, 10));
    }

    @Override
    public String getName() {
        return "Laufzeit Benchmark";
    }

    @Override
    public void benchmark(List<Sorter> sortPool) {
        contents.clear();

        int arraySize = (int) getValue("Array-Größe");
        int iterations = (int) getValue("Iterationen");

        Random random = new Random();
        long[] seeds = new long[iterations];
        for (int i = 0; i < iterations; i++) {
            seeds[i] = random.nextLong();
        }

        ArrayList<Long> tempResults = new ArrayList<>();
        for (Sorter sorter : sortPool) {
            if (!sorter.passedTests()) continue;
            tempResults.clear();
            for (int i = 0; i < iterations; i++) {
                int[] arr = new int[arraySize];
                getArray(arr, seeds[i]);
                long start = System.currentTimeMillis();
                sorter.sort(arr);
                long stop = System.currentTimeMillis();
                tempResults.add(stop - start);
            }
            long min = Long.MAX_VALUE;
            long max = Long.MIN_VALUE;
            long sum = 0;
            for (long l : tempResults) {
                sum += l;
                if (l > max) max = l;
                if (l < min) min = l;
            }
            double mean = sum / (double) tempResults.size();

            HashMap<String, Object> result = new HashMap<>();
            result.put("mean", mean);
            result.put("max", max);
            result.put("min", min);

            contents.add(new String[] {sorter.getName(), numberToString(mean), numberToString(min), numberToString(max)});

            updateResult(sorter, result);
        }
    }

    @Override
    protected void updateResult(JPanel panel, Object data) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        HashMap<String, Object> result = (HashMap<String, Object>) data;

        double mean = (Double) result.get("mean");
        long min = (Long) result.get("min");
        long max = (Long) result.get("max");

        JLabel meanDuration = new JLabel("Mittlere Laufzeit: " + Math.round(mean * 100) / 100d + " ms");
        meanDuration.setFont(Settings.font);

        JLabel minDuration = new JLabel("Minimale Laufzeit: " + min + " ms");
        minDuration.setFont(Settings.font);

        JLabel maxDuration = new JLabel("Maximale Laufzeit: " + max + " ms");
        maxDuration.setFont(Settings.font);

        panel.add(meanDuration);
        panel.add(minDuration);
        panel.add(maxDuration);
    }

    @Override
    public void exportResults(CSVUtils csvUtils) {
        csvUtils.setHeader(new String[] {"Algorithmus", "Mittlere Laufzeit", "Minimale Laufzeit", "Maxinale Laufzeit"});
        for (String[] content : contents) {
            csvUtils.addContent(content);
        }
    }
}
