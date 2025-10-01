package com.javarush.island.kozlov.entity.animals.predators;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Predator;

public class Eagle extends Predator {

    public Eagle() {
        super(6, 20, 3, 1);
    }

    @Override
    public Animal createNew() {
        return new Eagle();
    }
}
