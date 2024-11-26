package org.example.boardgames;

import java.util.concurrent.TimeUnit;

import org.example.cell.Cell;
import org.example.cell.State;
import org.example.player.Player;
import org.example.views.View;

public class TicTacToe extends BoardGame {

    public TicTacToe() {
        this.col = 3;
        this.row = 3;
        this.cells = new Cell[row][col];
    }

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

    public boolean checkGameOverTicTacToe(Player currentPlayer) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                State currentState = cells[i][j].getState();

                // Skip empty cells
                if (currentState == State.EMPTY) {
                    continue;
                }

                // Check vertical win
                if (i >= 1 && i < row - 1 &&
                        currentState.equals(cells[i - 1][j].getState()) &&
                        currentState.equals(cells[i + 1][j].getState())) {
                    view.displayBoard(cells); // Show the final board
                    view.victoryMessage(currentPlayer);
                    return true;
                }

                // Check horizontal win
                if (j >= 1 && j < col - 1 &&
                        currentState.equals(cells[i][j - 1].getState()) &&
                        currentState.equals(cells[i][j + 1].getState())) {
                    view.displayBoard(cells); // Show the final board
                    view.victoryMessage(currentPlayer);
                    return true;
                }

                // Check main diagonal win
                if (i >= 1 && i < row - 1 && j >= 1 && j < col - 1 &&
                        currentState.equals(cells[i - 1][j - 1].getState()) &&
                        currentState.equals(cells[i + 1][j + 1].getState())) {
                    view.displayBoard(cells); // Show the final board
                    view.victoryMessage(currentPlayer);
                    return true;
                }

                // Check anti-diagonal win
                if (i >= 1 && i < row - 1 && j >= 1 && j < col - 1 &&
                        currentState.equals(cells[i - 1][j + 1].getState()) &&
                        currentState.equals(cells[i + 1][j - 1].getState())) {
                    view.displayBoard(cells); // Show the final board
                    view.victoryMessage(currentPlayer);
                    return true;
                }
            }
        }

        // Check if the board is full (no empty cells)
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (cells[i][j].getState() == State.EMPTY) {
                    return false; // if one of the cells is empty, the game is not over yet
                }
            }
        }

        // Game over with no winner
        view.displayBoard(cells); // Show the final board
        view.drawMessage();
        view.gameOverMessage();
        return true;
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
     *         board.
     */
    public Cell[][] getCells() {
        return this.cells;
    }

}
