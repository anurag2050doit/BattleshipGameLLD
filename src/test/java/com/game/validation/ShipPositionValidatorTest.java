package com.game.validation;

import com.game.entity.Player;
import com.game.entity.Ship;
import com.game.entity.CoordinatePair;
import com.game.exception.PositionOverlapException;
import com.game.exception.OutOfAreaException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShipPositionValidatorTest {

    @Test
    public void testValidatePositionOverlap_NoOverlap() {
        Ship existingShip = new Ship(new CoordinatePair(0, 0), new CoordinatePair(2, 2));
        Ship newShip = new Ship(new CoordinatePair(3, 3), new CoordinatePair(4, 4));

        ShipPositionValidator.validatePositionOverlap(newShip, Collections.singletonList(existingShip));
    }

    @Test
    public void testValidatePositionOverlap_WithOverlap() {
        Ship existingShip = new Ship(new CoordinatePair(0, 0), new CoordinatePair(2, 2));
        Ship newShip = new Ship(new CoordinatePair(1, 1), new CoordinatePair(3, 3));

        assertThrows(PositionOverlapException.class, () -> {
            ShipPositionValidator.validatePositionOverlap(newShip, Collections.singletonList(existingShip));
        });
    }

    @Test
    public void testValidateOutOfArea_WithinBounds() {
        Player player = new Player(0, 0, 5, 5);
        Ship ship = new Ship(new CoordinatePair(1, 1), new CoordinatePair(3, 3));

        ShipPositionValidator.validateOutOfArea(ship, player);
    }

    @Test
    public void testValidateOutOfArea_OutOfBounds() {
        Player player = new Player(0, 0, 5, 5);
        Ship ship = new Ship(new CoordinatePair(4, 4), new CoordinatePair(6, 6));

        assertThrows(OutOfAreaException.class, () -> {
            ShipPositionValidator.validateOutOfArea(ship, player);
        });
    }

    @Test
    public void testValidateOutOfArea_NegativeCoordinates() {
        Player player = new Player(0, 0, 5, 5);
        Ship ship = new Ship(new CoordinatePair(-1, -1), new CoordinatePair(1, 1));

        assertThrows(OutOfAreaException.class, () -> {
            ShipPositionValidator.validateOutOfArea(ship, player);
        });
    }

    @Test
    public void testValidatePositionOverlap_MultipleShips() {
        Ship existingShip1 = new Ship(new CoordinatePair(0, 0), new CoordinatePair(2, 2));
        Ship existingShip2 = new Ship(new CoordinatePair(3, 3), new CoordinatePair(5, 5));
        Ship newShip = new Ship(new CoordinatePair(1, 1), new CoordinatePair(4, 4));

        assertThrows(PositionOverlapException.class, () -> {
            ShipPositionValidator.validatePositionOverlap(newShip, Arrays.asList(existingShip1, existingShip2));
        });
    }
}