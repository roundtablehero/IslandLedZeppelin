package com.javarush.island.kozlov.entity.animals;

public abstract class Predator extends Animal {
    public Predator(double weight, int maxOnCell, int speed, double foodNeeded) {
        super(weight, maxOnCell, speed, foodNeeded);
    }
}
