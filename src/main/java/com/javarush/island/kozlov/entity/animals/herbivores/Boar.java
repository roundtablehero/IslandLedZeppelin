package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.behaviors.OmnivoreEatingBehavior;
import com.javarush.island.kozlov.entity.animals.Herbivore;
import com.javarush.island.kozlov.world.Cell;
import com.javarush.island.kozlov.world.Island;

public class Boar extends Herbivore {
    public Boar() {
        super(400, 50, 2, 50);
        setEatingBehavior(new OmnivoreEatingBehavior());
    }

    @Override
    public void move(Island island, int row, int col) {
        this.move(island, island.getMap()[row][col], this, row, col);
    }

    @Override
    public void canReproduce(Cell cell) {
        this.reproduce(cell, this, Boar.class);
    }
}
