package org.example.boardgames;

import org.example.cell.Cell;
import org.example.cell.State;
import org.example.controller.player.Player;
import org.example.views.View;

public abstract class BoardGame {
    protected int row;
    protected int col;
    protected int nbIdenticalCell;
    protected Cell[][] cells;
    View view = new View();

    public BoardGame(int row , int col, int nbIdenticalCell) {
        this.row = row;
        this.col = col;
        this.nbIdenticalCell = nbIdenticalCell;

        this.cells = new Cell[row][col];
    }
    public boolean isOver(Player currentPlayer) {

        State currentState = currentPlayer.getState();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (checkDirection(i, j, 0, 1, currentState)
                        || checkDirection(i, j, 1, 0, currentState)
                        || checkDirection(i, j, 1, 1, currentState)
                        || checkDirection(i, j, 1, -1, currentState)) return true;
            }
        }
        return false;
    }

    public boolean checkDirection(int i, int j, int u, int v, State currentState) {
        for (int k = 0; k < nbIdenticalCell; k++) {
            if (!exist(i + u * k, j + v * k)) {
                return false;
            }
            if (getCell(i + u * k, j + v * k).getState() != currentState) {
                return false;
            }
        }
        return true;
    }

    public boolean exist(int i, int j) {
        return (i >= 0 && i < row && j >= 0 && j < col);
    }

    public boolean isDraw() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (getCells()[i][j].getState() == State.EMPTY) {
                    return false; // if one of the cells is empty, the game is not over yet
                }
            }
        }
        //If there's no empty cells left and no winner was declared, this is draw
        // Game over with no winner
        view.displayBoard(getCells()); // Show the final board
        view.drawMessage();
        view.gameOverMessage();
        return true;
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
