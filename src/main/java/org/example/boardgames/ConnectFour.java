package org.example.boardgames;

import java.util.concurrent.TimeUnit;

import org.example.player.Cell;
import org.example.player.Player;
import org.example.views.View;

public class ConnectFour extends BoardGame {

    public ConnectFour() {
        view = new View();
        size = 7;
        cells = new Cell[6][7];
    }

    public void populateTable() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void setOwner(int coordinates, Player player) {
        View menu = new View();
        TimeUnit time = TimeUnit.MILLISECONDS;
        int i = 0;
        int j = coordinates;
        while (!isOutOfBounds(i, j) && checkNextCellForEquals(i - 1, j)) {
            animateConnectFour(i, j);
            cells[i][j].setRepresentation(player.getRepresentation());
            i++;

            try {
                time.sleep(400);
            } catch (InterruptedException e) {
            }
            menu.clearScreen();
            menu.displayBoard(this.cells);
        }
        menu.clearScreen();
    }

    public boolean checkGameOver(Player player) {

        return false;
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public boolean isOutOfBounds(int i, int j) {
        return (i < 0 || i > 5 || j < 0 || j > 6);
    }

    public boolean checkNextCellForEquals(int i, int j) {
        return i + 1 == 6 || cells[i + 1][j].getRepresentation().equals("   ");
    }

    public void animateConnectFour(int i, int j) {
        if (i != 0) {
            cells[i - 1][j].setRepresentation("   ");
        }
    }
}
