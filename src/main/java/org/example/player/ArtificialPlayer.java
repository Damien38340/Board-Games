package org.example.player;

import java.util.Random;

import org.example.boardgames.ConnectFour;
import org.example.boardgames.Gomoku;
import org.example.boardgames.TicTacToe;
import org.example.cell.State;

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
    public int[] provideCoordinates(TicTacToe game) {

        int row, col;

        do {
            row = randomRow(game.getSize());
            col = randomCol(game.getSize());

        } while (!game.getCell(row, col).getRepresentation().equals("   ")); // Ensure AI picks an empty cell

        return new int[] { row, col };
    }

    @Override
    public int[] provideCoordinatesFromGomoku(Gomoku game) {

        int row, col;

        do {
            row = randomRow(game.getSize());
            col = randomCol(game.getSize());

        } while (!game.getCell(row, col).getRepresentation().equals("   ")); // Ensure AI picks an empty cell

        return new int[] { row, col };
    }

    @Override
    public int provideCoordinatesFromConnectFour(ConnectFour game) {
        return random.nextInt(game.getSize()) + 1;
    }
}
