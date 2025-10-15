package com.javarush.island.kozlov.util;

import java.util.concurrent.ThreadLocalRandom;

public class Rnd {
    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static double randomDouble(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static int randomInt(int value) { return ThreadLocalRandom.current().nextInt(value);}

    public static boolean chance(double probability) {
        return ThreadLocalRandom.current().nextDouble() < probability;
    }
}
