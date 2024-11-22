package org.example;

import org.example.boardgames.ConnectFour;
import org.example.boardgames.Gomoku;
import org.example.boardgames.TicTacToe;
import org.example.player.ArtificialPlayer;
import org.example.player.HumanPlayer;
import org.example.player.Player;
import org.example.views.View;
import org.example.cell.State;

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
        TicTacToe ticTacToe = new TicTacToe();
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

                int[] coordinates = currentPlayer.getCoordinates(ticTacToe);
                ticTacToe.setOwner(coordinates, currentPlayer);

                // Check if the game is over
                if (ticTacToe.checkGameOverTicTacToe(currentPlayer)) {
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
        Gomoku gomoku = new Gomoku();
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
                if (gomoku.checkGameOverGomoku(currentPlayer)) {
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
        ConnectFour connectFour = new ConnectFour();
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

                int coordinates = currentPlayer.getCoordinatesFromConnectFour(connectFour);
                connectFour.setOwner(coordinates, currentPlayer);

                // Check if the game is over
                if (connectFour.checkGameOver(currentPlayer)) {
                    view.displayBoard(connectFour.getCells());
                    view.victoryMessage(currentPlayer);
                    break;
                }

                // Switch players
                currentPlayer = (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer;

            } catch (Exception e) {
                System.err.println("An error occurred during the game: " + e.getMessage());
            }
        }

    }
}
