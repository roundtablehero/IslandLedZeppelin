package com.javarush.island.kozlov.simulation;

import com.javarush.island.kozlov.settings.FoodSettings;
import com.javarush.island.kozlov.settings.ReproduceSettings;
import com.javarush.island.kozlov.view.Renderer;
import com.javarush.island.kozlov.world.Island;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulation {
    private final Island island;
    private final Renderer renderer;
    private final FoodSettings foodSettings;
    private final ReproduceSettings reproduceSettings;
    private final SimulationSettings settings;

    public Simulation(Island island, Renderer renderer,
                      FoodSettings foodSettings, ReproduceSettings reproduceSettings,
                      SimulationSettings settings) {
        this.island = island;
        this.renderer = renderer;
        this.foodSettings = foodSettings;
        this.reproduceSettings = reproduceSettings;
        this.settings = settings;
    }

    public void run() {
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(3); // 3 потока для задач
        AtomicInteger currentStep = new AtomicInteger(1); // Счётчик шагов

        // Начальный рендер
        renderer.render(island, 1);

        // Задача роста растений (каждые stepDelay миллисекунд)
        scheduledPool.scheduleAtFixedRate(() -> {
            island.growPlants();
            //System.out.println("Рост растений на шаге: " + currentStep.get());
        }, 0, settings.getStepDelay() * 4 , TimeUnit.MILLISECONDS);

        // Задача жизненного цикла животных (каждые stepDelay миллисекунд)
        scheduledPool.scheduleAtFixedRate(() -> {
            if (currentStep.get() < settings.getMaxSteps()) {
                island.update(foodSettings, reproduceSettings);
                currentStep.incrementAndGet();
                renderer.render(island, currentStep.get());
                //System.out.println("Жизненный цикл на шаге: " + currentStep.get());
            } else {
                scheduledPool.shutdown();
            }
        }, 0, settings.getStepDelay(), TimeUnit.MILLISECONDS);

//        // Задача вывода статистики (каждые stepDelay миллисекунд)
//        scheduledPool.scheduleAtFixedRate(() -> {
//            int step = currentStep.get();
//            if (step <= settings.getMaxSteps()) {
//                renderer.render(island, step);
//                System.out.println("Рендер на шаге: " + step);
//            }
//        }, 0, settings.getStepDelay(), TimeUnit.MILLISECONDS);

        // Ждём завершения симуляции
        try {
            scheduledPool.awaitTermination(settings.getMaxSteps() * settings.getStepDelay() + 1000, TimeUnit.MILLISECONDS);
            System.out.println("Симуляция завершена после " + currentStep.get() + " шагов.");
        } catch (InterruptedException e) {
            System.err.println("Прерывание ожидания: " + e.getMessage());
            scheduledPool.shutdownNow();
        } catch (Exception e) {
            System.err.println("Ошибка на шаге: " + currentStep.get() + ": " + e.getMessage());
            e.printStackTrace();
            scheduledPool.shutdownNow();
        }
    }
}