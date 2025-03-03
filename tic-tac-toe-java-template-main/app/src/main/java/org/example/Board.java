package org.example;

public class Board {
    private char[] grid;

    public Board() {
        reset();
    }

    public void displayBoard() {
        System.out.println(" | " + grid[0] + " | " + grid[1] + " | " + grid[2] + " | ");
        System.out.println("-----------");
        System.out.println(" | " + grid[3] + " | " + grid[4] + " | " + grid[5] + " | ");
        System.out.println("-----------");
        System.out.println(" | " + grid[6] + " | " + grid[7] + " | " + grid[8] + " | ");
    }

    public boolean makeMove(int cell, char player) {
        if (cellFree(cell)) {
            grid[cell - 1] = player;
            return true;
        }
        return false;
    }

    public boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (grid[i * 3] == player && grid[i * 3 + 1] == player && grid[i * 3 + 2] == player)
                return true;
            if (grid[i] == player && grid[i + 3] == player && grid[i + 6] == player)
                return true;
        }
        return (grid[0] == player && grid[4] == player && grid[8] == player) ||
               (grid[2] == player && grid[4] == player && grid[6] == player);
    }

    public boolean isDraw() {
        for (char cell : grid) {
            if (cell >= '1' && cell <= '9') return false;
        }
        return true;
    }

    public boolean cellFree(int cell) {
        return cell >= 1 && cell <= 9 && grid[cell - 1] >= '1' && grid[cell - 1] <= '9';
    }

    public void reset() {
        grid = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    }
}
