package org.example.boardgames;

import java.util.concurrent.TimeUnit;

import org.example.player.Cell;
import org.example.player.Player;
import org.example.views.View;

public class Gomoku extends BoardGame {

    public Gomoku() {
        view = new View();
        size = 15;
        cells = new Cell[size][size];
    }

    public void populateTable() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void setOwner(int[] coordinates, Player player) {
        TimeUnit time = TimeUnit.MILLISECONDS;

        int row = coordinates[0];
        int col = coordinates[1];
        cells[row][col].setRepresentation(player.getRepresentation());
        try {
            time.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    public boolean checkGameOverGomoku(Player currentPlayer) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                String current = cells[i][j].getRepresentation();

                // Skip empty cells
                if (current.equals("   ")) {
                    continue;
                }

                // Check vertical win
                if (i + 4 < size) {
                    int count = 0;

                    for (int k = 1; k <= 4; k++) {
                        if (current.equals(cells[i + k][j].getRepresentation())) {
                            count++;
                        }
                    }
                    if (count == 4) {
                        view.displayBoard(cells); // Show the final board
                        view.victoryMessage(currentPlayer);
                        return true;
                    }
                }

                // Check horizontal win
                if (j + 4 < size) {
                    int count = 0;

                    for (int k = 1; k <= 4; k++) {
                        if (current.equals(cells[i][j + k].getRepresentation())) {
                            count++;
                        }
                    }
                    if (count == 4) {
                        view.displayBoard(cells); // Show the final board
                        view.victoryMessage(currentPlayer);
                        return true;
                    }
                }

                // Check main diagonal win
                if (i + 4 < size && j + 4 < size) {
                    int count = 0;

                    for (int k = 1; k <= 4; k++) {
                        if (current.equals(cells[i + k][j + k].getRepresentation())) {
                            count++;
                        }
                    }
                    if (count == 4) {
                        view.displayBoard(cells); // Show the final board
                        view.victoryMessage(currentPlayer);
                        return true;
                    }
                }

                // Check anti-diagonal win
                if (i + 4 < size && j - 4 >= 0) {
                    int count = 0;

                    for (int k = 1; k <= 4; k++) {
                        if (current.equals(cells[i + k][j - k].getRepresentation())) {
                            count++;
                        }
                    }
                    if (count == 4) {
                        view.displayBoard(cells); // Show the final board
                        view.victoryMessage(currentPlayer);
                        return true;
                    }
                }
            }
        }

        // Check if the board is full (no empty cells)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j].

                        getRepresentation().equals("   ")) {
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

    public int getSize() {
        return size;
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
        return cells;
    }

}
