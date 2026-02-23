package com.game.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OutOfAreaExceptionTest {

    @Test
    void testExceptionMessage() {
        OutOfAreaException exception = new OutOfAreaException();
        assertEquals("Coordinates are out of the allowed area.", exception.getMessage());
    }

    @Test
    void testLogDetailedMessage() {
        assertDoesNotThrow(() -> OutOfAreaException.logDetailedMessage("Test detailed message"));
    }
}