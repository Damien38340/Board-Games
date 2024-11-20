package org.example.boardgames;

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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == coordinates && cells[i][j].getRepresentation().equals("   ")) {
                    cells[i][j].setRepresentation(player.getRepresentation());
                }
            }
        }
    }

    public Cell[][] getCells() {
        return this.cells;
    }
}
