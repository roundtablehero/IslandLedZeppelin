package com.javarush.island.kozlov.behaviors;

import com.javarush.island.kozlov.settings.AnimalEmojis;
import com.javarush.island.kozlov.world.Cell;

public interface Alive {

    double getCurrentFoodValue();

    double getFoodNeeded();

    void setCurrentFoodValue(double value);

    boolean isMale();

    default boolean dieIfStarving(Cell cell, Object self) {
        if (getCurrentFoodValue() <= 0) {
            String emoji = AnimalEmojis.getEmoji(self.getClass().getSimpleName());
            String gender = isMale() ? "(M)" : "(F)";
            //System.out.println(emoji + self.getClass().getSimpleName() + gender + " умер от голода!" + "\uD83D\uDC80");
            cell.getAnimals().remove(self);
            return true;
        }
        return false;
    }

    default void updateFoodValue(double delta) {
        double newValue = Math.max(0, getCurrentFoodValue() + delta);
        setCurrentFoodValue(newValue);
    }
}
