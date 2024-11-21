package org.example;

import org.example.boardgames.ConnectFour;
import org.example.boardgames.Gomoku;
import org.example.boardgames.TicTacToe;
import org.example.player.ArtificialPlayer;
import org.example.player.HumanPlayer;
import org.example.player.Player;
import org.example.views.View;

public class Game {
    Player currentPlayer;
    Player firstPlayer;
    Player secondPlayer;
    TicTacToe ticTacToe;
    Gomoku gomoku;
    UserInteraction userInteraction;
    View view;

    public Game() {
        // Initialize players
        firstPlayer = new HumanPlayer(" 🟩 ", "Player 1 🟩");
        secondPlayer = new HumanPlayer(" 🟥 ", "Player 2 🟥 ");

        // Initialize game components
        view = new View();
        ticTacToe = new TicTacToe();
        userInteraction = new UserInteraction();
    }

    public void chooseGameMenu() {
        String chooseGame = userInteraction.chooseGame();
        view.clearScreen();
        switch (chooseGame) {
            case "1":
                startTicTacToe();
                break;

            case "2":
                startGomoku();
                break;

            case "3":
                startConnectFour();
                break;

            default:
                view.defaultMessage();
                chooseGameMenu();
                break;
        }
    }

    public void mainMenu() {
        String gameMode = userInteraction.mainMenu(); // Ask the user to select a game mode
        switch (gameMode) {
            case "1" -> {
            }

            case "2" -> secondPlayer = new ArtificialPlayer(" 🤖 ", "Awesome-O 🤖");

            case "3" -> {
                firstPlayer = new ArtificialPlayer(" 🤖 ", "Awesome-O 🤖");
                secondPlayer = new ArtificialPlayer(" 👾 ", "C-16 👾");
            }

            default -> {
                view.defaultMessage();
                mainMenu();
            }
        }
    }

    public void startTicTacToe() {
        view.displayTicTacToeLogo();
        ticTacToe.populateTable(); // Prepare the board
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
        gomoku = new Gomoku();
        view.displayGomokuLogo();
        gomoku.populateTable(); // Prepare the board
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
        view.displayConnectFourLogo();
        connectFour.populateTable(); // Prepare the board
        mainMenu();
        view.clearScreen();

        // Configure players based on game mode

        currentPlayer = firstPlayer; // Set the starting player
        while (true) {
            try {
                view.displayBoard(connectFour.getCells()); // Display the board updated after each turn

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
