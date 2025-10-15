package com.javarush.island.kozlov.behaviors;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.plants.Plant;
import com.javarush.island.kozlov.settings.FoodMap;
import com.javarush.island.kozlov.util.Rnd;
import com.javarush.island.kozlov.world.Cell;

import java.util.ArrayList;

public class OmnivoreEatingBehavior implements EatingBehavior {
    @Override
    public void eat(Cell cell, Animal self) {
        double eaten = 0;
        // Попытка съесть животное
        for (var prey : new ArrayList<>(cell.getAnimals())) {
            if (prey == self) continue;

            if (!FoodMap.canEat(self.getClass(), prey.getClass())) continue;

            int chance = FoodMap.getChance(self.getClass(), prey.getClass());
            if (chance > 0 && Rnd.random(0, 100) < chance) {
                cell.getAnimals().remove(prey);
                eaten = prey.getWeight();
                //System.out.println(self.getNameWithGender() + " съел " + prey.getNameWithGender());
                self.updateCurrentFoodValue(eaten);
                return;
            }
        }

        // Если не нашёл — ест растения
        if (!cell.getPlants().isEmpty()) {
            Plant plant = cell.getPlants().get(0);
            eaten = Math.min(self.getFoodNeeded(), plant.getWeight());
            plant.reduceWeight(eaten);
            //System.out.println(self.getNameWithGender() + " съел часть растения (" + eaten + ")" + "\uD83C\uDF3F");

            if (!plant.isAlive()) {
                cell.getPlants().remove(plant);
            }
        }
        else {
            self.updateCurrentFoodValue(eaten);
        }
    }
}
