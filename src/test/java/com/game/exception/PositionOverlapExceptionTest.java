package com.game.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionOverlapExceptionTest {

    @Test
    void testExceptionMessage() {
        PositionOverlapException exception = new PositionOverlapException();
        assertEquals("Position overlap detected. Please check the ship coordinates.", exception.getMessage());
    }

    @Test
    void testLogDetailedMessage() {
        assertDoesNotThrow(() -> PositionOverlapException.logDetailedMessage("Test detailed message"));
    }
}