package com.game.validation;

import com.game.entity.CoordinatePair;

public class ShipPositionValidator {

    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 9; // Assuming a 10x10 grid

    public boolean validateCoordinates(CoordinatePair start, CoordinatePair end) {
        if (start == null || end == null) {
            return false;
        }

        return isValidCoordinate(start.getX(), start.getY()) &&
               isValidCoordinate(end.getX(), end.getY()) &&
               areCoordinatesAligned(start, end);
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= MIN_COORDINATE && x <= MAX_COORDINATE &&
               y >= MIN_COORDINATE && y <= MAX_COORDINATE;
    }

    private boolean areCoordinatesAligned(CoordinatePair start, CoordinatePair end) {
        return start.getX() == end.getX() || start.getY() == end.getY();
    }
}