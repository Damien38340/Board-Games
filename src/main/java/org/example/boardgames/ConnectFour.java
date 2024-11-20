package org.example.boardgames;

import java.util.concurrent.TimeUnit;

import org.example.player.Cell;
import org.example.views.View;
import org.example.player.Player;

public class ConnectFour extends BoardGame {

    public ConnectFour() {
        view = new View();
        size = 7;
        cells = new Cell[size - 1][size];
    }

    public void populateTable() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void setOwner(int coordinates, Player player) {
        View menu = new View();
        TimeUnit time = TimeUnit.SECONDS;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                menu.clearScreen();
                menu.displayBoard(this.cells);
                if (j == coordinates && cells[i][j].getRepresentation().equals("   ")) {
                    cells[i][j].setRepresentation(player.getRepresentation());
                }
                if (i != 0 && j == coordinates
                        && cells[i - 1][j].getRepresentation().equals(cells[i][j].getRepresentation())) {
                    cells[i - 1][j].setRepresentation("   ");
                }
                // if (j == coordinates &&
                // cells[i][j].getRepresentation().equals(cells[i][j].getRepresentation())) {
                // cells[i][j].setRepresentation(" ");
                // }

            }
            try {
                time.sleep(1);
            } catch (InterruptedException e) {
            }
        }
        menu.clearScreen();
    }

    public Cell[][] getCells() {
        return this.cells;
    }
}
