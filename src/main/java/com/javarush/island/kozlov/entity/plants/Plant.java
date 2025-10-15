package com.javarush.island.kozlov.entity.plants;

public class Plant {
    private double weight;
    private final double maxWeight;
    private final double growthRate; // скорость роста за один ход

    public Plant(double maxWeight, double growthRate) {
        this.maxWeight = maxWeight;
        this.growthRate = growthRate;
        this.weight = maxWeight; // в начале растение полностью выросло
    }

    public double getWeight() {
        return weight;
    }

    public boolean isAlive() {
        return weight > 0;
    }

    public void reduceWeight(double amount) {
        weight -= amount;
        if (weight < 0) {
            weight = 0;
        }
    }

    public void grow() {
        if (weight < maxWeight) {
            weight = Math.min(maxWeight, weight + growthRate);
        }
    }
}
