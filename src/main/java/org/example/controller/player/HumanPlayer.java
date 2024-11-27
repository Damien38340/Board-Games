package org.example.controller.player;

import org.example.model.boardgames.BoardGame;
import org.example.router.UserInteraction;
import org.example.model.boardgames.ConnectFour;
import org.example.model.boardgames.Gomoku;
import org.example.model.boardgames.TicTacToe;
import org.example.model.cell.State;

public class HumanPlayer extends Player {

    UserInteraction userInteraction = new UserInteraction();

    public HumanPlayer(State state, String name) {
        super(state, name);
    }

    @Override
    public int[] provideCoordinates(BoardGame game) {
        int row = 1;
        if (game instanceof Gomoku || game instanceof TicTacToe) {
            row = userInteraction.askingRowNumber() - 1;
        }
        int col = userInteraction.askingColumnNumber() - 1;
        return new int[]{row, col};
    }

}
