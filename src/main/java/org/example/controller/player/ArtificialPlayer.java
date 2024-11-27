package org.example.controller.player;

import java.util.Random;

import org.example.model.boardgames.BoardGame;
import org.example.model.cell.State;

public class ArtificialPlayer extends Player {

    private static final Random random = new Random();

    public ArtificialPlayer(State state, String name) {
        super(state, name);
    }

    public int randomRow(int size) {
        return random.nextInt(size);
    }

    public int randomCol(int size) {
        return random.nextInt(size);
    }

    @Override
    public int[] provideCoordinates(BoardGame game) {

        int row, col;

        do {
            row = randomRow(game.getSize());
            col = randomCol(game.getSize());

        } while (game.getCell(row, col).getState() != State.EMPTY); // Ensure AI picks an empty cell

        return new int[] { row, col };
    }

}
