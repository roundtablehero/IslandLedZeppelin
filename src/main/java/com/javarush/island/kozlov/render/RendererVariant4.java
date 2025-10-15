package com.javarush.island.kozlov.render;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.settings.AnimalEmojis;
import com.javarush.island.kozlov.view.Renderer;
import com.javarush.island.kozlov.world.Island;

import java.util.HashMap;
import java.util.Map;

public class RendererVariant4 implements Renderer {
    @Override
    public void render(Island island, int step) {
        var map = island.getMap();
        int cols = map[0].length;

        int plants = 0;
        Map<String, Integer> animalCounts = new HashMap<>();

        //System.out.println("\n--- Шаг " + step + " ---");

        System.out.println("╔" + "═".repeat(cols * 3) + "╗");
        for (int i = 0; i < map.length; i++) {
            System.out.print("║ ");
            for (int j = 0; j < cols; j++) {
                String symbol = map[i][j].getSymbol();
                System.out.print(String.format("%-3s", symbol));

                if (symbol.equals("🌿")) {
                    plants += map[i][j].getPlants().size();
                } else {
                    for (Animal animal : map[i][j].getAnimals()) {
                        String species = animal.getClass().getSimpleName();
                        animalCounts.put(species, animalCounts.getOrDefault(species, 0) + 1);
                    }
                }
            }
            System.out.println("║");
        }
        System.out.println("╚" + "═".repeat(cols * 3) + "╝");

        StringBuilder stats = new StringBuilder("🌿: " + plants);
        for (Map.Entry<String, Integer> entry : animalCounts.entrySet()) {
            String species = entry.getKey();
            int count = entry.getValue();
            stats.append("   ").append(AnimalEmojis.getEmoji(species)).append(species).append(": ").append(count);
        }
        System.out.println(stats);
    }
}
