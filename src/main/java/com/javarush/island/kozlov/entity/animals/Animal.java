package com.javarush.island.kozlov.entity.animals;

import com.javarush.island.kozlov.behaviors.*;
import com.javarush.island.kozlov.settings.AnimalEmojis;
import com.javarush.island.kozlov.settings.FoodSettings;
import com.javarush.island.kozlov.world.Cell;
import com.javarush.island.kozlov.world.Island;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Animal implements Movable, Reproducible, Actable, Alive {
    protected double weight;
    protected int maxOnCell;
    protected int speed;
    protected double currentFoodValue;
    protected double maxFoodValue;
    protected double foodNeeded;
    protected boolean acted;
    protected boolean isMale;
    protected int reproduceCooldown;
    protected double foodEaten;
    protected EatingBehavior eatingBehavior;

    private static FoodSettings foodSettings = new FoodSettings(3.0);


    public Animal(double weight, int maxOnCell, int speed, double foodNeeded) {
        this.weight = weight;
        this.maxOnCell = maxOnCell;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
        this.maxFoodValue = foodNeeded * foodSettings.getMaxFoodMultiplier();
        this.currentFoodValue = maxFoodValue; // животное появляется сытым
        this.isMale = Math.random() < 0.5;
        //System.out.println(getNameWithGender() + " created with maxFoodValue: " + maxFoodValue + ", currentFoodValue: " + currentFoodValue);
    }

    public static void setFoodSettings(FoodSettings settings) {
        foodSettings = settings;
    }

    public boolean dieIfStarving(Cell cell) {
        if (currentFoodValue <= 0) {
            cell.getAnimals().remove(this);
            //System.out.println(getNameWithGender() + " умер от голода!💀");
            return true; // умер
        }
        return false; // жив
    }

    @Override
    public boolean hasActed() {
        return acted;
    }

    @Override
    public void setHasActed(boolean acted) {
        this.acted = acted;
    }

    @Override
    public double getCurrentFoodValue() {
        return currentFoodValue;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    @Override
    public void setCurrentFoodValue(double value) {
        this.currentFoodValue = value;
    }

    // Общий метод для названия с полом и эмодзи
    public String getNameWithGender() {
        return AnimalEmojis.getEmoji(getClass().getSimpleName()) + getClass().getSimpleName() + (isMale ? "(M)" : "(F)");
    }

    public abstract void move(Island island, int row, int col);

    public abstract void canReproduce(Cell cell);


    public boolean isMale() {
        return isMale;
    }

    public double getWeight() {
        return weight;
    }

    public int getSpeed() {
        return speed;
    }

    public int getReproduceCooldown() {
        return reproduceCooldown;
    }

    public void setReproduceCooldown(int turns) {
        reproduceCooldown = turns;
    }

    public void decreaseCooldown() {
        if (reproduceCooldown > 0) reproduceCooldown--;
    }

    public int getMaxOnCell() {
        return maxOnCell;
    }


    public void updateCurrentFoodValue(double eaten) {
        currentFoodValue += eaten;
        if (currentFoodValue > maxFoodValue) {
            currentFoodValue = maxFoodValue;
        }

        currentFoodValue -= foodNeeded / 6; // Уменьшаем на норму каждый шаг (делим на 6, для баланса)
        if (currentFoodValue < 0) { // А то умирали слишком быстро от голода
            currentFoodValue = 0;
        }
        currentFoodValue = roundToTwoDecimals(currentFoodValue);
        //System.out.println(getNameWithGender() + " food: " + currentFoodValue);
    }

    private double roundToTwoDecimals(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public void setEatingBehavior(EatingBehavior eatingBehavior) {
        this.eatingBehavior = eatingBehavior;
    }

    public void eat(Cell cell) {
        if (eatingBehavior != null) {
            eatingBehavior.eat(cell, this);
        }
        else {
            updateCurrentFoodValue(0); // ничего не съел
        }
    }
}
