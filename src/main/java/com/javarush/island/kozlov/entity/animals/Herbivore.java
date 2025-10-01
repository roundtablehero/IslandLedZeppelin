package com.javarush.island.kozlov.entity.animals;

import com.javarush.island.kozlov.map.Cell;
import com.javarush.island.kozlov.api.action.Eatable;
import com.javarush.island.kozlov.entity.plants.Plant;

public abstract class Herbivore extends Animal implements Eatable {

    public Herbivore(double weight, int maxPerCell, int speed, double foodNeeded) {
        super(weight, maxPerCell, speed, foodNeeded);
        eatProbabilities.put(Plant.class, 100);
    }

    @Override
    public void eat(Cell cell) {
        cell.cellLock.lock();

        try {
            if (currentSaturation < foodNeeded && !cell.plants.isEmpty()) {
                Plant plant = cell.plants.remove(0);
                currentSaturation += plant.getWeight();
                if (currentSaturation > foodNeeded) {
                    currentSaturation = foodNeeded;
                }
            }
        } finally {
            cell.cellLock.unlock();
        }
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
