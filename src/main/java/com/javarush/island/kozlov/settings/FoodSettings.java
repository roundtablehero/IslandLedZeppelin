package com.javarush.island.kozlov.settings;

    public class FoodSettings {
        private final double maxFoodMultiplier; // например, еды на 3 хода

        // setter, чтобы можно было менять перед игрой
        public FoodSettings(double maxFoodMultiplier) {
            this.maxFoodMultiplier = maxFoodMultiplier;
        }

        // getter
        public double getMaxFoodMultiplier() {
            return maxFoodMultiplier;
        }
    }


