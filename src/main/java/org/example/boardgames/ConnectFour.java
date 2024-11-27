package org.example.boardgames;

import java.util.concurrent.TimeUnit;

import org.example.cell.Cell;
import org.example.cell.State;
import org.example.controller.player.Player;
import org.example.views.View;

public class ConnectFour extends BoardGame {


    public ConnectFour() {
  super(6,7,4);
    }

    public void populateTable() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void setOwner(int coordinates, Player player) {
        View menu = new View();
        TimeUnit time = TimeUnit.MILLISECONDS;
        int i = 0;
        int j = coordinates;
        while (exist(i, j) && checkNextCellForEquals(i - 1, j)) {
            animateConnectFour(i, j);
            cells[i][j].setState(player.getState());
            i++;

            try {
                time.sleep(100);
            } catch (InterruptedException e) {
            }
            menu.clearScreen();
            menu.displayBoard(this.cells);
        }
        menu.clearScreen();
    }

    public void animateConnectFour(int i, int j) {
        if (i != 0) {
            cells[i - 1][j].setState(State.EMPTY);
        }
    }

    public boolean checkNextCellForEquals(int i, int j) {
        return i + 1 == 6 || cells[i + 1][j].getState() == State.EMPTY;
    }

    public int getSize(){
        return row;
    }


}
