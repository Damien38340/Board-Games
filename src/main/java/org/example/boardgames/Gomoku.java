package org.example.boardgames;

import java.util.concurrent.TimeUnit;

import org.example.cell.Cell;
import org.example.player.Player;
import org.example.cell.State;


public class Gomoku extends BoardGame {

    public Gomoku() {
        this.col = 15;
        this.row = 15;
        this.cells = new Cell[row][col];
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


    public boolean checkGameOverGomoku(Player currentPlayer) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                State current = cells[i][j].getState();

                // Skip empty cells
                if (current == State.EMPTY) {
                    continue;
                }

                if (checkVerticalWinGomoku(current, currentPlayer, i, j)) {
                    return true;
                }

                if (checkHorizontalWinGomoku(current, currentPlayer, i, j)) {
                    return true;
                }

                if (checkDiagonalWinGomoku(current, currentPlayer, i, j)) {
                    return true;
                }

                if (checkAntiDiagonalWinGomoku(current, currentPlayer, i, j)) {
                    return true;
                }
            }
        }

// Check if the board is full (no empty cells)
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (cells[i][j].

                        getState() == State.EMPTY) {
                    return false; // If one of the cells is empty, the game is not over yet
                }
            }
        }

        // Game over with no winner
        view.displayBoard(cells); // Show the final board
        view.drawMessage();
        view.gameOverMessage();
        return true;
    }

    public boolean checkVerticalWinGomoku(State current, Player currentPlayer, int i, int j) {
        if (i + 4 < row) {
            int count = 0;

            for (int k = 1; k <= 4; k++) {
                if (current.equals(cells[i + k][j].getState())) {
                    count++;
                }
            }
            if (count == 4) {
                view.displayBoard(cells); // Show the final board
                view.victoryMessage(currentPlayer);
                return true;
            }
        }
        return false;
    }

    public boolean checkHorizontalWinGomoku(State current, Player currentPlayer, int i, int j) {
        if (j + 4 < col) {
            int count = 0;

            for (int k = 1; k <= 4; k++) {
                if (current.equals(cells[i][j + k].getState())) {
                    count++;
                }
            }
            if (count == 4) {
                view.displayBoard(cells); // Show the final board
                view.victoryMessage(currentPlayer);
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagonalWinGomoku(State current, Player currentPlayer, int i, int j) {
        if (i + 4 < row && j + 4 < col) {
            int count = 0;

            for (int k = 1; k <= 4; k++) {
                if (current.equals(cells[i + k][j + k].getState())) {
                    count++;
                }
            }
            if (count == 4) {
                view.displayBoard(cells); // Show the final board
                view.victoryMessage(currentPlayer);
                return true;
            }
        }
        return false;
    }

    public boolean checkAntiDiagonalWinGomoku(State current, Player currentPlayer, int i, int j) {
        if (i + 4 < row && j - 4 >= 0) {
            int count = 0;

            for (int k = 1; k <= 4; k++) {
                if (current.equals(cells[i + k][j - k].getState())) {
                    count++;
                }
            }
            if (count == 4) {
                view.displayBoard(cells); // Show the final board
                view.victoryMessage(currentPlayer);
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return row;
    }

    /**
     * Retrieves the specific Cell object located at the given row and column
     * indices.
     *
     * @param row the row index of the desired cell (0-based).
     * @param col the column index of the desired cell (0-based).
     * @return the Cell object at the specified row and column.
     * @throws ArrayIndexOutOfBoundsException if the row or column indices are out
     *                                        of bounds.
     */
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    /**
     * Returns the entire 2D array of Cell objects representing the game board.
     *
     * @return a 2D array of Cell objects representing the current state of the
     * board.
     */
    public Cell[][] getCells() {
        return this.cells;
    }

}
