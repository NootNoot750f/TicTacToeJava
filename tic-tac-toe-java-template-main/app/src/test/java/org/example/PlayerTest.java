package org.example;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    void testGetLetter() {
        Player player = new Player('X');
        assertEquals('X', player.getLetter());
    }

    @Test
    void testGetMoveValidInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("3\n".getBytes());
        System.setIn(in);
        Player player = new Player('O');
        assertEquals(3, player.getMove());
    }

    //These tests simulate a person playing the game by using ByteArrayInputStream which mimic the input of a human
    // and by using System.setin which temporarily replace the keyboard input with the computer's test input
}
