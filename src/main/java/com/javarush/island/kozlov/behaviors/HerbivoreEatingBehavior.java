package com.javarush.island.kozlov.behaviors;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.plants.Plant;
import com.javarush.island.kozlov.world.Cell;

public class HerbivoreEatingBehavior implements EatingBehavior {
    @Override
    public void eat(Cell cell, Animal self) {
        double eaten = 0;
        if (!cell.getPlants().isEmpty()) {
            Plant plant = cell.getPlants().get(0);
            eaten = Math.min(self.getFoodNeeded(), plant.getWeight());

            plant.reduceWeight(eaten);

            //System.out.println(self.getNameWithGender() + " съел часть растения (" + eaten + ")" + "\uD83C\uDF3F");

            if (!plant.isAlive()) {
                cell.getPlants().remove(plant);
            //    System.out.println("Растение полностью съедено и исчезло.");
            }
        }
        else {
            self.updateCurrentFoodValue(eaten);
        }
    }
}
