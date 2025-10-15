package com.javarush.island.kozlov.entity.animals;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int maxOnCell, int speed, double foodNeeded) {
        super(weight, maxOnCell, speed, foodNeeded);
    }
}
