package org.example;

import org.example.player.ArtificialPlayer;
import org.example.player.HumanPlayer;
import org.example.player.Player;
import org.example.views.View;

public class Game {
    Player currentPlayer;
    Player firstPlayer;
    Player secondPlayer;
    TicTacToe ticTacToe;
    UserInteraction userInteraction;
    View view;

    public Game() {
        // Initialize players
        firstPlayer = new HumanPlayer(" X ", "Player 1");
        secondPlayer = new HumanPlayer(" O ", "Player 2");

        // Initialize game components
        view = new View();
        ticTacToe = new TicTacToe();
        userInteraction = new UserInteraction();
    }

    public void mainMenu() {
        String gameMode = userInteraction.mainMenu(); // Ask the user to select a game mode
        switch (gameMode) {
            case "1":
                break;

            case "2":
                secondPlayer = new ArtificialPlayer(" O ", "Awesome-O");
                break;

            case "3":
                firstPlayer = new ArtificialPlayer(" X ", "Awesome-O");
                secondPlayer = new ArtificialPlayer(" O ", "C-16");
                break;

            default:
                view.defaultMessage();
                mainMenu();
                break;
        }
    }

    public void startGame() {
        view.displayHomePage();
        ticTacToe.populateTable(); // Prepare the board
        mainMenu();
        // Configure players based on game mode

        currentPlayer = firstPlayer; // Set the starting player

        while (true) {
            try {
                view.displayBoard(ticTacToe.getCells()); // Display the board updated after each turn
                view.playerMessage(currentPlayer); // Display current player's turn

                int[] coordinates = currentPlayer.getCoordinates(ticTacToe);
                ticTacToe.setOwner(coordinates, currentPlayer);

                // Check if the game is over
                if (ticTacToe.checkGameOver(currentPlayer)) {
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
}
