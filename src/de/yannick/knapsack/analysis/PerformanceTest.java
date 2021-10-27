package de.yannick.knapsack.analysis;

import de.yannick.knapsack.KnapsackSolver;
import de.yannick.knapsack.models.ItemStorage;
import de.yannick.knapsack.models.Knapsack;
import de.yannick.knapsack.utils.Util;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PerformanceTest {

    private static final ItemStorage[] test1 = Util.generateRandomItems(10);
    private static final ItemStorage[] test2 = Util.generateRandomItems(100);
    private static final ItemStorage[] test3 = Util.generateRandomItems(1000);
    private static final ItemStorage[] test4 = Util.generateRandomItems(10000);
    private static final ItemStorage[] test5 = Util.generateRandomItems(100000);
    private static final ItemStorage[] test6 = Util.generateRandomItems(1000000);

    private static final Map<Integer, List<PerformanceTestResult>> resultMap = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i <= 5; i++) {
            resultMap.put(i, new ArrayList<>());
        }
        testPerformance(KnapsackSolver.SolverType.DYNAMIC_SOLVER, 20);

      /*  new PerformanceTestResultHandler().showAverage(resultMap.get(0));
        new PerformanceTestResultHandler().showAverage(resultMap.get(1));
        new PerformanceTestResultHandler().showAverage(resultMap.get(2));
        new PerformanceTestResultHandler().showAverage(resultMap.get(3));
        new PerformanceTestResultHandler().showAverage(resultMap.get(4));
        new PerformanceTestResultHandler().showAverage(resultMap.get(5));*/
        new PerformanceTestResultHandler().saveTestResults(resultMap.get(0));
        new PerformanceTestResultHandler().saveTestResults(resultMap.get(1));
        new PerformanceTestResultHandler().saveTestResults(resultMap.get(2));
        new PerformanceTestResultHandler().saveTestResults(resultMap.get(3));
        new PerformanceTestResultHandler().saveTestResults(resultMap.get(4));
        new PerformanceTestResultHandler().saveTestResults(resultMap.get(5));


    }

    public static void testPerformance(KnapsackSolver.SolverType type, int capacity) {
        KnapsackSolver solver = new KnapsackSolver();
        System.out.println("start analysis test");

        for (int i = 0; i <= 100; i++) {
            Knapsack knapsack = new Knapsack(test1, capacity);
            solver.init(knapsack, type);
            long startNano = System.nanoTime();
            solver.solve();
            long stopNano = System.nanoTime();
         /*   System.out.println(
                    "Test1: " + (stopNano - startNano) + " " + TimeUnit.MILLISECONDS.convert((stopNano - startNano),
                            TimeUnit.NANOSECONDS) + "ms " + test1.length + " items Capacity: 20\n\n");*/
            List<PerformanceTestResult> performanceTestResults = resultMap.get(0);
            performanceTestResults.add(new PerformanceTestResult(startNano, stopNano, "Test-" + i, test1.length,
                    capacity, type));
            resultMap.put(0, performanceTestResults);


            knapsack = new Knapsack(test2, capacity);
            solver.init(knapsack, type);
            startNano = System.nanoTime();
            solver.solve();
            stopNano = System.nanoTime();
          /*  System.out.println(
                    "Test2: " + (stopNano - startNano) + " " + TimeUnit.MILLISECONDS.convert((stopNano - startNano),
                            TimeUnit.NANOSECONDS) + "ms " + test2.length + " items Capacity: 20\n\n");*/

            performanceTestResults = resultMap.get(1);
            performanceTestResults.add(new PerformanceTestResult(startNano, stopNano, "Test-" + i, test2.length,
                    capacity, type));
            resultMap.put(1, performanceTestResults);

            knapsack = new Knapsack(test3, capacity);
            solver.init(knapsack, type);
            startNano = System.nanoTime();
            solver.solve();
            stopNano = System.nanoTime();
         /*   System.out.println(
                    "Test3: " + (stopNano - startNano) + " " + TimeUnit.MILLISECONDS.convert((stopNano - startNano),
                            TimeUnit.NANOSECONDS) + "ms " + test3.length + " items Capacity: 20\n\n");*/

            performanceTestResults = resultMap.get(2);
            performanceTestResults.add(new PerformanceTestResult(startNano, stopNano, "Test-" + i, test3.length,
                    capacity, type));
            resultMap.put(2, performanceTestResults);

            knapsack = new Knapsack(test4, capacity);
            solver.init(knapsack, type);
            startNano = System.nanoTime();
            solver.solve();
            stopNano = System.nanoTime();
          /*  System.out.println(
                    "Test4: " + (stopNano - startNano) + " " + TimeUnit.MILLISECONDS.convert((stopNano - startNano),
                            TimeUnit.NANOSECONDS) + "ms " + test4.length + " items Capacity: 20\n\n");*/

            performanceTestResults = resultMap.get(3);
            performanceTestResults.add(new PerformanceTestResult(startNano, stopNano, "Test-" + i, test4.length,
                    capacity, type));
            resultMap.put(3, performanceTestResults);

            knapsack = new Knapsack(test5, capacity);
            solver.init(knapsack, type);
            startNano = System.nanoTime();
            solver.solve();
            stopNano = System.nanoTime();
        /*    System.out.println(
                    "Test5: " + (stopNano - startNano) + " " + TimeUnit.MILLISECONDS.convert((stopNano - startNano),
                            TimeUnit.NANOSECONDS) + "ms " + test5.length + " items Capacity: 20\n\n");*/

            performanceTestResults = resultMap.get(4);
            performanceTestResults.add(new PerformanceTestResult(startNano, stopNano, "Test-" + i, test5.length,
                    capacity, type));
            resultMap.put(4, performanceTestResults);

            knapsack = new Knapsack(test6, capacity);
            solver.init(knapsack, type);
            startNano = System.nanoTime();
            solver.solve();
            stopNano = System.nanoTime();
      /*      System.out.println(
                    "Test6: " + (stopNano - startNano) + " " + TimeUnit.MILLISECONDS.convert((stopNano - startNano),
                            TimeUnit.NANOSECONDS) + "ms " + test6.length + " items Capacity: 20 \n\n");*/
            performanceTestResults = resultMap.get(5);
            performanceTestResults.add(new PerformanceTestResult(startNano, stopNano, "Test-" + i, test6.length,
                    capacity, type));
            resultMap.put(5, performanceTestResults);
        }

        System.out.println("finished analysis test");
    }

    private static record PerformanceTestResult(long nanoStart, long nanoStop, String testName, int amountOfItems,
                                                int capacity, KnapsackSolver.SolverType solverType) {

        public long getNanoStart() {
            return nanoStart;
        }

        public long getNanoStop() {
            return nanoStop;
        }

        public String getTestName() {
            return testName;
        }

        public int getAmountOfItems() {
            return amountOfItems;
        }

        public int getCapacity() {
            return capacity;
        }

    }

    private static class PerformanceTestResultHandler {

        private final String filePath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        private final Random random = new Random();

        public PerformanceTestResultHandler() {

        }

        public void showAllResults(List<PerformanceTestResult> results) {
            PerformanceTestResult r = results.get(0);
            long average = calculateAverage(results);

            System.out.println("Test Results for Performance Test");
            System.out.println("Tested with Items = " + r.getAmountOfItems() + ", Capacity = " + r.getCapacity());
            System.out.println("Average of Test: " + average + "ns = " + TimeUnit.MILLISECONDS.convert(average,
                    TimeUnit.NANOSECONDS));

            for (PerformanceTestResult result : results)
                System.out.println(
                        result.getTestName() + ": " + (result.getNanoStop() - result.getNanoStart()) + "ns = " + TimeUnit.MILLISECONDS.convert(
                                (result.getNanoStop() - result.getNanoStart()), TimeUnit.NANOSECONDS) + "ms");
        }

        public void saveTestResults(List<PerformanceTestResult> results) {
            PerformanceTestResult r = results.get(0);
            long average = calculateAverage(results);

            try {
                File file = new File(filePath, generateRandomFileName() + " (" + r.getAmountOfItems() + ").txt");
                file.getParentFile().mkdir();
                file.createNewFile();


                FileWriter writer = new FileWriter(file);
                writer.write("Test Results for Performance Test\n");
                writer.write("Algorithm: " + r.solverType().getName() + "\n");
                writer.write("Tested with Items = " + r.getAmountOfItems() + ", Capacity = " + r.getCapacity() + "\n");
                writer.write("Average of Test: " + average + "ns = " + TimeUnit.MILLISECONDS.convert(average,
                        TimeUnit.NANOSECONDS) + "ms\n");
                writer.write(" \n\n");

                for (PerformanceTestResult result : results)
                    writer.write(
                            result.getTestName() + ": " + (result.getNanoStop() - result.getNanoStart()) + "ns = " + TimeUnit.MILLISECONDS.convert(
                                    (result.getNanoStop() - result.getNanoStart()), TimeUnit.NANOSECONDS) + "ms\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("File successfully created");


        }

        private long calculateAverage(List<PerformanceTestResult> results) {
            long average = 0;

            for (PerformanceTestResult result : results) {
                average += (result.getNanoStop() - result.getNanoStart());
            }

            return (average / results.size());
        }

        private String generateRandomFileName() {
            return "Test-" + random.nextInt();
        }

    }


}
