package com.javarush.island.kozlov.entity.animals.herbivores;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Herbivore;

public class Mouse extends Herbivore {
    public Mouse() {
        super(0.05, 500, 1, 0.01);
    }

    @Override
    public Animal createNew() {
        return new Mouse();
    }
}
