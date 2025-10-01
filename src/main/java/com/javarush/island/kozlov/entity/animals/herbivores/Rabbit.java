package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Herbivore;

public class Rabbit extends Herbivore {

    public Rabbit() {
        super(2, 150, 2, 0.45);
    }

    @Override
    public Animal createNew() {
        return new Rabbit();
    }
}
