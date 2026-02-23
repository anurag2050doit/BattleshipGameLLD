package com.game.strategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RandomFiringStrategyTest {

    @Test
    public void testRandomFiringStrategyProducesValidCoordinates() {
        RandomFiringStrategy strategy = new RandomFiringStrategy();
        int battlefieldSize = 10;

        for (int i = 0; i < 100; i++) {
            int[] target = strategy.getNextTarget(battlefieldSize);
            assertNotNull(target, "Target should not be null");
            assertEquals(2, target.length, "Target should have two coordinates");
            assertTrue(target[0] >= 0 && target[0] < battlefieldSize, "X coordinate should be within bounds");
            assertTrue(target[1] >= 0 && target[1] < battlefieldSize, "Y coordinate should be within bounds");
        }
    }

    @Test
    public void testRandomFiringStrategyHandlesSmallBattlefield() {
        RandomFiringStrategy strategy = new RandomFiringStrategy();
        int battlefieldSize = 1;

        for (int i = 0; i < 10; i++) {
            int[] target = strategy.getNextTarget(battlefieldSize);
            assertNotNull(target, "Target should not be null");
            assertEquals(2, target.length, "Target should have two coordinates");
            assertEquals(0, target[0], "X coordinate should be 0 for battlefield size 1");
            assertEquals(0, target[1], "Y coordinate should be 0 for battlefield size 1");
        }
    }

    @Test
    public void testRandomFiringStrategyHandlesLargeBattlefield() {
        RandomFiringStrategy strategy = new RandomFiringStrategy();
        int battlefieldSize = 1000;

        for (int i = 0; i < 100; i++) {
            int[] target = strategy.getNextTarget(battlefieldSize);
            assertNotNull(target, "Target should not be null");
            assertEquals(2, target.length, "Target should have two coordinates");
            assertTrue(target[0] >= 0 && target[0] < battlefieldSize, "X coordinate should be within bounds");
            assertTrue(target[1] >= 0 && target[1] < battlefieldSize, "Y coordinate should be within bounds");
        }
    }

    @Test
    public void testRandomFiringStrategyGeneratesDifferentCoordinates() {
        RandomFiringStrategy strategy = new RandomFiringStrategy();
        int battlefieldSize = 10;
        boolean differentCoordinatesGenerated = false;

        int[] firstTarget = strategy.getNextTarget(battlefieldSize);
        for (int i = 0; i < 100; i++) {
            int[] target = strategy.getNextTarget(battlefieldSize);
            if (target[0] != firstTarget[0] || target[1] != firstTarget[1]) {
                differentCoordinatesGenerated = true;
                break;
            }
        }

        assertTrue(differentCoordinatesGenerated, "Strategy should generate different coordinates over multiple calls");
    }

    @Test
    public void testRandomFiringStrategyHandlesZeroBattlefieldSize() {
        RandomFiringStrategy strategy = new RandomFiringStrategy();
        int battlefieldSize = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            strategy.getNextTarget(battlefieldSize);
        });

        assertEquals("Battlefield size must be greater than 0", exception.getMessage(), "Exception message should indicate invalid battlefield size");
    }
}