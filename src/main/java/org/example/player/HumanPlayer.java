package org.example.player;

import org.example.UserInteraction;
import org.example.boardgames.ConnectFour;
import org.example.boardgames.Gomoku;
import org.example.boardgames.TicTacToe;
import org.example.cell.State;

public class HumanPlayer extends Player {

    UserInteraction userInteraction = new UserInteraction();

    public HumanPlayer(State state, String name) {
        super(state, name);
    }

    @Override
    public int[] provideCoordinates(TicTacToe game) {
        int row = userInteraction.askingRowNumber() - 1;
        int col = userInteraction.askingColumnNumber() - 1;
        return new int[]{row, col};
    }

    @Override
    public int[] provideCoordinatesFromGomoku(Gomoku game) {
        int row = userInteraction.askingRowNumber() - 1;
        int col = userInteraction.askingColumnNumber() - 1;
        return new int[]{row, col};
    }

    @Override
    public int provideCoordinatesFromConnectFour(ConnectFour game) {
return userInteraction.getInputNumberFromPlayer();    }
}
