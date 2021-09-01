package benchmarker.logic.benchmarks;

import benchmarker.logic.Benchmark;
import benchmarker.sort.Sorter;
import benchmarker.utils.CSVUtils;
import benchmarker.utils.Settings;
import benchmarker.utils.options.Option;
import benchmarker.utils.options.OptionType;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class RuntimeBenchmark extends Benchmark {

    private final ArrayList<String[]> contents;

    private boolean showBars;

    public RuntimeBenchmark() {
        contents = new ArrayList<>();

        addOption(new Option("Array-Größe", OptionType.NUMBER, 1000000, 1, Integer.MAX_VALUE, 100000));
        addOption(new Option("Iterationen", OptionType.NUMBER, 10, 1, Integer.MAX_VALUE));
        addOption(new Option("Balken anzeigen", OptionType.BOOL, true));
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
        showBars = (boolean) getValue("Balken anzeigen");

        Random random = new Random();
        long[] seeds = new long[iterations];
        for (int i = 0; i < iterations; i++) {
            seeds[i] = random.nextLong();
        }

        long globalMax = Long.MIN_VALUE;
        HashMap<Sorter, HashMap<String, Object>> datas = new HashMap<>();
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

            if (max > globalMax) globalMax = max;
            datas.put(sorter, result);

            updateResult(sorter, result);
        }
        if (showBars) {
            for (Sorter sorter : datas.keySet()) {
                HashMap<String, Object> result = datas.get(sorter);
                result.put("globalMax", globalMax);
                updateResult(sorter, result);
            }
        }
    }

    @Override
    protected void updateResult(JPanel panel, Object data) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        HashMap<String, Object> result = (HashMap<String, Object>) data;

        double mean = (Double) result.get("mean");
        long min = (Long) result.get("min");
        long max = (Long) result.get("max");

        if (showBars) {
            if (result.get("globalMax") != null) {
                long globalMax = (Long) result.get("globalMax");
                System.out.println(globalMax);
                Bar bar = new Bar(mean / (double)  globalMax, min / (double) globalMax, max / (double) globalMax);
                bar.setPreferredSize(new Dimension(800, 30));
                panel.add(bar);
            } else {
                Bar bar = new Bar(-1, -1, -1);
                bar.setPreferredSize(new Dimension(800, 30));
                panel.add(bar);
            }
            panel.add(Box.createVerticalStrut(10));
        }

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
        csvUtils.setHeader(new String[] {"Algorithmus", "Mittlere Laufzeit", "Minimale Laufzeit", "Maximale Laufzeit"});
        for (String[] content : contents) {
            csvUtils.addContent(content);
        }
    }

    public static class Bar extends JComponent {

        private final double mean;
        private final double min;
        private final double max;

        public Bar(double mean, double min, double max) {
            this.mean = mean;
            this.min = min;
            this.max = max;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(new Color(192, 192, 192));
            g.fillRect(0, 5, getWidth(), getHeight() - 10);
            if (mean != -1) {
                g.setColor(new Color(204, 0, 0));
                g.fillRect(0, 5, (int) (getWidth() * max), getHeight() - 10);
                g.setColor(new Color(0, 160, 0));
                g.fillRect(0, 5, (int) (getWidth() * min), getHeight() - 10);
                g.setColor(new Color(0 ,0, 0));
                g.fillRect((int) (getWidth() * mean) - 2, 0, 4, getHeight());
            }
        }
    }
}
