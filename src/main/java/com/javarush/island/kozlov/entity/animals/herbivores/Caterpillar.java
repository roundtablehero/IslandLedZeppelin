package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Herbivore;

public class Caterpillar extends Herbivore {


    public Caterpillar() {
        super(0.01, 1000, 0, 0);
    }

    @Override
    public Animal createNew() {
        return new Caterpillar();
    }
}
