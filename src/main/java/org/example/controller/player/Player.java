package org.example.controller.player;

import org.example.model.boardgames.BoardGame;
import org.example.model.cell.State;
import org.example.views.View;

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

    public int[] getCoordinates(BoardGame game) {
        View view = new View();

        int[] coordinates;
        int row, col;

        while (true) {
            try {
                coordinates = provideCoordinates(game);
                row = coordinates[0];
                col = coordinates[1];

                if (row < 0 || row >= game.getSize() || col < 0 || col >= game.getSize()) {
                    view.invalidRowOrCol();
                }
                // Validate if the cell is empty
                else if (game.getCell(row, col).getState() != State.EMPTY) {
                    view.cellAlreadyOccupied();
                }
                // If valid, return the coordinates
                else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter valid numbers.");
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        return coordinates;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString(){
        return name;
    }
}
