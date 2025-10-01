package com.javarush.island.kozlov.entity.animals.predators;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Predator;

public class Fox extends Predator {

    public Fox() {
        super(8, 30, 2, 2);
    }

    @Override
    public Animal createNew() {
        return new Fox();
    }
}
