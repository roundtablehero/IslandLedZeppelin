package com.javarush.island.kozlov.world;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.settings.FoodSettings;
import com.javarush.island.kozlov.settings.ReproduceSettings;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Island {
    private Cell[][] map;

    public Island(int rows, int cols) {
        map = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getMap() {
        return map;
    }

    public void growPlants() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j].growPlants();
            }
        }
    }

    public void update(FoodSettings foodSettings, ReproduceSettings reproduceSettings) {
        ExecutorService pool = Executors.newFixedThreadPool(4); // Пул на 4 потока

        for (int i = 0; i < map.length; i++) {
            final int row = i; // final для лямбда-выражения
            pool.submit(() -> {
                for (int j = 0; j < map[0].length; j++) {
                    Cell cell = map[row][j];
                    var animalsCopy = new ArrayList<>(cell.getAnimals());

                    for (Animal animal : animalsCopy) {
                        if (animal == null) continue;
                        if (!cell.getAnimals().contains(animal)) continue;
                        if (animal.hasActed()) continue;

                        animal.eat(cell);
                        if (animal.dieIfStarving(cell)) continue;
                        animal.move(this, row, j);
                        animal.canReproduce(cell);
                        animal.setHasActed(true);
                    }
                }
            });
        }

        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.MINUTES); // Ожидание завершения
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Сбрасываем acted с проверкой на null
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (Animal a : map[i][j].getAnimals()) {
                    if (a != null) {
                        a.setHasActed(false);
                    } else {
                        System.err.println("Найден null в клетке [" + i + "][" + j + "]");
                    }
                }
            }
        }

        //growPlants();
    }
}