package com.game.validation;

import com.game.exception.InvalidBattleFieldSizeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameValidatorTest {

    @Test
    public void testValidBattleFieldSize() {
        assertTrue(GameValidator.isValidBattleFieldSize(4));
        assertTrue(GameValidator.isValidBattleFieldSize(10));
    }

    @Test
    public void testInvalidBattleFieldSizeNegative() {
        Exception exception = assertThrows(InvalidBattleFieldSizeException.class, () -> {
            GameValidator.isValidBattleFieldSize(-2);
        });
        assertEquals("Battlefield size must be a positive even number.", exception.getMessage());
    }

    @Test
    public void testInvalidBattleFieldSizeOdd() {
        Exception exception = assertThrows(InvalidBattleFieldSizeException.class, () -> {
            GameValidator.isValidBattleFieldSize(3);
        });
        assertEquals("Battlefield size must be a positive even number.", exception.getMessage());
    }

    @Test
    public void testInvalidBattleFieldSizeZero() {
        Exception exception = assertThrows(InvalidBattleFieldSizeException.class, () -> {
            GameValidator.isValidBattleFieldSize(0);
        });
        assertEquals("Battlefield size must be a positive even number.", exception.getMessage());
    }

    @Test
    public void testLargeValidBattleFieldSize() {
        assertTrue(GameValidator.isValidBattleFieldSize(1000));
    }

    @Test
    public void testLargeInvalidBattleFieldSizeOdd() {
        Exception exception = assertThrows(InvalidBattleFieldSizeException.class, () -> {
            GameValidator.isValidBattleFieldSize(1001);
        });
        assertEquals("Battlefield size must be a positive even number.", exception.getMessage());
    }
}