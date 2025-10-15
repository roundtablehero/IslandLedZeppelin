package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.behaviors.HerbivoreEatingBehavior;
import com.javarush.island.kozlov.entity.animals.Herbivore;
import com.javarush.island.kozlov.world.Cell;
import com.javarush.island.kozlov.world.Island;

public class Horse extends Herbivore {
    public Horse() {
        super(400, 20, 4, 60);
        setEatingBehavior(new HerbivoreEatingBehavior());
    }

    @Override
    public void move(Island island, int row, int col) {
        this.move(island, island.getMap()[row][col], this, row, col);
    }

    @Override
    public void canReproduce(Cell cell) {
        this.reproduce(cell, this, Horse.class);
    }
}
