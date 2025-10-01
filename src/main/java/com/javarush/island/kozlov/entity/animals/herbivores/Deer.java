package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Herbivore;

public class Deer extends Herbivore {

    public Deer() {
        super(300, 20, 4, 50);
    }

    @Override
    public Animal createNew() {
        return new Deer();
    }
}
