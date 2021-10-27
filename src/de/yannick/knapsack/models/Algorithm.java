package de.yannick.knapsack.models;

/**
 * Simple interface class for basic algorithm
 */
public interface Algorithm {

    String getName();

    Knapsack solve(Knapsack backpack);

}
