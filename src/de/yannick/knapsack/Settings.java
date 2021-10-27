package de.yannick.knapsack;

public class Settings {

    private int capacity;
    private int amountOfItems;
    private KnapsackSolver.SolverType solverType;


    public Settings() {
        this.capacity = 20;
        this.amountOfItems = 5;
        this.solverType = KnapsackSolver.SolverType.DYNAMIC_SOLVER;

    }

    public KnapsackSolver.SolverType getSolverType() {
        return solverType;
    }

    public void setSolverType(KnapsackSolver.SolverType solverType) {
        this.solverType = solverType;
    }

    public int getAmountOfItems() {
        return amountOfItems;
    }

    public void setAmountOfItems(int amountOfItems) {
        this.amountOfItems = amountOfItems;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
