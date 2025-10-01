package com.javarush.island.kozlov.entity.animals;

import com.javarush.island.kozlov.map.Cell;
import com.javarush.island.kozlov.Direction;
import com.javarush.island.kozlov.map.Island;
import com.javarush.island.kozlov.api.action.Eatable;
import com.javarush.island.kozlov.api.action.Movable;
import com.javarush.island.kozlov.api.action.Reproducible;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements Movable, Reproducible, Eatable {
    protected double weight; // Вес особи в кг
    protected int maxPerCell; // Макс. кол-во животных одного вида в одной клетке
    protected int speed; // Клеток за ход (скорость)
    protected double foodNeeded; // Сколько еды нужно для полного насыщения
    protected double currentSaturation; // Текущая сытость (Уменьшается каждый такт)
    protected final Map<Class<? extends Eatable>, Integer> eatProbabilities; // Вероятность поедания других объектов

    public Animal(double weight, int maxPerCell, int speed, double foodNeeded) {
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
        this.currentSaturation = foodNeeded / 4; // Сытость на старте (50%)
        this.eatProbabilities = new HashMap<>();
    }

    public abstract void eat(Cell cell);

    @Override
    public void reproduce(Cell cell) {
        cell.cellLock.lock();
        try {
            long sameTypeCount = cell.animals.stream()
                    .filter(a -> a.getClass() == this.getClass())
                    .count();
            if (sameTypeCount >= 2 && cell.animals.size() < maxPerCell &&
                    ThreadLocalRandom.current().nextBoolean()) {
                Animal baby = createNew();
                cell.addAnimal(baby);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cell.cellLock.unlock();
        }
    }

    public abstract Animal createNew();

    public Direction chooseDirection() {
        return Direction.random();
    }

    @Override
    public void move(Cell currentCell, Island island) {
        for (int step = 0; step < speed; step++) {
            Direction direction = chooseDirection();
            int newX = currentCell.x + (direction == Direction.RIGHT ? 1 : direction == Direction.LEFT ? -1 : 0);
            int newY = currentCell.y + (direction == Direction.UP ? 1 : direction == Direction.DOWN ? -1 : 0);
            if (island.isValid(newX, newY)) {
                Cell newCell = island.getCell(newX, newY);
                if (newCell.getAnimalsByType(this.getClass()).size() < maxPerCell) {
                    currentCell.removeAnimal(this);
                    newCell.addAnimal(this);
                    break;
                }
            }
        }
    }

    public boolean isDead() {
        currentSaturation -= 2;

        if (currentSaturation < 0) {
            currentSaturation = 0;
            return true;
        }
        return false;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public double getCurrentSaturation() {
        return currentSaturation;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    public int getMaxPerCell() {
        return maxPerCell;
    }
}