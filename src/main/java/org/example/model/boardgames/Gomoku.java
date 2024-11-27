package org.example.model.boardgames;

import java.util.concurrent.TimeUnit;

import org.example.model.cell.Cell;
import org.example.controller.player.Player;


public class Gomoku extends BoardGame {

    public Gomoku() {
      super(15,15,5, "Gomoku");
    }

    public void populateTable() {
        System.out.println(row + " " + col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    @Override
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

    @Override
    public int getSize() {
        return row;
    }


}
