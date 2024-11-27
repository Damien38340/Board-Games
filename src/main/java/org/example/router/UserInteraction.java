package org.example.router;

import java.util.Scanner;

import org.example.views.View;

public class UserInteraction {
    View view = new View();
    private final Scanner sc;

    public UserInteraction() {
        sc = new Scanner(System.in);
    }

    public String chooseGame() {
        view.clearScreen();
        System.out.println("Choose your game");
        System.out.println("1. Tic Tac Toe");
        System.out.println("2. Gomoku");
        System.out.println("3. Connect Four");

        return sc.nextLine();
    }

    public String mainMenu() {

        System.out.println("1. Player 1 VS Player 2");
        System.out.println("2. Player 1 VS CPU");
        System.out.println("3. CPU VS CPU");

        return sc.nextLine();
    }

    public int askingRowNumber() {
        System.out.println("Enter row number (1, 2 or 3): ");
        return sc.nextInt();
    }

    public int askingColumnNumber() {
        System.out.println("Enter column number (1, 2 or 3): ");
        return sc.nextInt();
    }

    public int getInputNumberFromPlayer() {
        System.out.println("Please input a column number (1-7):");
        return sc.nextInt();
    }
}
