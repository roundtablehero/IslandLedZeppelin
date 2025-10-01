package com.javarush.island.kozlov.entity.animals.predators;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Predator;

public class Bear extends Predator {

    public Bear() {
        super(500, 5, 2, 80);
    }

    @Override
    public Animal createNew() {
        return new Bear();
    }
}
