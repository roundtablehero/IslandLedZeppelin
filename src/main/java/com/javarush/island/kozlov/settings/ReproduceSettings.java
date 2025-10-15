
package com.javarush.island.kozlov.settings;

public class ReproduceSettings {
    private final int cooldownTurns;
    private final double successChance;

    public ReproduceSettings(int cooldownTurns, double successChance) {
        this.cooldownTurns = cooldownTurns;
        this.successChance = successChance;
    }


    public int getCooldownTurns() {
        return cooldownTurns;
    }

    public double getSuccessChance() {
        return successChance;
    }
}
