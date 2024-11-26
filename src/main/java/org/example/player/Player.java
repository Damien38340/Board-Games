package org.example.player;

import org.example.boardgames.ConnectFour;
import org.example.boardgames.Gomoku;
import org.example.boardgames.TicTacToe;
import org.example.cell.State;

public abstract class Player {


    State state;
    private final String name;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";

    public Player(State state, String name) {
        this.state = state;
        this.name = name;
    }

    public String getRepresentation() {
        return state.getValue();
    }

    public String getName() {
        return name;
    }

    protected abstract int[] provideCoordinatesFromTicTacToe(TicTacToe game);

    protected abstract int[] provideCoordinatesFromGomoku(Gomoku game);

    protected abstract int provideCoordinatesFromConnectFour(ConnectFour game);

    public int[] getCoordinatesFromTicTacToe(TicTacToe game) {

        int[] coordinates;
        int row, col;

        while (true) {
            try {
                coordinates = provideCoordinatesFromTicTacToe(game);
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

    public int getCoordinatesFromConnectFour(ConnectFour game) {
        int column = provideCoordinatesFromConnectFour(game);
        while (true) {
            if (column > 0 || column < 8) {
                return column - 1;
            }
        }
    }
    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return getRepresentation();
    }
}
