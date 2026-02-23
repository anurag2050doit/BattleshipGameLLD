package com.game.validation;

import com.game.entity.CoordinatePair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipPositionValidatorTest {

    private final ShipPositionValidator validator = new ShipPositionValidator();

    @Test
    public void testValidCoordinates() {
        CoordinatePair start = new CoordinatePair(2, 3);
        CoordinatePair end = new CoordinatePair(2, 5);
        assertTrue(validator.validateCoordinates(start, end));
    }

    @Test
    public void testInvalidCoordinatesOutOfRange() {
        CoordinatePair start = new CoordinatePair(-1, 3);
        CoordinatePair end = new CoordinatePair(2, 5);
        assertFalse(validator.validateCoordinates(start, end));
    }

    @Test
    public void testInvalidCoordinatesNotAligned() {
        CoordinatePair start = new CoordinatePair(2, 3);
        CoordinatePair end = new CoordinatePair(3, 5);
        assertFalse(validator.validateCoordinates(start, end));
    }

    @Test
    public void testNullCoordinates() {
        assertFalse(validator.validateCoordinates(null, null));
    }

    @Test
    public void testValidCoordinatesOnBoundary() {
        CoordinatePair start = new CoordinatePair(0, 0);
        CoordinatePair end = new CoordinatePair(0, 9);
        assertTrue(validator.validateCoordinates(start, end));
    }

    @Test
    public void testInvalidCoordinatesExceedingBoundary() {
        CoordinatePair start = new CoordinatePair(10, 10);
        CoordinatePair end = new CoordinatePair(10, 11);
        assertFalse(validator.validateCoordinates(start, end));
    }

    @Test
    public void testSinglePointCoordinates() {
        CoordinatePair start = new CoordinatePair(4, 4);
        CoordinatePair end = new CoordinatePair(4, 4);
        assertTrue(validator.validateCoordinates(start, end));
    }

    @Test
    public void testDiagonalCoordinates() {
        CoordinatePair start = new CoordinatePair(1, 1);
        CoordinatePair end = new CoordinatePair(2, 2);
        assertFalse(validator.validateCoordinates(start, end));
    }
}