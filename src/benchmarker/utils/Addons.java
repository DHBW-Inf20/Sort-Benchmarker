package benchmarker.utils;

import benchmarker.main.Main;
import benchmarker.logic.Benchmark;
import benchmarker.logic.Benchmarker;
import benchmarker.sort.Sorter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Addons {

    public static void loadAddons(Benchmarker benchmarker) {
        try {
            Addons.loadExternalAlgorithms(benchmarker);
            Addons.loadExternalBenchmarks(benchmarker);
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void loadExternalAlgorithms(Benchmarker benchmarker) throws IOException, ClassNotFoundException {

        File folder = new File("addons/sorter");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(".jar")) {

                URLClassLoader classLoader = new URLClassLoader(
                        new URL[] {file.toURI().toURL()},
                        Main.class.getClassLoader()
                );

                try (ZipFile zipFile = new ZipFile(file)) {
                    Enumeration zipEntries = zipFile.entries();
                    while (zipEntries.hasMoreElements()) {
                        String fileName = ((ZipEntry) zipEntries.nextElement()).getName();
                        if (!fileName.contains("/") && fileName.endsWith(".class")) {
                            String className = fileName.substring(0, fileName.length() - 6);
                            Class<?> algoClass = Class.forName(className, true, classLoader);
                            if (algoClass.getSuperclass() == Sorter.class) {
                                benchmarker.addSorterClass((Class<? extends Sorter>) algoClass);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void loadExternalBenchmarks(Benchmarker benchmarker) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        File folder = new File("addons/benchmarks");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(".jar")) {

                URLClassLoader classLoader = new URLClassLoader(
                        new URL[] {file.toURI().toURL()},
                        Main.class.getClassLoader()
                );

                try (ZipFile zipFile = new ZipFile(file)) {
                    Enumeration zipEntries = zipFile.entries();
                    while (zipEntries.hasMoreElements()) {
                        String fileName = ((ZipEntry) zipEntries.nextElement()).getName();
                        if (!fileName.contains("/") && fileName.endsWith(".class")) {
                            String className = fileName.substring(0, fileName.length() - 6);
                            Class<?> benchClass = Class.forName(className, true, classLoader);
                            if (benchClass.getSuperclass() == Benchmark.class) {
                                Benchmark benchmark = (Benchmark) benchClass.getDeclaredConstructor().newInstance();
                                benchmarker.addBenchmark(benchmark);
                            }
                        }
                    }
                }
            }
        }
    }
}
