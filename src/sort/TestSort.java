package sort;

import sort.algorithms.QuickSort;
import utils.options.OptionType;
import utils.options.Option;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

public class TestSort extends Sorter {

    public TestSort() {
        addOption(new Option("Test", OptionType.NUMBER, 10));
        System.out.println(getValue("Test"));
        setValue("Test", 20);
        System.out.println(getValue("Test"));
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int[] sort(int[] toSort) {
        return new int[0];
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {


//        System.out.println(new File("./").getAbsolutePath());

        Sorter s = new QuickSort();

        int[] arr = new int [1000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000000);
        }

        long start = System.currentTimeMillis();
        int[] sorted = s.sort(arr);
        long stop = System.currentTimeMillis();
        System.out.println(stop - start + "ms");

//        for (int i : sorted) {
//            System.out.print(i + " ");
//        }

//        for (int i = 0; i < 20; i++) {
//            long start = System.currentTimeMillis();
//            s.sort(null);
//            long stop = System.currentTimeMillis();
//            System.out.println(stop - start + "ms");
//        }

//        File file = new File("D:/Fabian/Dukumente/IdeaProjects/TestSort/out/artifacts/TestSort/TestSort.jar");
//        if (file.exists()) {
//            URLClassLoader classLoader = new URLClassLoader(
//                    new URL[] {file.toURI().toURL()},
//                    TestSort.class.getClassLoader()
//            );
//            Class algoClass = Class.forName("sort.Algo", true, classLoader);
//            Method method = algoClass.getDeclaredMethod("sort");
//            Object result = method.invoke(classLoader);
//            System.out.println(result);
//        }
    }
}
