package de.yannick.knapsack.algorithms;

import de.yannick.knapsack.models.Algorithm;
import de.yannick.knapsack.models.Knapsack;

/**
 * RecursiveAlgorithm test class
 */
public class RecursiveAlgorithm implements Algorithm {

    @Override
    public String getName() {
        return "RecursiveAlgorithm";
    }

    @Override
    public Knapsack solve(Knapsack knapsack) {
        return solveAlgorithm(0, knapsack);
    }


    public Knapsack solveAlgorithm(int current, Knapsack knapsack) {
        if (current == knapsack.getItemStorage().length) return knapsack;

        if (knapsack.getItemStorage()[current].getWeight() > knapsack.getCapacity()) return knapsack;

        Knapsack backpack1 = new Knapsack(knapsack);

        if (backpack1.getWeight() + knapsack.getItemStorage()[current].getWeight() <= knapsack.getCapacity()) {
            Knapsack backpack2 = new Knapsack(knapsack);
            backpack2.getSelection()[current] = true;
            backpack2 = solveAlgorithm(current + 1, backpack2);

            if (backpack2.getValue() >= backpack1.getValue()) {
                return backpack2;
            }

        }

        return backpack1;
    }


}
