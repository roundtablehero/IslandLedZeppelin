package com.javarush.island.kozlov.map;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {
    public final int x;
    public final int y;

    public List<Animal> animals = new ArrayList<>();

    public List<Plant> plants = new ArrayList<>();

    public ReentrantLock cellLock = new ReentrantLock();

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addAnimal(Animal animal) {
        cellLock.lock();
        try {
            animals.add(animal);
        } finally {
            cellLock.unlock();
        }
    }

    public void removeAnimal (Animal animal) {
        cellLock.lock();
        try {
            animals.remove(animal);
        } finally {
            cellLock.unlock();
        }
    }
    // Метод для получения животных определённого типа
    // получили wildcard дженерик Wolf.class
    // подсчитали этих животных(волков)
    //
    public List<Animal> getAnimalsByType (Class<?> type) {
        List<Animal> result = new ArrayList<>();

        cellLock.lock();

        try {
            for (Animal animal : animals) {
                if (animal.getClass() == type) {
                    result.add(animal);
                }
            }
        } finally {
            cellLock.unlock();
        } return result;
    }
}
