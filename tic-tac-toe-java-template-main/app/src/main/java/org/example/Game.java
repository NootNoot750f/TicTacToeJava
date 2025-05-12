package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player playerX, playerO;
    private int xWins = 0, oWins = 0, ties = 0;
    private char nextStarter = 'X';

    public Game() {
        board = new Board();
        playerX = new Player('X');
        playerO = new Player('O');
    }

    public Board getBoard() {
        return board;
    }
    
    public boolean isValidMove(int position) {
        return board.cellFree(position);
    }
    

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe");
        System.out.println("Choose a game mode:");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs Computer");
        System.out.println("3. Computer vs Player");

        int choice = scanner.nextInt();

        if (choice == 1) {
            playPvP(scanner);
        } else if (choice == 2) {
            playPvC(scanner, false); // Human goes first
        } else if (choice == 3) {
            playPvC(scanner, true); // Computer goes first
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        // After playing
        System.out.println("\nWriting final stats to game.txt...");
        saveStatsToFile();
    }

    private void playPvP(Scanner scanner) {
        boolean keepPlaying = true;

        while (keepPlaying) {
            board.reset();
            System.out.println("\nStarting a new game...");

            Player first = (nextStarter == 'X') ? playerX : playerO;
            Player second = (first == playerX) ? playerO : playerX;

            char result = playRound(first, second);
            updateStats(result);
            showStats();

            System.out.print("Play again? (yes/no): ");
            String answer = scanner.next().toLowerCase();
            keepPlaying = answer.startsWith("y");

            nextStarter = (nextStarter == 'X') ? 'O' : 'X';
        }
    }

    private void playPvC(Scanner scanner, boolean cpuFirst) {
        boolean keepPlaying = true;

        while (keepPlaying) {
            board.reset();
            System.out.println("\nStarting a new game...");

            Player human = cpuFirst ? playerO : playerX;
            ComputerPlayer cpu = new ComputerPlayer(cpuFirst ? 'X' : 'O');

            Player current = (nextStarter == cpu.getLetter()) ? cpu : human;

            char result = playRoundWithCPU(current, human, cpu);
            updateStats(result);
            showStats();

            System.out.print("Play again? (yes/no): ");
            String answer = scanner.next().toLowerCase();
            keepPlaying = answer.startsWith("y");

            nextStarter = (nextStarter == 'X') ? 'O' : 'X';
        }
    }

    private char playRound(Player p1, Player p2) {
        Player current = p1;

        while (true) {
            board.displayBoard();
            int move = current.getMove(board);

            if (!board.cellFree(move)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            board.makeMove(move, current.getLetter());

            if (board.checkWin(current.getLetter())) {
                board.displayBoard();
                System.out.println("Player " + current.getLetter() + " wins!");
                return current.getLetter();
            }

            if (board.isDraw()) {
                board.displayBoard();
                System.out.println("It's a tie!");
                return 'T';
            }

            current = (current == p1) ? p2 : p1;
        }
    }

    private char playRoundWithCPU(Player current, Player human, ComputerPlayer cpu) {
        while (true) {
            board.displayBoard();
            int move;

            if (current == human) {
                move = human.getMove(board);
                if (!board.cellFree(move)) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
            } else {
                move = cpu.getMove(board);
                System.out.println("Computer chooses: " + move);
            }

            board.makeMove(move, current.getLetter());

            if (board.checkWin(current.getLetter())) {
                board.displayBoard();
                System.out.println("Player " + current.getLetter() + " wins!");
                return current.getLetter();
            }

            if (board.isDraw()) {
                board.displayBoard();
                System.out.println("It's a tie!");
                return 'T';
            }

            current = (current == human) ? cpu : human;
        }
    }

    private void updateStats(char result) {
        if (result == 'X') xWins++;
        else if (result == 'O') oWins++;
        else ties++;
    }

    private void showStats() {
        System.out.println("\nGame Stats:");
        System.out.println("Player X wins: " + xWins);
        System.out.println("Player O wins: " + oWins);
        System.out.println("Ties: " + ties);
    }

    private void saveStatsToFile() {
        try (FileWriter writer = new FileWriter("game.txt")) {
            writer.write("Final Game Stats:\n");
            writer.write("Player X wins: " + xWins + "\n");
            writer.write("Player O wins: " + oWins + "\n");
            writer.write("Ties: " + ties + "\n");
        } catch (IOException e) {
            System.out.println("Could not write stats: " + e.getMessage());
        }
    }
}
