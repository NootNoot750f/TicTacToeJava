package org.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void testIsValidMove() {
        assertTrue(game.isValidMove(1));
        game.getBoard().makeMove(1, 'X');
        assertFalse(game.isValidMove(1));
    }
}
