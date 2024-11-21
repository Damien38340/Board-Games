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
                time.sleep(100);
            } catch (InterruptedException e) {
            }
            menu.clearScreen();
            menu.displayBoard(this.cells);
        }
        menu.clearScreen();
    }

    public boolean checkGameOver(Player player) {
        int checkWinningRows = 0;
        int checkWinningCols = 0;
        int checkWinningDiagOne = 0;
        int checkWinningDiagTwo = 0;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 4; k++) {
                    // Checks rows and columns
                    if (!isOutOfBounds(i, j + k) && !checkForEmptyCell(i, j + k, cells)
                            && cells[i][k].getRepresentation().equals(cells[i][k + 1].getRepresentation())) {
                        checkWinningRows++;
                    }
                    if (!isOutOfBounds(i + k, j) && !checkForEmptyCell(i + k, j, cells)
                            && cells[k][j].getRepresentation().equals(cells[k + 1][j].getRepresentation())) {
                        checkWinningCols++;
                    }

                    // Checks diagonals
                    if (!isOutOfBounds(i + k + 1, j + k + 1) && !checkForEmptyCell(i + k, j + k, cells)
                            && cells[i + k][j + k].getRepresentation()
                                    .equals(cells[i + k + 1][j + k + 1].getRepresentation())) {
                        checkWinningDiagTwo++;
                    }

                    // Anti diagonal
                    if (!isOutOfBounds(i + k + 1, j - k - 1) && !checkForEmptyCell(i + k, j - k, cells)
                            && cells[i + k][j - k].getRepresentation()
                                    .equals(cells[i + k + 1][j - k - 1].getRepresentation())) {
                        checkWinningDiagOne++;
                    }

                    if (checkWinningRows == 3 || checkWinningCols == 3 || checkWinningDiagOne == 3
                            || checkWinningDiagTwo == 3) {
                        return true;
                    }
                }
                // System.out.println(checkWinningRows + ": " + cells[i][j].getRepresentation()
                // + "at index(" + i
                // + ", " + j + ")");
                checkWinningRows = 0;
                checkWinningCols = 0;
                checkWinningDiagOne = 0;
                checkWinningDiagTwo = 0;
            }
        }
        return false;
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public boolean isOutOfBounds(int i, int j) {
        return (i < 0 || i > 5 || j < 0 || j > 6);
    }

    public boolean checkForEmptyCell(int i, int j, Cell[][] cells) {
        return cells[i][j].getRepresentation().equals("   ");
    }

    public boolean checkNextCellForEquals(int i, int j) {
        return i + 1 == 6 || cells[i + 1][j].getRepresentation().equals("   ");
    }

    public void animateConnectFour(int i, int j) {
        if (i != 0) {
            cells[i - 1][j].setRepresentation("   ");
        }
    }

    public int getSize(){
        return size;
    }
}
