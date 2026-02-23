package com.game.validation;

import com.game.entity.CoordinatePair;
import com.game.exception.OutOfAreaException;
import com.game.exception.PositionOverlapException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipPositionValidatorTest {

    @Test
    void testValidatePositionWithinBounds() {
        List<CoordinatePair> occupiedCoordinates = List.of(new CoordinatePair(2, 2));
        ShipPositionValidator validator = new ShipPositionValidator(occupiedCoordinates);
        CoordinatePair topLeft = new CoordinatePair(0, 0);
        CoordinatePair bottomRight = new CoordinatePair(1, 1);

        assertDoesNotThrow(() -> validator.validatePosition(topLeft, bottomRight, 5));
    }

    @Test
    void testValidatePositionOutOfBounds() {
        List<CoordinatePair> occupiedCoordinates = List.of(new CoordinatePair(2, 2));
        ShipPositionValidator validator = new ShipPositionValidator(occupiedCoordinates);
        CoordinatePair topLeft = new CoordinatePair(-1, 0);
        CoordinatePair bottomRight = new CoordinatePair(1, 1);

        OutOfAreaException exception = assertThrows(OutOfAreaException.class, () -> validator.validatePosition(topLeft, bottomRight, 5));
        assertEquals("Coordinates are out of the allowed area.", exception.getMessage());
    }

    @Test
    void testValidatePositionOverlap() {
        List<CoordinatePair> occupiedCoordinates = List.of(new CoordinatePair(1, 1));
        ShipPositionValidator validator = new ShipPositionValidator(occupiedCoordinates);
        CoordinatePair topLeft = new CoordinatePair(0, 0);
        CoordinatePair bottomRight = new CoordinatePair(2, 2);

        PositionOverlapException exception = assertThrows(PositionOverlapException.class, () -> validator.validatePosition(topLeft, bottomRight, 5));
        assertEquals("Position overlap detected. Please check the ship coordinates.", exception.getMessage());
    }
}