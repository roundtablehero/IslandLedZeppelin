package com.javarush.island.kozlov.view;

import com.javarush.island.kozlov.world.Island;

public interface Renderer {
    void render(Island island, int step);
}
