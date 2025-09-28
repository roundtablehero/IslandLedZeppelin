package com.javarush.island.kozlov;

import java.util.concurrent.ThreadLocalRandom;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public static Direction random() {
        return values()[ThreadLocalRandom.current().nextInt(values().length)];
    }


}
