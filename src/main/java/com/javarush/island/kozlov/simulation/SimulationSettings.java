package com.javarush.island.kozlov.simulation;

public class SimulationSettings {
    private int maxSteps;
    private int stepDelay;

    public SimulationSettings(int maxSteps, int stepDelay) {
        this.maxSteps = maxSteps;
        this.stepDelay = stepDelay;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public int getStepDelay() {
        return stepDelay;
    }
}
