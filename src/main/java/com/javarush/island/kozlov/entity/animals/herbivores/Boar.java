package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Herbivore;

public class Boar extends Herbivore {

    public Boar() {
        super(400, 50, 2, 50);
    }

    @Override
    public Animal createNew() {
        return new Boar();
    }
}
