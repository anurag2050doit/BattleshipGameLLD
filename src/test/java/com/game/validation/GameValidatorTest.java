package com.game.validation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameValidatorTest {

    private final GameValidator gameValidator = new GameValidator();

    @Test
    public void testValidBattleFieldSize() {
        assertTrue(gameValidator.isValidBattleFieldSize(4));
        assertTrue(gameValidator.isValidBattleFieldSize(10));
    }

    @Test
    public void testInvalidBattleFieldSizeNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gameValidator.isValidBattleFieldSize(-2);
        });
        assertEquals("Battlefield size must be a positive even number.", exception.getMessage());
    }

    @Test
    public void testInvalidBattleFieldSizeOdd() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gameValidator.isValidBattleFieldSize(3);
        });
        assertEquals("Battlefield size must be a positive even number.", exception.getMessage());
    }

    @Test
    public void testInvalidBattleFieldSizeZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gameValidator.isValidBattleFieldSize(0);
        });
        assertEquals("Battlefield size must be a positive even number.", exception.getMessage());
    }

    @Test
    public void testBoundaryBattleFieldSize() {
        assertTrue(gameValidator.isValidBattleFieldSize(2));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gameValidator.isValidBattleFieldSize(1);
        });
        assertEquals("Battlefield size must be a positive even number.", exception.getMessage());
    }
}