package org.example.model.boardgames;

import java.util.concurrent.TimeUnit;

import org.example.model.cell.Cell;
import org.example.controller.player.Player;

public class TicTacToe extends BoardGame {

    public TicTacToe() {
        super(3, 3, 3, "TicTacToe");
    }

    public void populateTable() {
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
            time.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public int getSize() {
        return row;
    }

}
