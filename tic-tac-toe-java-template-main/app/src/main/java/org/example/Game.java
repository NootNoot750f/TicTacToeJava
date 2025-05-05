package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player playerX, playerO;
    private int xWins = 0, oWins = 0, ties = 0;
    private char nextStarter = 'X'; // X starts first game

    public Game() {
        board = new Board();
        playerX = new Player('X');
        playerO = new Player('O');
    }

    public void start() {
        System.out.println("Welcome to Tic Tac Toe");
        Scanner scanner = new Scanner(System.in);
        boolean keepPlaying = true;

        while (keepPlaying) {
            board.reset();
            System.out.println("\nStarting a new game!");
            Player first = (nextStarter == 'X') ? playerX : playerO;
            Player second = (nextStarter == 'X') ? playerO : playerX;
            char result = playRound(first, second);

            printStats();

            System.out.print("\nWould you like to play again (yes/no)? ");
            String input = scanner.next().trim().toLowerCase();
            keepPlaying = input.startsWith("y");

            // Decide next starter
            if (result == 'X') nextStarter = 'O';
            else if (result == 'O') nextStarter = 'X';
            // If tie, keep the same starter
        }

        System.out.println("\nWriting the game log to disk. Please see game.txt for the final statistics!");
        writeLogToFile();
    }

    private char playRound(Player first, Player second) {
        Player currentPlayer = first;
        while (true) {
            board.displayBoard();
            int move = currentPlayer.getMove(board);
            if (isValidMove(move)) {
                board.makeMove(move, currentPlayer.getLetter());

                if (board.checkWin(currentPlayer.getLetter())) {
                    board.displayBoard();
                    System.out.println("Player " + currentPlayer.getLetter() + " wins!");
                    if (currentPlayer.getLetter() == 'X') xWins++;
                    else oWins++;
                    return currentPlayer.getLetter();
                }

                if (board.isDraw()) {
                    board.displayBoard();
                    System.out.println("It's a tie!");
                    ties++;
                    return 'T';
                }

                currentPlayer = (currentPlayer == first) ? second : first;
            } else {
                System.out.println("Invalid move, please enter a valid move.");
            }
        }
    }

    private void printStats() {
        System.out.println("\nThe current log is:");
        System.out.printf("Player X Wins   %d%n", xWins);
        System.out.printf("Player O Wins   %d%n", oWins);
        System.out.printf("Ties            %d%n", ties);
    }

    private void writeLogToFile() {
        try (FileWriter writer = new FileWriter("game.txt")) {
            writer.write("Final Game Statistics:\n");
            writer.write(String.format("Player X Wins   %d%n", xWins));
            writer.write(String.format("Player O Wins   %d%n", oWins));
            writer.write(String.format("Ties            %d%n", ties));
        } catch (IOException e) {
            System.out.println("Error writing game log: " + e.getMessage());
        }
    }

    public boolean isValidMove(int move) {
        return board.cellFree(move);
    }


    public Board getBoard(){
        return board;
    }
}
