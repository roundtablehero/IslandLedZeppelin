package com.javarush.island.kozlov.behaviors;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.world.Cell;

public interface EatingBehavior {
    void eat(Cell cell, Animal self);
}
