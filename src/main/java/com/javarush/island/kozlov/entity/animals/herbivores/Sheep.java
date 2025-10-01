package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Herbivore;

public class Sheep extends Herbivore {

    public Sheep() {
        super(70, 140, 3, 15);
    }

    @Override
    public Animal createNew() {
        return new Sheep();
    }
}
