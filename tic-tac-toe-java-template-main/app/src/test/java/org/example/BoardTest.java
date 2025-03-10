package org.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testMakeMoveValid() {
        assertTrue(board.makeMove(1, 'X'));
        assertFalse(board.makeMove(1, 'O')); 
    }

    @Test
    void testMakeMoveInvalid() {
        assertFalse(board.makeMove(10, 'X'));
        assertFalse(board.makeMove(0, 'O'));
    }

    @Test
    void testCheckWinRow() {
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        board.makeMove(3, 'X');
        assertTrue(board.checkWin('X'));
    }

    @Test
    void testCheckWinColumn() {
        board.makeMove(1, 'O');
        board.makeMove(4, 'O');
        board.makeMove(7, 'O');
        assertTrue(board.checkWin('O'));
    }

    @Test
    void testCheckWinDiagonal() {
        board.makeMove(1, 'X');
        board.makeMove(5, 'X');
        board.makeMove(9, 'X');
        assertTrue(board.checkWin('X'));
    }

    @Test
    void testDraw() {
        board.makeMove(1, 'X');
        board.makeMove(2, 'O');
        board.makeMove(3, 'X');
        board.makeMove(4, 'X');
        board.makeMove(5, 'O');
        board.makeMove(6, 'X');
        board.makeMove(7, 'O');
        board.makeMove(8, 'X');
        board.makeMove(9, 'O');
        assertTrue(board.isDraw());
    }

    @Test
    void testCellFree() {
        assertTrue(board.cellFree(1));
        board.makeMove(1, 'X');
        assertFalse(board.cellFree(1));
    }

    @Test
    void testReset() {
        board.makeMove(1, 'X');
        board.reset();
        assertTrue(board.cellFree(1));
    }
}
