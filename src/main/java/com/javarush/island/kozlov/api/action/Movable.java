package com.javarush.island.kozlov.api.action;

import com.javarush.island.kozlov.map.Cell;
import com.javarush.island.kozlov.map.Island;

public interface Movable {
    void reproduce(Cell cell);

    void move(Cell currentCell, Island island);
}
