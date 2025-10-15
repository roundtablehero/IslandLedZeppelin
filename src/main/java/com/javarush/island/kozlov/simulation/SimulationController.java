package com.javarush.island.kozlov.simulation;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.render.*;
import com.javarush.island.kozlov.settings.FoodSettings;
import com.javarush.island.kozlov.settings.ReproduceSettings;
import com.javarush.island.kozlov.view.Renderer;
import com.javarush.island.kozlov.world.Island;
import com.javarush.island.kozlov.world.PopulationInitializer;

import java.util.Scanner;

public class SimulationController {

    private final Simulation simulation;
    private final Renderer renderer;

    public SimulationController() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите максимальное кол-во шагов ('default' 100): ");
        int maxSteps = getInputOrDefault(scanner.nextLine(), 100);

        System.out.print("Введите задержку между шагами в миллисекундах ('default' 200): ");
        int stepDelay = getInputOrDefault(scanner.nextLine(), 200);

        System.out.print("Введите максимальное кол-во рядов острова ('default' 4): ");
        int maxRows = getInputOrDefault(scanner.nextLine(), 4);

        System.out.print("Введите максимальное кол-во столбцов острова ('default' 4): ");
        int maxCols = getInputOrDefault(scanner.nextLine(), 4);

        SimulationSettings simSettings = new SimulationSettings(maxSteps, stepDelay);
        FoodSettings foodSettings = new FoodSettings(3.0);
        ReproduceSettings reproduceSettings = new ReproduceSettings(3, 25);
        Animal.setFoodSettings(foodSettings);

        Island island = new Island(maxRows, maxCols);
        PopulationInitializer.fill(island);

        this.renderer = new RendererVariant4();
        this.simulation = new Simulation(island, renderer, foodSettings, reproduceSettings, simSettings);
    }

    private int getInputOrDefault(String input, int defaultValue) {
        if (input.trim().equalsIgnoreCase("default")) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод, используется значение по умолчанию: " + defaultValue);
            return defaultValue;
        }
    }

    public void start() {
        simulation.run();
    }
}
