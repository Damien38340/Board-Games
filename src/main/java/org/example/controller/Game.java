package org.example.controller;

import org.example.model.boardgames.BoardGame;
import org.example.router.UserInteraction;
import org.example.model.boardgames.ConnectFour;
import org.example.model.boardgames.Gomoku;
import org.example.model.boardgames.TicTacToe;
import org.example.controller.player.ArtificialPlayer;
import org.example.controller.player.HumanPlayer;
import org.example.controller.player.Player;
import org.example.views.View;
import org.example.model.cell.State;

public class Game {
    Player currentPlayer;
    Player firstPlayer;
    Player secondPlayer;
    UserInteraction userInteraction;
    View view;

    public Game() {
        // Initialize players
        firstPlayer = new HumanPlayer(State.X, "\u001B[35m Player 1 \u001B[0m");
        secondPlayer = new HumanPlayer(State.O, "\u001B[36m Player 2 \u001B[0m");

        // Initialize game components
        view = new View();
        userInteraction = new UserInteraction();
    }

    public void chooseGameMenu() {
        String chooseGame = userInteraction.chooseGame();
        view.clearScreen();
        switch (chooseGame) {
            case "1" -> startTicTacToe();

            case "2" -> startGomoku();

            case "3" -> startConnectFour();

            default -> {
                view.defaultMessage();
                chooseGameMenu();
            }
        }
    }

    public void mainMenu() {
        String gameMode = userInteraction.mainMenu(); // Ask the user to select a game mode
        switch (gameMode) {
            case "1" -> {
            }

            case "2" -> secondPlayer = new ArtificialPlayer(State.O, "\u001B[36m O \u001B[0mAwesome-O");

            case "3" -> {
                firstPlayer = new ArtificialPlayer(State.X, "\u001B[35m Awesome-O \u001B[0m");
                secondPlayer = new ArtificialPlayer(State.O, "\u001B[36m C-16 \u001B[0m");
            }

            default -> {
                view.defaultMessage();
                mainMenu();
            }
        }
    }

    public void startTicTacToe() {
        BoardGame ticTacToe = new TicTacToe();
        ticTacToe.populateTable(); // Prepare the board
        view.displayTicTacToeLogo();
        mainMenu();
        view.clearScreen();
        // Configure players based on game mode

        currentPlayer = firstPlayer; // Set the starting player

        while (true) {
            try {
                view.displayBoard(ticTacToe.getCells()); // Display the board updated after each turn
                view.playerMessage(currentPlayer); // Display current player's turn

                int[] coordinates = currentPlayer.getCoordinatesFromTicTacToe(ticTacToe);
                ticTacToe.setOwner(coordinates, currentPlayer);

                // Check if the game is over
                if (ticTacToe.isOver(currentPlayer)) {
                    view.displayBoard(ticTacToe.getCells());
                    view.victoryMessage(currentPlayer);
                    break;
                }
                else if (isDraw(ticTacToe)){
                    break;
                }

                // Switch players
                currentPlayer = (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer;

            } catch (Exception e) {
                System.err.println("An error occurred during the game: " + e.getMessage());
            }
        }
    }

    public void startGomoku() {
        BoardGame gomoku = new Gomoku();
        gomoku.populateTable(); // Prepare the board

        view.displayGomokuLogo();
        mainMenu();
        view.clearScreen();

        // Configure players based on game mode

        currentPlayer = firstPlayer; // Set the starting player

        while (true) {
            try {
                view.displayBoard(gomoku.getCells()); // Display the board updated after each turn
                view.playerMessage(currentPlayer); // Display current player's turn

                int[] coordinates = currentPlayer.getCoordinatesFromGomoku(gomoku); // Needs to set a boardgame type
                gomoku.setOwner(coordinates, currentPlayer);

                // Check if the game is over
                if (gomoku.isOver(currentPlayer)) {
                    view.displayBoard(gomoku.getCells());
                    view.victoryMessage(currentPlayer);
                    break;
                }
                else if (isDraw(gomoku)){
                    break;
                }

                // Switch players
                currentPlayer = (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer;

            } catch (Exception e) {
                System.err.println("An error occurred during the game: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void startConnectFour() {
        BoardGame connectFour = new ConnectFour();
        connectFour.populateTable(); // Prepare the board
        view.displayConnectFourLogo();
        mainMenu();
        view.clearScreen();

        // Configure players based on game mode

        currentPlayer = firstPlayer; // Set the starting player
        while (true) {
            try {
                view.displayBoard(connectFour.getCells()); // Display the board updated after each turn
                view.playerMessage(currentPlayer); // Display current player's turn

                int[] coordinates = currentPlayer.getCoordinatesFromConnectFour(connectFour);
                connectFour.setOwner(coordinates, currentPlayer);

                // Check if the game is over
                if (connectFour.isOver(currentPlayer)) {
                    view.displayBoard(connectFour.getCells());
                    view.victoryMessage(currentPlayer);
                    break;
                }

                else if (isDraw(connectFour)){
                    break;
                }

                // Switch players
                currentPlayer = (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer;

            } catch (Exception e) {
                System.err.println("An error occurred during the game: " + e.getMessage());
            }
        }

    }
    public boolean isDraw(BoardGame game) {
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                if (game.getCells()[i][j].getState() == State.EMPTY) {
                    return false; // if one of the cells is empty, the game is not over yet
                }
            }
        }
        //If there's no empty cells left and no winner was declared, this is draw
        // Game over with no winner
        view.displayBoard(game.getCells()); // Show the final board
        view.drawMessage();
        view.gameOverMessage();
        return true;
    }
}
