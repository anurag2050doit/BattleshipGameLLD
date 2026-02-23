package com.game.validation;

import com.game.entity.CoordinatePair;
import com.game.exception.OutOfAreaException;
import com.game.exception.PositionOverlapException;
import java.util.List;

public class ShipPositionValidator {

    private final List<CoordinatePair> occupiedCoordinates;

    public ShipPositionValidator(List<CoordinatePair> occupiedCoordinates) {
        this.occupiedCoordinates = occupiedCoordinates;
    }

    public void validatePosition(CoordinatePair topLeft, CoordinatePair bottomRight, int battlefieldSize) throws OutOfAreaException, PositionOverlapException {
        // Check if the ship is within battlefield bounds
        if (topLeft.getX() < 0 || topLeft.getY() < 0 || bottomRight.getX() >= battlefieldSize || bottomRight.getY() >= battlefieldSize) {
            OutOfAreaException.logDetailedMessage("OutOfAreaException: Coordinates " + topLeft + " to " + bottomRight + " exceed battlefield size " + battlefieldSize);
            throw new OutOfAreaException();
        }

        // Check for overlap with existing ships
        for (CoordinatePair coordinate : occupiedCoordinates) {
            if (coordinate.isWithin(topLeft, bottomRight)) {
                PositionOverlapException.logDetailedMessage("PositionOverlapException: Overlap detected at " + coordinate);
                throw new PositionOverlapException();
            }
        }
    }
}