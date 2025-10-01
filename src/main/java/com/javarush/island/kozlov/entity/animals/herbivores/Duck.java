package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.map.Cell;
import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Herbivore;
import com.javarush.island.kozlov.entity.plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivore {

    public Duck() {
        super(1, 200, 14, 0.15);
        eatProbabilities.put(Caterpillar.class, 90);
    }

    @Override
    public void eat(Cell cell) {
        cell.cellLock.lock();
        try {
            System.out.println("Утка ест, начальная сытость: " + currentSaturation + ", foodNeeded: " + foodNeeded); //++
            if (currentSaturation < foodNeeded) {
                List<Animal> animalsCopy = new ArrayList<>(cell.animals);
                List<Animal> toRemove = new ArrayList<>();

                for (Animal prey : animalsCopy) {
                    if (prey != this && prey instanceof Caterpillar && eatProbabilities.containsKey(Caterpillar.class)) {
                        int prob = eatProbabilities.get(Caterpillar.class);
                        if (ThreadLocalRandom.current().nextInt(100) < prob) {
                            currentSaturation += prey.getWeight();
                            toRemove.add(prey);
                            System.out.println("Утка ест гусеницу, новая сытость: " + currentSaturation); //++
                            if (currentSaturation >= foodNeeded) break;
                        }
                    }
                }
                for (Animal prey : toRemove) {
                    cell.removeAnimal(prey);
                    System.out.println("Удалена гусеница"); //++
                }

                if (currentSaturation < foodNeeded && !cell.plants.isEmpty()) {
                    Plant plant = cell.plants.remove(0);
                    currentSaturation += plant.getWeight();
                    if (currentSaturation > foodNeeded) {
                        currentSaturation = foodNeeded;
                    }
                } System.out.println("Утка ест растение, сытость: " + currentSaturation);
            }
        } finally {
            cell.cellLock.unlock();
        }

    }

    @Override
    public Animal createNew() {
        return new Duck();
    }


}
