package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Herbivore;

public class Horse extends Herbivore {

    public Horse() {
        super(400, 20, 4, 60);
    }

    @Override
    public Animal createNew() {
        return new Horse();
    }
}
