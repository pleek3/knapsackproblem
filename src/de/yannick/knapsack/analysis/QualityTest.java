package de.yannick.knapsack.analysis;

import de.yannick.knapsack.KnapsackSolver;
import de.yannick.knapsack.models.Item;
import de.yannick.knapsack.models.ItemStorage;
import de.yannick.knapsack.models.Knapsack;
import de.yannick.knapsack.utils.Util;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QualityTest {

    private static final List<ItemStorage[]> testItems = new ArrayList<>();
    private static final List<QualityTestResult> resultList = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            testItems.add(Util.generateRandomItems(10));
        }

        testQuality(20);
        new QualityTestResultHandler().saveTestResults(resultList);

    }

    public static void testQuality(int capacity) {
        for (ItemStorage[] items : testItems) {
            Knapsack knapsack = new Knapsack(items, capacity);
            KnapsackSolver dSolver = new KnapsackSolver();
            dSolver.init(knapsack, KnapsackSolver.SolverType.DYNAMIC_SOLVER);
            dSolver.solve();
            List<Item> dSolution = dSolver.getBestSolutions();
            long dWeight = dSolver.getTotalWeight();
            long dValue = dSolver.getTotalValue();

            KnapsackSolver rSolver = new KnapsackSolver();
            rSolver.init(knapsack, KnapsackSolver.SolverType.RECURSIVE_SOLVER);
            rSolver.solve();
            List<Item> rSolution = rSolver.getBestSolutions();
            long rWeight = rSolver.getTotalWeight();
            long rValue = rSolver.getTotalValue();

            resultList.add(new QualityTestResult(dWeight, dValue, rWeight, rValue, dSolution, rSolution, items));
        }
    }

    private static record QualityTestResult(long dynamicWeight, long dynamicValue, long recursiveWeight,
                                            long recursiveValue, List<Item> dynamicSolution,
                                            List<Item> recursiveSolution, ItemStorage[] itemStorage) {
    }

    private static class QualityTestResultHandler {

        private final String filePath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        private final Random random = new Random();

        public QualityTestResultHandler() {

        }

        public void saveTestResults(List<QualityTestResult> results) {
            for (QualityTestResult result : results) {
                try {
                    File file = new File(filePath, generateRandomFileName() + ".txt");
                    file.getParentFile().mkdir();
                    file.createNewFile();


                    FileWriter writer = new FileWriter(file);
                    writer.write("Test Results for Quality Test\n");

                    writer.write(
                            "Dynamic Value: " + result.dynamicValue() + " - Dynamic Weight: " + result.dynamicWeight() + "\n");
                    writer.write(
                            "Recursive Value: " + result.recursiveValue() + " - Recursive Weight: " + result.recursiveWeight() + "\n\n");

                    writer.write("ItemStorage: \n");
                    for (ItemStorage item : result.itemStorage())
                        writer.write(item.getName() + "\n");


                    writer.write("\nDynamic Algorithm Solution: \n");
                    for (Item item : result.dynamicSolution())
                        writer.write(item.getName() + "\n");


                    writer.write("\nRecursive Algorithm Solution: \n");
                    for (Item item : result.recursiveSolution())
                        writer.write(item.getName() + "\n");


                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Files successfully created");
        }

        private String generateRandomFileName() {
            return "Test-" + random.nextInt();
        }

    }

}
