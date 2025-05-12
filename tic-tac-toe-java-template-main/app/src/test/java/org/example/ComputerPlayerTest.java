package org.example;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComputerPlayerTest {
    private ComputerPlayer cpu;
    private Board board;

    @BeforeEach
    void setUp() {
        cpu = new ComputerPlayer('X');
        board = new Board();
    }

    @Test
    void testPickCornerOnEmptyBoard() {
        int move = cpu.getMove(board);
        List<Integer> corners = Arrays.asList(1, 3, 7, 9);
        assertTrue(corners.contains(move), "Computer should choose a corner as first move");
    }

    @Test
    void testPickCenterOnSecondMove() {
        board.makeMove(1, 'O');
        if (board.cellFree(5)) {
            int move = cpu.getMove(board);
            assertEquals(5, move, "Computer should pick center on second move if it's free");
        }
    }

    @Test
    void testWinIfPossible() {

        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        board.makeMove(4, 'O');
        board.makeMove(5, 'O');

        int move = cpu.getMove(board);
        assertEquals(3, move, "Computer should choose winning move");
    }

}
