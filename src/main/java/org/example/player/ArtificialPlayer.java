package org.example.player;

import org.example.TicTacToe;

import java.util.Random;

public class ArtificialPlayer extends Player {

    private static final Random random = new Random();

    public ArtificialPlayer(String representation, String name) {
        super(representation, name);
    }

    public int randomRow() {
        return random.nextInt(3);
    }

    public int randomCol() {
        return random.nextInt(3);
    }

    @Override
    public int[] provideCoordinates(TicTacToe game) {

        int row, col;

        do {
            row = randomRow();
            col = randomCol();

        } while (!game.getCell(row, col).getRepresentation().equals("   ")); // Ensure AI picks an empty cell

        return new int[]{row, col};
    }
}
