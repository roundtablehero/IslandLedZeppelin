package com.javarush.island.kozlov.behaviors;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.util.Rnd;
import com.javarush.island.kozlov.world.Cell;
import com.javarush.island.kozlov.world.Island;

public interface Movable {

    int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    default void move(Island island, Cell cell, Animal self, int row, int col) {
        int currentRow = row;
        int currentCol = col;

        for (int i = 0; i < self.getSpeed(); i++) {
            int[] dir = DIRECTIONS[Rnd.random(0, DIRECTIONS.length)];
            int newRow = currentRow + dir[0];
            int newCol = currentCol + dir[1];

            if (newRow >= 0 && newRow < island.getMap().length &&
                    newCol >= 0 && newCol < island.getMap()[0].length) {

                Cell currentCell = island.getMap()[currentRow][currentCol];
                Cell newCell = island.getMap()[newRow][newCol];

                currentCell.removeAnimal(self);
                newCell.addAnimal(self);

                currentRow = newRow;
                currentCol = newCol;

                //System.out.println(self.getNameWithGender() + " переместился в [" + newRow + "][" + newCol + "]");
            }
        }
    }
}
