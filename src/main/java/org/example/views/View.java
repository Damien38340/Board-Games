package org.example.views;

import org.example.model.cell.Cell;
import org.example.controller.player.Player;

public class View {

    AsciiArt asciiArt = new AsciiArt();

    // Only on Linux
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void displayBoard(Cell[][] cells) {
        clearScreen();
        // Generate and display column headers dynamically
        System.out.print("     "); // Leading space for alignment
        for (int i = 0; i < cells[0].length; i++) {
            System.out.printf("%-5d", i + 1); // Adjust spacing as needed
        }
        System.out.println();

        // Top border
        System.out.print("  ");
        for (int i = 0; i < cells[0].length; i++) {
            System.out.print("====="); // Adjust dashes as needed
        }
        System.out.println();

        // Display rows with row numbers
        for (int i = 0; i < cells.length; i++) {
            System.out.print((i + 1) + "|"); // Print row number
            for (Cell cell : cells[i]) {
                System.out.printf("%-4s|", cell.getRepresentation()); // Print cell content with spacing
            }
            System.out.println(); // Move to the next line

            // Print row separator
            System.out.print("  ");
            for (int j = 0; j < cells[i].length; j++) {
                System.out.print("====="); // Adjust dashes as needed
            }
            System.out.println();
        }

    }

    public void playerMessage(Player currentPlayer) {
        System.out.println(currentPlayer.getName());
    }

    public void gameOverMessage() {
        asciiArt.gameOverArt();
    }

    public void victoryMessage(Player currentPlayer) {
        System.out.println("\t    " + currentPlayer.getName() + " won !!");
        asciiArt.victoryArt();
    }

    public void drawMessage() {
        asciiArt.drawArt();
    }

    public void defaultMessage() {
        System.out.println("\n Invalid choice, please select a valid option.");
    }

    public void invalidRowOrCol() {
        System.out.println("Invalid row or column number. Please try again.");
    }

    public void cellAlreadyOccupied() {
        System.out.println("Cell is already occupied. Please choose an empty cell.");
    }

}
