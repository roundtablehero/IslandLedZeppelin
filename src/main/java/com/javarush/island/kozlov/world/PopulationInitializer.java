package com.javarush.island.kozlov.world;


import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.herbivores.*;
import com.javarush.island.kozlov.entity.animals.predators.*;
import com.javarush.island.kozlov.entity.plants.Plant;

import java.util.Arrays;
import java.util.List;

import static com.javarush.island.kozlov.util.Rnd.random;
import static com.javarush.island.kozlov.util.Rnd.randomInt;

// Добавить рандомизацию добавления позже
public class PopulationInitializer {

    private static final List<Animal> ANIMAL_PROTOTYPES_PREDATOR = Arrays.asList(
            new Wolf(),
            new Boa(),
            new Fox(),
            new Bear(),
            new Eagle()
    );
    private static final List<Animal> ANIMAL_PROTOTYPES_HERBIVORE = Arrays.asList(
            new Horse(),
            new Deer(),
            new Rabbit(),
            new Mouse(),
            new Goat(),
            new Sheep(),
            new Boar(),
            new Buffalo(),
            new Duck(),
            new Caterpillar()
    );

    public static void fill(Island island) {

        int rows = island.getMap().length;
        int cols = island.getMap()[0].length;


        for (Animal prototype : ANIMAL_PROTOTYPES_PREDATOR) {
            int count = random(rows * cols / 8, rows * cols / 4);
            for (int k = 0; k < count; k++) {
                int row = randomInt(rows);
                int col = randomInt(cols);
                try {
                    Animal animal = prototype.getClass().getDeclaredConstructor().newInstance();
                    if (animal != null) {
                        island.getMap()[row][col].addAnimal(animal);
                    } else {
                        System.err.println("Ошибка: создан null для " + prototype.getClass().getSimpleName());
                    }

                } catch (Exception e) {
                    System.err.println("Ошибка создания животного: " + prototype.getClass().getSimpleName()
                            + ": " + e.getMessage());
                }
            }
        }
        for (Animal prototype : ANIMAL_PROTOTYPES_HERBIVORE) {
            int count = random(rows * cols / 4, rows * cols);
            for (int k = 0; k < count; k++) {
                int row = randomInt(rows);
                int col = randomInt(cols);
                try {
                    Animal animal = prototype.getClass().getDeclaredConstructor().newInstance();
                    if (animal != null) {
                        island.getMap()[row][col].addAnimal(animal);
                    } else {
                        System.err.println("Ошибка: создан null для " + prototype.getClass().getSimpleName());
                    }

                } catch (Exception e) {
                    System.err.println("Ошибка создания животного: " + prototype.getClass().getSimpleName()
                            + ": " + e.getMessage());
                }
            }
        }


        int plantCount = random(rows * cols / 2, rows * cols);
        for (int k = 0; k < plantCount; k++) {
            int row = randomInt(rows);
            int col = randomInt(cols);
            island.getMap()[row][col].getPlants().add(new Plant(50.0, 0.1));
        }
    }
}

