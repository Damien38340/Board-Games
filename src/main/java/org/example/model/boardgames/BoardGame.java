package org.example.model.boardgames;

import org.example.model.cell.Cell;
import org.example.controller.player.Player;


public abstract class BoardGame {
    protected String name;
    protected int row;
    protected int col;
    protected int nbIdenticalCell;
    protected Cell[][] cells;

    public BoardGame(int row , int col, int nbIdenticalCell, String name) {
        this.row = row;
        this.col = col;
        this.nbIdenticalCell = nbIdenticalCell;
        this.name = name;
        this.cells = new Cell[row][col];
    }

    public void populateTable() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cells[i][j] = new Cell();
            }
        }
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

    public abstract void setOwner(int[] coordinates, Player player);

    public abstract int getSize();

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getNbIdenticalCell() {
        return nbIdenticalCell;
    }
}
