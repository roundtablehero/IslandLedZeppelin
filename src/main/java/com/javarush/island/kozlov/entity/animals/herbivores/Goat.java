package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Herbivore;

public class Goat extends Herbivore {

    public Goat() {
        super(60, 140, 3, 10);
    }

    @Override
    public Animal createNew() {
        return new Goat();
    }
}
