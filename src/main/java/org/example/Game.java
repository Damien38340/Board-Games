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
            case "1" -> {
            }

            case "2" -> secondPlayer = new ArtificialPlayer(" O ", "Awesome-O");

            case "3" -> {
                firstPlayer = new ArtificialPlayer(" X ", "Awesome-O");
                secondPlayer = new ArtificialPlayer(" O ", "C-16");
            }

            default -> {
                view.defaultMessage();
                mainMenu();
            }
        }
    }

    public void startTicTacToe() {
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
            }
        }
    }

    public void startGomoku() {
 gomoku = new Gomoku();
        view.displayHomePage();
        gomoku.populateTable(); // Prepare the board
        mainMenu();
        // Configure players based on game mode

        currentPlayer = firstPlayer; // Set the starting player

        while (true) {
            try {
                view.displayBoard(gomoku.getCells()); // Display the board updated after each turn
                view.playerMessage(currentPlayer); // Display current player's turn

                int[] coordinates = currentPlayer.getCoordinatesFromGomoku(gomoku); //Needs to set a boardgame type
                gomoku.setOwner(coordinates, currentPlayer);

                // Check if the game is over
                if (gomoku.checkGameOver(currentPlayer)) {
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
        view.displayHomePage();
        connectFour.populateTable(); // Prepare the board
        mainMenu();
        // Configure players based on game mode

        currentPlayer = firstPlayer; // Set the starting player

        try {
            view.printBasicBoard(connectFour.getCells()); // Display the board updated after each turn
            // view.playerMessage(currentPlayer); // Display current player's turn

            // int[] coordinates = currentPlayer.getCoordinates(ticTacToe);
            // ticTacToe.setOwner(coordinates, currentPlayer);

            // // Check if the game is over
            // if (ticTacToe.checkGameOver(currentPlayer)) {
            // break;

            // // Switch players
            // currentPlayer = (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer;

        } catch (Exception e) {
            System.err.println("An error occurred during the game: " + e.getMessage());
        }

    }
}
