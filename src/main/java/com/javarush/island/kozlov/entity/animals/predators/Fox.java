package com.javarush.island.kozlov.entity.animals.predators;

import com.javarush.island.kozlov.behaviors.PredatorEatingBehavior;
import com.javarush.island.kozlov.entity.animals.Predator;
import com.javarush.island.kozlov.world.Cell;
import com.javarush.island.kozlov.world.Island;

public class Fox extends Predator {
    public Fox() {
        super(8, 30, 2, 2);
        setEatingBehavior(new PredatorEatingBehavior());
    }

    @Override
    public void move(Island island, int row, int col) {
        this.move(island, island.getMap()[row][col], this, row, col);
    }

    @Override
    public void canReproduce(Cell cell) {
        this.reproduce(cell, this, Fox.class);
    }
}
