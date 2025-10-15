package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.behaviors.HerbivoreEatingBehavior;
import com.javarush.island.kozlov.entity.animals.Herbivore;
import com.javarush.island.kozlov.world.Cell;
import com.javarush.island.kozlov.world.Island;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super(2, 150, 2, 0.45);
        setEatingBehavior(new HerbivoreEatingBehavior());
    }

    @Override
    public void move(Island island, int row, int col) {
        this.move(island, island.getMap()[row][col], this, row, col);
    }

    @Override
    public void canReproduce(Cell cell) {
        this.reproduce(cell, this, Rabbit.class);
    }

}

