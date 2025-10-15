package com.javarush.island.kozlov.settings;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.herbivores.*;
import com.javarush.island.kozlov.entity.animals.predators.*;
import com.javarush.island.kozlov.entity.plants.Plant;

import java.util.HashMap;
import java.util.Map;

public class FoodMap {
    private static final Map<Class<? extends Animal>, Map<Class<?>, Integer>> dietMap = new HashMap<>();

    static {
        initializeDiet();
    }

    private static void initializeDiet() {
        dietMap.put(Wolf.class, Map.of(
                Horse.class, 10,
                Deer.class, 15,
                Rabbit.class, 60,
                Mouse.class, 80,
                Goat.class, 60,
                Sheep.class, 70,
                Boar.class, 15,
                Buffalo.class, 10,
                Duck.class, 40));

        dietMap.put(Boa.class, Map.of(
                Fox.class, 15,
                Rabbit.class, 20,
                Mouse.class, 40,
                Duck.class, 10));

        dietMap.put(Fox.class, Map.of(
                Rabbit.class, 70,
                Mouse.class, 90,
                Duck.class, 60,
                Caterpillar.class, 40));

        dietMap.put(Bear.class, Map.of(
                Boa.class, 80,
                Horse.class, 40,
                Deer.class, 80,
                Mouse.class, 90,
                Goat.class, 70,
                Sheep.class, 70,
                Boar.class, 50,
                Buffalo.class, 20,
                Duck.class, 10));

        dietMap.put(Eagle.class, Map.of (
                Fox.class, 10,
                Rabbit.class, 90,
                Mouse.class, 90,
                Duck.class, 80));

        dietMap.put(Horse.class, Map.of (
                Plant.class, 100));

        dietMap.put(Deer.class, Map.of(
                Plant.class, 100));

        dietMap.put(Rabbit.class, Map.of(
                Plant.class, 100));

        dietMap.put(Mouse.class, Map.of(
                Caterpillar.class, 90,
                Plant.class, 100));

        dietMap.put(Goat.class, Map.of(
                Plant.class, 100));

        dietMap.put(Sheep.class, Map.of(
                Plant.class, 100));

        dietMap.put(Boar.class, Map.of(
                Mouse.class, 50,
                Caterpillar.class, 90,
                Plant.class, 100));

        dietMap.put(Buffalo.class, Map.of(
                Plant.class, 100));

        dietMap.put(Duck.class, Map.of(
                Caterpillar.class, 90,
                Plant.class, 100));


        dietMap.put(Caterpillar.class, Map.of(Plant.class, 100));

    }

    public static int getChance(Class<? extends Animal> predatorClass, Class<?> preyClass) {
        Map<Class<?>, Integer> preyMap = dietMap.get(predatorClass);
        if (preyMap == null) return 0;
        return preyMap.getOrDefault(preyClass, 0);
    }

    public static boolean canEat(Class<? extends Animal> predatorClass, Class<?> preyClass) {
        Map<Class<?>, Integer> preyMap = dietMap.get(predatorClass);
        return preyMap != null && preyMap.containsKey(preyClass);
    }

}
