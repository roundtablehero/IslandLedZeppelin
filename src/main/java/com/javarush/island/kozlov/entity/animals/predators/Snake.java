package com.javarush.island.kozlov.entity.animals.predators;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Predator;

public class Snake extends Predator {

    public Snake() {
        super(15, 30, 1, 3);
    }

    @Override
    public Animal createNew() {
        return new Snake();
    }
}
