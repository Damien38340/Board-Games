package org.example.boardgames;

import java.util.concurrent.TimeUnit;

import org.example.cell.Cell;
import org.example.cell.State;
import org.example.player.Player;
import org.example.views.View;

public class ConnectFour extends BoardGame {


    public ConnectFour() {
        this.row = 6;
        this.col = 7;
        this.cells = new Cell[row][col];
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
        while (!isOutOfBounds(i, j) && checkNextCellForEquals(i - 1, j)) {
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

    public boolean checkGameOver(Player player) {
        int[] successiveTiles = {0, 0, 0, 0}; //Row, Col, Diag, AntiDiag

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < 4; k++) {
                    if (checkHorizontalWin(i, j, k)) {
                        successiveTiles[0] += 1;
                    }
                    if (checkVerticalWin(i, j, k)) {
                        successiveTiles[1] += 1;
                    }
                    if (checkDiagonalWin(i, j, k)) {
                        successiveTiles[2] += 1;
                    }
                    if (checkAntiDiagonalWin(i, j, k)) {
                        successiveTiles[3] += 1;
                    }
                }
                for( int x = 0; x < 4; x++ ) {
                    if(successiveTiles[x] == 3) {
                        return true;
                    }
                    successiveTiles[x] = 0; // resets win condition
                }

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
            cells[i - 1][j].setState(State.EMPTY);
        }
    }

    public int getSize(){
        return row;
    }

    private boolean checkHorizontalWin(int i, int j, int k){
        return (!isOutOfBounds(i, j + k) && !checkForEmptyCell(i, j + k, cells) && cells[i][k].getRepresentation().equals(cells[i][k + 1].getRepresentation()));
    }

    private boolean checkVerticalWin(int i, int j, int k){
        return (!isOutOfBounds(i + k, j) && !checkForEmptyCell(i + k, j, cells) && cells[k][j].getRepresentation().equals(cells[k + 1][j].getRepresentation()));
    }

    private boolean checkDiagonalWin(int i, int j, int k){
        return (!isOutOfBounds(i + k + 1, j + k + 1) && !checkForEmptyCell(i + k, j + k, cells) && cells[i + k][j + k].getRepresentation().equals(cells[i + k + 1][j + k + 1].getRepresentation()));
    }

    private boolean checkAntiDiagonalWin(int i, int j, int k){
        return (!isOutOfBounds(i + k + 1, j - k - 1) && !checkForEmptyCell(i + k, j - k, cells) && cells[i + k][j - k].getRepresentation().equals(cells[i + k + 1][j - k - 1].getRepresentation()));
    }
}
