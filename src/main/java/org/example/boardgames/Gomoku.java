package org.example.boardgames;

import java.util.concurrent.TimeUnit;

import org.example.cell.Cell;
import org.example.controller.player.Player;
import org.example.cell.State;


public class Gomoku extends BoardGame {

    public Gomoku() {
      super(15,15,5);
    }

    public void populateTable() {
        System.out.println(row + " " + col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void setOwner(int[] coordinates, Player player) {
        TimeUnit time = TimeUnit.MILLISECONDS;

        int row = coordinates[0];
        int col = coordinates[1];
        cells[row][col].setState(player.getState());
        try {
            time.sleep(400);
        } catch (InterruptedException e) {
        }
    }

    public int getSize() {
        return row;
    }


}
