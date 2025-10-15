package com.javarush.island.kozlov.world;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.plants.Plant;
import com.javarush.island.kozlov.settings.AnimalEmojis;
import com.javarush.island.kozlov.util.Rnd;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    public synchronized List<Animal> getAnimals() {
        return animals;
    }

    public synchronized void addAnimal (Animal animal) {
        animals.add(animal);
    }

    public synchronized void removeAnimal (Animal animal) {
        animals.remove(animal);
    }

    public synchronized List<Plant> getPlants() {
        return plants;
    }

    public void growPlants() {
        for (Plant plant : new ArrayList<>(plants)) {
            plant.grow();
        }

        // шанс появления нового растения, если их нет
        if (plants.isEmpty()) {
            if (Rnd.chance(0.35)) { // 35% шанс нового растения
                plants.add(new Plant(1.0, 0.1));
            }
        }
    }

    //  Символьное представление для консольного рендера(c эмодзи)
    public String getSymbol() {
        if (!animals.isEmpty()) {
            Animal firstAnimal = animals.get(0);

            if (firstAnimal != null) {
                return AnimalEmojis.getEmoji(firstAnimal.getClass().getSimpleName());
            }
        }
        return plants.isEmpty() ? "." : "🌿";
    }
}
