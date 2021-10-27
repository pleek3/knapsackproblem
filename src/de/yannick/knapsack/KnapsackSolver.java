package de.yannick.knapsack;

import de.yannick.knapsack.algorithms.DynamicAlgorithm;
import de.yannick.knapsack.algorithms.RecursiveAlgorithm;
import de.yannick.knapsack.models.Algorithm;
import de.yannick.knapsack.models.Item;
import de.yannick.knapsack.models.Knapsack;

import java.util.ArrayList;
import java.util.List;

public class KnapsackSolver {

    private final List<Item> bestSolutions = new ArrayList<>();
    private SolverType solverType = null;
    private Knapsack knapsackToSolve = null;

    public KnapsackSolver() {
    }

    public void init(Knapsack backpack, SolverType type) {
        this.knapsackToSolve = backpack;
        this.solverType = type;
    }

    public void solve() {
        if (this.solverType == null || this.knapsackToSolve == null) {
            System.out.println("Error: solverTyp = null or knapsackToSolve = null");
            return;
        }

        Knapsack result = solverType.getAlgorithm().solve(this.knapsackToSolve);

        for (int i = 0; i < result.getSelection().length; i++) {
            if (result.getSelection()[i]) {
                this.bestSolutions.add(this.knapsackToSolve.getItemStorage()[i].getItem());
            }
        }

    }

    public List<Item> getBestSolutions() {
        return bestSolutions;
    }

    public int getTotalWeight() {
        return bestSolutions.stream().mapToInt(Item::getWeight).sum();
    }

    public int getTotalValue() {
        return bestSolutions.stream().mapToInt(Item::getValue).sum();
    }

    public enum SolverType {
        RECURSIVE_SOLVER(new RecursiveAlgorithm(),"Rekursiver Algorithmus"),
        DYNAMIC_SOLVER(new DynamicAlgorithm(),"Dynamischer Algorithmus"),
        ;

        private final Algorithm algorithm;
        private final String name;

        SolverType(Algorithm algorithm, String name) {
            this.algorithm = algorithm;
            this.name = name;
        }

        public static SolverType getTypeByName(String name) {
            for(SolverType type : values()){
                if(type.getName().equalsIgnoreCase(name))return type;
            }
            return null;
        }

        public Algorithm getAlgorithm() {
            return algorithm;
        }

        public String getName() {
            return name;
        }
    }

}
