package com.javarush.island.kozlov.simulation;

import com.javarush.island.kozlov.map.Island;

public class Main {
    public static void main(String[] args) {
        Island island = new Island();

        for (int tick = 0; tick < 25; tick++) {
            System.out.println("Такт " + tick);
            island.processAllCells();
            island.printStatistics();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
