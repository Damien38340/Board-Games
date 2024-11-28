package org.example.controller;

import org.example.model.boardgames.BoardGame;
import org.example.router.UserInteraction;
import org.example.model.boardgames.ConnectFour;
import org.example.model.boardgames.Gomoku;
import org.example.model.boardgames.TicTacToe;
import org.example.controller.player.ArtificialPlayer;
import org.example.controller.player.HumanPlayer;
import org.example.controller.player.Player;
import org.example.views.AsciiArt;
import org.example.views.View;
import org.example.model.cell.State;

public class Game {
    Player currentPlayer;
    Player firstPlayer;
    Player secondPlayer;
    UserInteraction userInteraction;
    View view;
    AsciiArt asciiArt;
    BoardGame ticTacToe;
    BoardGame gomoku;
    BoardGame connectFour;


    public Game() {
        // Initialize players
        firstPlayer = new HumanPlayer(State.X, "\u001B[35m Player 1 \u001B[0m");
        secondPlayer = new HumanPlayer(State.O, "\u001B[36m Player 2 \u001B[0m");

        // Initialize game components
        view = new View();
        asciiArt = new AsciiArt();
        userInteraction = new UserInteraction();
        ticTacToe = new TicTacToe();
        gomoku = new Gomoku();
        connectFour = new ConnectFour();

    }

    public void chooseGameMenu() {
        String chooseGame = userInteraction.chooseGame();
        view.clearScreen();
        switch (chooseGame) {
            case "1" -> startGame(ticTacToe);

            case "2" -> startGame(gomoku);

            case "3" -> startGame(connectFour);

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


    public void startGame(BoardGame game) {
        game.populateTable(); // Prepare the board
        chooseLogo(game);
        mainMenu();
        view.clearScreen();
        // Configure players based on game mode

        currentPlayer = firstPlayer; // Set the starting player

        while (true) {
            try {
                view.displayBoard(game.getCells()); // Display the board updated after each turn
                view.playerMessage(currentPlayer); // Display current player's turn

                int[] coordinates = currentPlayer.getCoordinates(game);
                game.setOwner(coordinates, currentPlayer);

                // Check if the game is over
                if (isOver(game)) {
                    view.displayBoard(game.getCells());
                    view.victoryMessage(currentPlayer);
                    asciiArt.victoryArt();
                    break;
                } else if (isDraw(game)) {
                    break;
                }

                // Switch players
                currentPlayer = (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer;

            } catch (Exception e) {
                System.err.println("An error occurred during the game: " + e.getMessage());
            }
        }
    }

    public boolean isOver(BoardGame game) {

        State currentState = currentPlayer.getState();

        for (int i = 0; i < game.getRow(); i++) {
            for (int j = 0; j < gomoku.getCol(); j++) {
                if (checkDirection(i, j, 0, 1, currentState, game)
                        || checkDirection(i, j, 1, 0, currentState, game)
                        || checkDirection(i, j, 1, 1, currentState, game)
                        || checkDirection(i, j, 1, -1, currentState, game)) return true;
            }
        }
        return false;
    }

    public boolean checkDirection(int i, int j, int u, int v, State currentState, BoardGame game) {
        for (int k = 0; k < game.getNbIdenticalCell(); k++) {
            if (!exist(i + u * k, j + v * k, game)) {
                return false;
            }
            if (game.getCell(i + u * k, j + v * k).getState() != currentState) {
                return false;
            }
        }
        return true;
    }

    public boolean exist(int i, int j, BoardGame game) {
        return (i >= 0 && i < game.getRow() && j >= 0 && j < game.getCol());
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
        asciiArt.drawArt();
        asciiArt.gameOverArt();
        return true;
    }

    public void chooseLogo(BoardGame game) {

        if (game instanceof TicTacToe) {
            asciiArt.ticTacToeLogo();
        } else if (game instanceof Gomoku) {
            asciiArt.gomokuLogo();
        } else if (game instanceof ConnectFour) {
            asciiArt.connectFourLogo();
        }
        else {
            view.defaultMessage();
        }
    }
}
