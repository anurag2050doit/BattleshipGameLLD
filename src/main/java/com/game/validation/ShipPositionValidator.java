package com.game.validation;

import com.game.entity.CoordinatePair;
import com.game.exception.OutOfAreaException;
import com.game.exception.PositionOverlapException;

import java.util.ArrayList;
import java.util.List;

public class ShipPositionValidator {

    private static final int MIN_COORDINATE = 0;
    private final List<CoordinatePair> occupiedCoordinates;

    public ShipPositionValidator() {
        this.occupiedCoordinates = new ArrayList<>();
    }

    public ShipPositionValidator(List<CoordinatePair> occupiedCoordinates) {
        this.occupiedCoordinates = occupiedCoordinates;
    }

    public void validatePosition(CoordinatePair topLeft, CoordinatePair bottomRight, int battlefieldSize) throws OutOfAreaException, PositionOverlapException {
        if (topLeft.getX() < 0 || topLeft.getY() < 0 || bottomRight.getX() >= battlefieldSize || bottomRight.getY() < 0) {
            OutOfAreaException.logDetailedMessage("OutOfAreaException: Coordinates " + topLeft + " to " + bottomRight + " exceed battlefield size " + battlefieldSize);
            throw new OutOfAreaException();
        }
        for (CoordinatePair coordinate : occupiedCoordinates) {
            if (coordinate.isWithin(topLeft, bottomRight)) {
                PositionOverlapException.logDetailedMessage("PositionOverlapException: Overlap detected at " + coordinate);
                throw new PositionOverlapException();
            }
        }
    }

    public boolean validateCoordinates(CoordinatePair start, CoordinatePair end) {
        if (start == null || end == null) {
            return false;
        }
        return isValidCoordinate(start.getX(), start.getY()) &&
               isValidCoordinate(end.getX(), end.getY()) &&
               areCoordinatesAligned(start, end);
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= MIN_COORDINATE && y >= MIN_COORDINATE;
    }

    private boolean areCoordinatesAligned(CoordinatePair start, CoordinatePair end) {
        return start.getX() == end.getX() || start.getY() == end.getY();
    }
}