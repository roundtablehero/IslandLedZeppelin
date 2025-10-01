package com.javarush.island.kozlov.simulation;

import com.javarush.island.kozlov.entity.plants.Plant;
import com.javarush.island.kozlov.map.Cell;
import com.javarush.island.kozlov.map.Island;

import java.util.concurrent.*;

    public class MainMultithread {
        public static void main(String[] args) {
            Island island = new Island(); // Создаём остров

            // Создаём пулы потоков
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3); // Для задач по расписанию

            // Задача для роста растений (раз в 5 секунд)
            Runnable plantGrowth = () -> {
                ExecutorService pool = Executors.newFixedThreadPool(10); // Пул для параллельной обработки клеток
                for (Cell[] row : island.cells) {
                    for (Cell cell : row) {
                        pool.submit(() -> {
                            cell.cellLock.lock();
                            try {
                                if (cell.plants.size() < 200) { // Макс растений в клетке
                                    ThreadLocalRandom random = ThreadLocalRandom.current();
                                    for (int i = 0; i < random.nextInt(3); i++) {
                                        cell.plants.add(new Plant());
                                    }
                                }
                            } finally {
                                cell.cellLock.unlock();
                            }
                        });
                    }
                }
                pool.shutdown();
                try {
                    pool.awaitTermination(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            scheduler.scheduleAtFixedRate(plantGrowth, 0, 5, TimeUnit.SECONDS);

            // Задача для действий животных (раз в 1 секунду)
            Runnable animalCycle = () -> {
                ExecutorService pool = Executors.newFixedThreadPool(20); // Пул для параллельной обработки клеток
                for (Cell[] row : island.cells) {
                    for (Cell cell : row) {
                        pool.submit(() -> island.processCell(cell)); // Вызываем processCell для каждой клетки
                    }
                }
                pool.shutdown();
                try {
                    pool.awaitTermination(1, TimeUnit.MINUTES);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            scheduler.scheduleAtFixedRate(animalCycle, 0, 1, TimeUnit.SECONDS);

            // Задача для вывода статистики (раз в 1 секунду)
            scheduler.scheduleAtFixedRate(island::printStatistics, 0, 1, TimeUnit.SECONDS);

            // Для теста: Останавливаем через 30 секунд
            try {
                Thread.sleep(30000);
                scheduler.shutdownNow();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

