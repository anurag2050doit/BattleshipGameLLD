package com.game.validation;

import com.game.entity.Player;
import com.game.entity.Ship;
import com.game.exception.PositionOverlapException;
import com.game.exception.OutOfAreaException;

import java.util.List;

public class ShipPositionValidator {
    public static void validatePositionOverlap(Ship ship, List<Ship> existingShips) {
        for (Ship existingShip : existingShips) {
            if (ship.getTopLeft().getX() >= existingShip.getTopLeft().getX()
            && ship.getTopLeft().getX() < existingShip.getBottomRight().getX()
            && ship.getTopLeft().getY() >= existingShip.getTopLeft().getY()
            && ship.getTopLeft().getY() < existingShip.getBottomRight().getY()) {
                throw new PositionOverlapException("Ship position overlaps with an existing ship.");
            }
        }
    }

    public static void validateOutOfArea(Ship ship, Player player) {
        if (
            ship.getTopLeft().getX() < player.getMinX() || ship.getBottomRight().getX() > player.getMaxX()
                || ship.getTopLeft().getY() < 0 || ship.getBottomRight().getY() > player.getSize()
        ) {
            throw new OutOfAreaException("Ship position is out of the allowed area.");
        }
    }
}