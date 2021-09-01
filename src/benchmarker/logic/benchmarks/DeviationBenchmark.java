package benchmarker.logic.benchmarks;

import benchmarker.logic.Benchmark;
import benchmarker.sort.Sorter;
import benchmarker.utils.CSVUtils;
import benchmarker.utils.Settings;
import benchmarker.utils.options.Option;
import benchmarker.utils.options.OptionType;

import javax.swing.*;
import java.util.*;

public class DeviationBenchmark extends Benchmark {

    private final ArrayList<String[]> contents;

    public DeviationBenchmark() {
        contents = new ArrayList<>();

        addOption(new Option("Array-Größe", OptionType.NUMBER, 500000,1, Integer.MAX_VALUE, 50000));
        addOption(new Option("Iterationen", OptionType.NUMBER, 50,1, Integer.MAX_VALUE));
    }

    @Override
    public String getName() {
        return "Abweichung Benchmark";
    }

    /**
     * @param sortPool Liste an Objekten aller hinzugefügten {@link Sorter}
     */
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

            for (long value : tempResults) {
                if (value < min) min = value;
                if (value > max) max = value;
            }

            long sum = 0;
            for (long l : tempResults) {
                sum += l;
            }
            double mean = sum / (double) tempResults.size();

            long range = max - min;
            double standardDeviation = calculateSD(tempResults, mean);
            double coefficientOfVariation = (standardDeviation / mean) * 100; // Standardabweichung in Prozent

            HashMap<String, Object> result = new HashMap<>();
            result.put("mean", mean);
            result.put("range", range);
            result.put("standardDeviation", standardDeviation);
            result.put("coefficientOfVariation", coefficientOfVariation);

            contents.add(new String[] {sorter.getName(), numberToString(mean), numberToString(range),
                    numberToString(standardDeviation), numberToString(coefficientOfVariation)});

            updateResult(sorter, result);
        }
    }



    /**
     * Berechnung der Standardabweichung
     *
     * @param list  Liste der Laufzeiten eines Algorithmus
     * @param mean  Mittelwert der Laufzeiten eines Algorithmus
     * @return      standardDeviation
     */
    private double calculateSD(ArrayList<Long> list, double mean) {
        double variance = 0;
        for (long l : list) {
            variance += Math.pow(l - mean, 2);
        }
        variance /= list.size() - 1;
        return Math.sqrt(variance);
    }

    /**
     * @param panel Panel eines {@link Sorter}
     * @param data  Daten, welche mit {@link #updateResult(Sorter, Object)} mitgesendet wurden
     */
    @Override
    protected void updateResult(JPanel panel, Object data) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        HashMap<String, Object> result = (HashMap<String, Object>) data;

        double mean = (Double) result.get("mean");
        long range = (Long) result.get("range");
        double standardDeviation = (Double) result.get("standardDeviation");
        double coefficientOfVariation = (Double) result.get("coefficientOfVariation");

        JLabel meanLabel = new JLabel("Mittlere Laufzeit: " + Math.round(mean * 100) / 100d + " ms");
        meanLabel.setFont(Settings.font);

        JLabel rangeLabel = new JLabel("Spannweite: " + range + " ms");
        rangeLabel.setFont(Settings.font);

        JLabel deviationLabel = new JLabel("Standardabweichung: " + Math.round(standardDeviation * 100) / 100d + " ms");
        deviationLabel.setFont(Settings.font);

        JLabel variationLabel = new JLabel("Variationskoeffizient: " + Math.round(coefficientOfVariation * 100) / 100d + "%");
        variationLabel.setFont(Settings.font);

        panel.add(meanLabel);
        panel.add(rangeLabel);
        panel.add(deviationLabel);
        panel.add(variationLabel);
    }

    /**
     * @param csvUtils {@link CSVUtils}
     */
    @Override
    public void exportResults(CSVUtils csvUtils) {
        csvUtils.setHeader(new String[] {"Algorithmus", "Mittlere Laufzeit", "Spannweite", "Standardabweichung", "Variationskoeffizient"});
        for (String[] content : contents) {
            csvUtils.addContent(content);
        }
    }
}
