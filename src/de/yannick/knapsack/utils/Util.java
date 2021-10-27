package de.yannick.knapsack.utils;

import de.yannick.knapsack.models.Item;
import de.yannick.knapsack.models.ItemStorage;

import java.util.concurrent.ThreadLocalRandom;

public class Util {

    private final static ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    /**
     * Create a new random generated item
     *
     * @return the randomly generated item
     */
    public static Item createRandomItem() {
        int weight = RANDOM.nextInt(1, 10);
        int value = RANDOM.nextInt(1, 10);
        String name = "ITEM W: " + weight + " V: " + value;
        return new Item(name, weight, value);
    }

    /**
     * Create a array within random generated items
     *
     * @param amount The amount of Items
     * @return the object array with random generated items
     */
    public static ItemStorage[] generateRandomItems(int amount) {
        ItemStorage[] items = new ItemStorage[amount];
        for (int i = 0; i < amount; i++) {
            Item item = createRandomItem();
            items[i] = new ItemStorage() {
                @Override
                public int getValue() {
                    return item.getValue();
                }

                @Override
                public int getWeight() {
                    return item.getWeight();
                }

                @Override
                public Item getItem() {
                    return item;
                }

                @Override
                public String getName() {
                    return item.getName() + "(" + item.getValue() + "V:" + item.getWeight() + "W), ";

                }
            };
        }
        return items;
    }


}
