package org.example;

import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1, player2;

    public Game() {
        board = new Board();
        player1 = new Player('O');
        player2 = new Player('X');
    }

    public void start() {
        System.out.println("Welcome to Tic Tac Toe");
        Scanner scanner = new Scanner(System.in);
        char playAgain = 'y';
        while (playAgain == 'y') {
            board.reset();
            play();
            System.out.print("Play Again? y or n: ");
            playAgain = scanner.next().toLowerCase().charAt(0);
        }
        scanner.close();
    }

    private void play() {
        Player currentPlayer = player1;
        while (true) {
            board.displayBoard();
            int move = currentPlayer.getMove();
            if (isValidMove(move)) {
                board.makeMove(move, currentPlayer.getLetter());
                if (board.checkWin(currentPlayer.getLetter())) {
                    board.displayBoard();
                    System.out.println("Player " + currentPlayer.getLetter() + " won!");
                    break;
                }
                if (board.isDraw()) {
                    board.displayBoard();
                    System.out.println("It's a tie!");
                    break;
                }
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            } else {
                System.out.println("Invalid move, please enter a valid move.");
            }
        }
    }

    private boolean isValidMove(int move) {
        return board.cellFree(move);
    }
}
