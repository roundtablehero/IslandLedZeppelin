package com.javarush.island.kozlov.behaviors;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.settings.ReproduceSettings;
import com.javarush.island.kozlov.world.Cell;

import java.util.Optional;
import java.util.Random;

public interface Reproducible {
    ReproduceSettings reproduce = new ReproduceSettings(3, 0.25);
    default <T extends Animal> void reproduce(Cell cell, T self, Class<T> clazz) {
        if (self.getReproduceCooldown() > 0) {
            self.decreaseCooldown();
            return;
        }

        Optional<T> partnerOpt = cell.getAnimals().stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .filter(a -> a.isMale() != self.isMale())
                .filter(a -> a.getReproduceCooldown() == 0)
                .findAny();

        if (partnerOpt.isPresent() && cell.getAnimals().size() < self.getMaxOnCell()) {
            T partner = partnerOpt.get();
            if (new Random().nextInt(100) < reproduce.getSuccessChance()) {
                try {
                    T child = clazz.getDeclaredConstructor().newInstance();
                    cell.getAnimals().add(child);
                    self.setReproduceCooldown(reproduce.getCooldownTurns());
                    partner.setReproduceCooldown(reproduce.getCooldownTurns());
                    //System.out.println("Родился новый " + child.getNameWithGender());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
