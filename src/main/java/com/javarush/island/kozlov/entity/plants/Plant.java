package com.javarush.island.kozlov.entity.plants;

import com.javarush.island.kozlov.api.action.Eatable;

public class Plant implements Eatable {
    private double weight = 1.0;

    public double getWeight() {
        return weight;
    }
}
