package org.example;

public class TicTacToe {

    private final int size = 3;
    private final Cell[][] cells;
    private final Menu menu;

    public TicTacToe() {
        menu = new Menu();
        cells = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void display() {

        System.out.println("   1    2    3"); // Column headers
        System.out.println("  --------------");

        for (int i = 0; i < cells.length; i++) { // Use a traditional for loop to track row index
            System.out.print((i + 1) + " |"); // Print row number
            for (Cell cell : cells[i]) {
                System.out.print(cell.getRepresentation() + "|"); // Print cell contents
            }
            System.out.println(); // Move to the next line
            System.out.println("  --------------"); // Row separator
        }
    }

    public int[] getMoveFromPlayer() {

        int[] coordinates = new int[2];

        int row, col;

        while (true) {
            try {
                row = menu.askingRowNumber();
                col = menu.askingColumnNumber();
                coordinates[0] = row-1;
                coordinates[1] = col-1;

                if (row < 1 || row > size || col < 1 || col > size) {
                    System.out.println("Invalid row or column number");
                } else if (!cells[coordinates[0]][coordinates[1]].getRepresentation().equals("   ")) {
                    System.out.println("You must select an empty cell");
                } else {
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter whole numbers between 1 and 3");
            }
        }
        return coordinates;
    }

    public void setOwner(int[] coordinates, Player player) {
        int row = coordinates[0];
        int col = coordinates[1];
        cells[row][col].setRepresentation(player.getRepresentation());
    }
}
