package com.javarush.island.kozlov.entity.animals.predators;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.Predator;
import com.javarush.island.kozlov.entity.animals.herbivores.*;

public class Wolf extends Predator {
    public Wolf() {
        super(50, 30, 3, 8); //Вес: 50 кг, макс в клетке 30, скорость 3, еда 8 кг.

//        eatProbabilities.put(Rabbit.class, 60);
//        eatProbabilities.put(Boar.class, 15);
//        eatProbabilities.put(Buffalo.class, 10);
//        eatProbabilities.put(Deer.class, 15);
        eatProbabilities.put(Duck.class, 40);
//        eatProbabilities.put(Goat.class, 60);
//        eatProbabilities.put(Horse.class, 10);
//        eatProbabilities.put(Rabbit.class, 60);
//        eatProbabilities.put(Sheep.class, 70);


    }

    @Override
    public Animal createNew() {
        return new Wolf();
    }
}
