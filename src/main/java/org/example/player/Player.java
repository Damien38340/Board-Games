package org.example.player;

<<<<<<< Updated upstream
=======
import org.example.UserInteraction;
import org.example.boardgames.Gomoku;
>>>>>>> Stashed changes
import org.example.boardgames.TicTacToe;

public abstract class Player {
    private final String representation;
    private final String name;

    public Player(String representation, String name) {
        this.representation = representation;
        this.name = name;
    }

    public String getRepresentation() {
        return representation;
    }

    public String getName() {
        return name;
    }

    protected abstract int[] provideCoordinates(TicTacToe game);

    public int[] getCoordinates(TicTacToe game) {

        int[] coordinates;
        int row, col;

        while (true) {
            try {
                coordinates = provideCoordinates(game);
                row = coordinates[0];
                col = coordinates[1];

                if (row < 0 || row >= game.getSize() || col < 0 || col >= game.getSize()) {
                    System.out.println("Invalid row or column number. Please try again.");
                }
                // Validate if the cell is empty
                else if (!game.getCell(row, col).getRepresentation().equals("   ")) {
                    System.out.println("Cell is already occupied. Please choose an empty cell.");
                }
                // If valid, return the coordinates
                else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        return coordinates;
    }
<<<<<<< Updated upstream
=======

    public int[] getCoordinatesFromGomoku(Gomoku game) {

        int[] coordinates;
        int row, col;

        while (true) {
            try {
                coordinates = provideCoordinatesFromGomoku(game);
                row = coordinates[0];
                col = coordinates[1];

                if (row < 0 || row >= game.getSize() || col < 0 || col >= game.getSize()) {
                    System.out.println("Invalid row or column number. Please try again.");
                }
                // Validate if the cell is empty
                else if (!game.getCell(row, col).getRepresentation().equals("   ")) {
                    System.out.println("Cell is already occupied. Please choose an empty cell.");
                }
                // If valid, return the coordinates
                else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        return coordinates;
    }

    public int getCoordinatesFromConnectFour() {
        UserInteraction userInput = new UserInteraction();
        int column;
        while (true) {
            System.out.println("Please input a column number (1-7):");
            column = userInput.getInputNumberFromPlayer();
            if (column > 0 || column < 8) {
                return column - 1;
            }
        }
    }
>>>>>>> Stashed changes
}
