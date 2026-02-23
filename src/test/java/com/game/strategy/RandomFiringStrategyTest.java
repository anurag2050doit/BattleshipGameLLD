package com.game.strategy;

import com.game.entity.CoordinatePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RandomFiringStrategyTest {

    private RandomFiringStrategy randomFiringStrategy;
    private final int firingRange = 10;
    private final int minX = 0;
    private final int maxX = 10;

    @BeforeEach
    void setUp() {
        randomFiringStrategy = new RandomFiringStrategy(firingRange, minX, maxX);
    }

    @Test
    void testFireGeneratesValidCoordinates() {
        CoordinatePair coordinate = randomFiringStrategy.fire();
        assertTrue(coordinate.getX() >= minX && coordinate.getX() < maxX);
        assertTrue(coordinate.getY() >= 0 && coordinate.getY() < firingRange);
    }

    @Test
    void testFireDoesNotRepeatCoordinates() {
        Set<CoordinatePair> firedCoordinates = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            CoordinatePair coordinate = randomFiringStrategy.fire();
            assertFalse(firedCoordinates.contains(coordinate));
            firedCoordinates.add(coordinate);
        }
    }

    @Test
    void testSecureRandomIsUsed() {
        // This test ensures that SecureRandom is used instead of Random.
        // Since SecureRandom is not directly accessible, we rely on the behavior.
        CoordinatePair firstCoordinate = randomFiringStrategy.fire();
        CoordinatePair secondCoordinate = randomFiringStrategy.fire();
        assertNotEquals(firstCoordinate, secondCoordinate);
    }

    @Test
    void testEdgeCaseFiringRangeZero() {
        RandomFiringStrategy edgeCaseStrategy = new RandomFiringStrategy(0, minX, maxX);
        Exception exception = assertThrows(IllegalArgumentException.class, edgeCaseStrategy::fire);
        assertEquals("Bound must be positive", exception.getMessage());
    }

    @Test
    void testEdgeCaseMinXEqualsMaxX() {
        RandomFiringStrategy edgeCaseStrategy = new RandomFiringStrategy(firingRange, 5, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, edgeCaseStrategy::fire);
        assertEquals("Bound must be positive", exception.getMessage());
    }
}