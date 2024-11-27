package org.example.boardgames;

import java.util.concurrent.TimeUnit;

import org.example.cell.Cell;
import org.example.cell.State;
import org.example.controller.player.Player;

public class TicTacToe extends BoardGame {

    public TicTacToe() {
       super(3,3,3);    }

    public void populateTable() {
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
            time.sleep(1000);
        } catch (InterruptedException e) {
        }
    }


    public int getSize() {
        return row;
    }

}
