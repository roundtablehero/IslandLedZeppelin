package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Herbivore;

public class Buffalo extends Herbivore {

    public Buffalo() {
        super(700, 10, 3, 100);
    }

    @Override
    public Animal createNew() {
        return new Buffalo();
    }
}
