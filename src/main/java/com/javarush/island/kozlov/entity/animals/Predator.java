package com.javarush.island.kozlov.entity.animals;

import com.javarush.island.kozlov.map.Cell;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {

    public Predator(double weight, int maxPerCell, int speed, double foodNeeded) {
        super(weight, maxPerCell, speed, foodNeeded);
    }

    @Override
    public void eat(Cell cell) {
        cell.cellLock.lock();

        try {
            if (currentSaturation < foodNeeded) {
                for (Animal prey: cell.animals) {
                    if (prey != this && eatProbabilities.containsKey(prey.getClass())) {
                        int prob = eatProbabilities.get(prey.getClass());
                        if (ThreadLocalRandom.current().nextInt(100) < prob) {
                            currentSaturation += prey.getWeight();
                            cell.removeAnimal(prey);

                            if (currentSaturation > foodNeeded) {
                                currentSaturation = foodNeeded;
                            } break;

                        }
                    }
                }
            }
        } finally {
            cell.cellLock.unlock();
        }
    }
}
