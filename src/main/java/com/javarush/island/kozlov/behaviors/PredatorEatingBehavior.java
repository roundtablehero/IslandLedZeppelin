package com.javarush.island.kozlov.behaviors;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.settings.FoodMap;
import com.javarush.island.kozlov.util.Rnd;
import com.javarush.island.kozlov.world.Cell;

import java.util.Iterator;

public class PredatorEatingBehavior implements EatingBehavior {
    @Override
    public void eat(Cell cell, Animal self) {
        double eaten = 0;
        Iterator<Animal> iterator = cell.getAnimals().iterator();
        while (iterator.hasNext()) {
            Animal prey = iterator.next();
            if (prey == self) continue;

            if (!FoodMap.canEat(self.getClass(), prey.getClass())) continue;

            int chance = FoodMap.getChance(self.getClass(), prey.getClass());

            if (chance > 0 && Rnd.random(0, 100) < chance) {
                iterator.remove();
                eaten = (prey.getWeight());
                //System.out.println(self.getNameWithGender() + " съел " + prey.getNameWithGender());
                break;
            }
        }
        self.updateCurrentFoodValue(eaten);
    }
}
