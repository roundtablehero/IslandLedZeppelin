
package com.javarush.island.kozlov.map;

import com.javarush.island.kozlov.EmojiMapper;
import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.plants.Plant;
import com.javarush.island.kozlov.entity.animals.herbivores.Caterpillar;
import com.javarush.island.kozlov.entity.animals.herbivores.Duck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Island {

    public Cell[][] cells;

    private final int width = 2; //100
    private final int height = 2; //20

    // Инициализация поля (массив 2х2)
    public Island() {
        cells = new Cell[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        initialize();
    }

    // Начальная инициализация растений и животных
    private void initialize() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                for (int i = 0; i < random.nextInt(60); i++) {
                    cell.plants.add(new Plant());
                }
                //Тут добавляем животных

                cell.addAnimal(new Caterpillar());
                if (random.nextBoolean()) cell.addAnimal(new Duck());



                //if (random.nextBoolean()) cell.addAnimal(new Wolf());
                //if (random.nextBoolean()) cell.addAnimal(new Duck());
                //cell.addAnimal(new Caterpillar());
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public void processAllCells() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                processCell(cell);
            }
        }
    }

    public void processCell(Cell cell) {
        cell.cellLock.lock();
        try {
            // First pass: Collect actions
            List<Animal> toRemove = new ArrayList<>();
            List<Animal> toAdd = new ArrayList<>();

            for (Animal animal : new ArrayList<>(cell.animals)) { // Iterate over a copy
                animal.eat(cell);
                if (animal.getCurrentSaturation() >= animal.getFoodNeeded() / 2) {
                    animal.reproduce(cell);
                    if (cell.animals.size() < animal.getMaxPerCell()) { // Используем геттер
                        toAdd.add(animal.createNew()); // Вызываем createNew() через animal
                    }
                }
                animal.move(cell, this);
                if (animal.isDead()) {
                    toRemove.add(animal);
                }
            }

            // Second pass: Apply modifications
            cell.animals.removeAll(toRemove);
            cell.animals.addAll(toAdd);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cell.cellLock.unlock();
        }
    }

    public void printStatistics() {
        Map<Class<?>, Long> counts = new HashMap<>();

        long totalPlants = 0;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.cellLock.lock();
                try {
                    totalPlants += cell.plants.size();
                    for (Animal animal : cell.animals) {
                        counts.merge(animal.getClass(), 1L, Long::sum);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cell.cellLock.unlock();
                }
            }
        }

        System.out.println("Такт: " + System.currentTimeMillis());
        System.out.println("\uD83C\uDF31 " + "Растения: " + totalPlants);
        counts.forEach((type, count) ->
                System.out.println(EmojiMapper.getEmoji((Class<? extends Animal>) type) + " " + type.getSimpleName() + ": " + count));

    }
}
