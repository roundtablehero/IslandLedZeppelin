package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.behaviors.HerbivoreEatingBehavior;
import com.javarush.island.kozlov.entity.animals.Herbivore;
import com.javarush.island.kozlov.world.Cell;
import com.javarush.island.kozlov.world.Island;

public class Goat extends Herbivore {
    public Goat() {
        super(60, 140, 3, 10);
        setEatingBehavior(new HerbivoreEatingBehavior());
    }

    @Override
    public void move(Island island, int row, int col) {
        this.move(island, island.getMap()[row][col], this, row, col);
    }

    @Override
    public void canReproduce(Cell cell) {
        this.reproduce(cell, this, Goat.class);
    }
}
