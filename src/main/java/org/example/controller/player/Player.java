package org.example.controller.player;

import org.example.model.boardgames.BoardGame;
import org.example.model.cell.State;

public abstract class Player {


    State state;
    private final String name;

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

    protected abstract int[] provideCoordinates(BoardGame game);

    public int[] getCoordinatesFromTicTacToe(BoardGame game) {

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
                else if (game.getCell(row, col).getState() != State.EMPTY) {
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

    public int[] getCoordinatesFromGomoku(BoardGame game) {

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
                else if (game.getCell(row, col).getState() != State.EMPTY) {
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

    public int[] getCoordinatesFromConnectFour(BoardGame game) {
        int[] coordinates = provideCoordinates(game);
        while (true) {
            if (coordinates[0] > 0 || coordinates[0] < 8) {
                coordinates = new int[]{(coordinates[0] - 1), coordinates[1]};
                return coordinates;
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
