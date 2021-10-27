package de.yannick.knapsack.models;

import java.util.Arrays;

public class Knapsack {

    private final boolean[] selection;
    private final int capacity;
    private final ItemStorage[] itemStorage;

    public Knapsack(ItemStorage[] items, int capacity) {
        this.itemStorage = items;
        this.capacity = capacity;
        this.selection = new boolean[items.length];
    }

    public Knapsack(Knapsack backpack) {
        this.selection = backpack.getSelection();
        this.itemStorage = backpack.getItemStorage();
        this.capacity = backpack.getCapacity();
    }

    /**
     * Calculate the current weight of this backpack
     * <p>
     * The weight is determined by adding the base value of the weight with the weight of the selected items
     *
     * @return The complete weight of this backpack
     */
    public int getWeight() {
        int weight = 0;
        for (int i = 0; i < this.selection.length; i++) {
            if (selection[i])
                weight = weight + itemStorage[i].getWeight();
        }
        return weight;
    }

    /**
     * Calculate the current value of this backpack
     * <p>
     * The value is determined by adding the base value of the value with the value of the selected items
     *
     * @return The complete value of this backpack
     */
    public int getValue() {
        int value = 0;
        for (int i = 0; i < selection.length; i++) {
            if (selection[i])
                value = value + itemStorage[i].getWeight();
        }
        return value;
    }


    public boolean[] getSelection() {
        return selection;
    }

    public ItemStorage[] getItemStorage() {
        return itemStorage;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Backpack{" +
                "selection=" + Arrays.toString(selection) +
                '}';
    }
}
