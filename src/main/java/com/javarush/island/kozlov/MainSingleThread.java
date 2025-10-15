    package com.javarush.island.kozlov; //version 1010.2025.1032

    import com.javarush.island.kozlov.simulation.SimulationController;

    public class MainSingleThread {
        public static void main(String[] args) {
            int logicalCores = Runtime.getRuntime().availableProcessors();
            System.out.println("Логических ядер: " + logicalCores);

            // Оценка физических ядер (приблизительно)
            int estimatedPhysical = logicalCores / 2;
            System.out.println("Оценка физических ядер: " + estimatedPhysical);

            // Оптимально для CPU-bound
            int optimalThreads = Math.max(estimatedPhysical, 4);
            System.out.println("Рекомендуемые потоки: " + optimalThreads);
            SimulationController controller = new SimulationController();
            controller.start();
        }
    }