package de.yannick.knapsack.algorithms;

import de.yannick.knapsack.models.Algorithm;
import de.yannick.knapsack.models.Knapsack;

public class DynamicAlgorithm implements Algorithm {

    @Override
    public String getName() {
        return "DynamicAlgorithm";
    }

    @Override
    public Knapsack solve(Knapsack backpack) {
        int[][] matrix = new int[backpack.getItemStorage().length + 1][backpack.getCapacity() + 1];

        for (int i = 0; i <= backpack.getCapacity(); i++) {
            matrix[0][i] = 0;
        }

        for (int i = 1; i <= backpack.getItemStorage().length; i++) {
            for (int j = 0; j <= backpack.getCapacity(); j++) {
                if (backpack.getItemStorage()[i - 1].getWeight() > j) {
                    matrix[i][j] = matrix[i - 1][j];
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j],
                            matrix[i - 1][j - backpack.getItemStorage()[i - 1].getWeight()] + backpack.getItemStorage()[i - 1].getValue());
                }
            }
        }

        int res = matrix[backpack.getItemStorage().length][backpack.getCapacity()];
        int w = backpack.getCapacity();

        for (int i = backpack.getItemStorage().length; i > 0 && res > 0; i--) {
            if (res != matrix[i - 1][w]) {
                backpack.getSelection()[i - 1] = true;
                res -= backpack.getItemStorage()[i - 1].getValue();
                w -= backpack.getItemStorage()[i - 1].getWeight();
            }
        }
        return backpack;
    }
}
