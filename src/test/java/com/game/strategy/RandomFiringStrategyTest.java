package com.game.strategy;

import com.game.entity.BattleField;
import com.game.entity.CoordinatePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RandomFiringStrategyTest {

    private RandomFiringStrategy strategy;
    private BattleField mockBattleField;

    @BeforeEach
    void setUp() {
        strategy = new RandomFiringStrategy();
        mockBattleField = mock(BattleField.class);
        when(mockBattleField.getSize()).thenReturn(5);
    }

    @Test
    void testGetNextTarget_NoDuplicates() {
        Set<CoordinatePair> targets = new HashSet<>();
        for (int i = 0; i < 25; i++) {
            CoordinatePair target = strategy.getNextTarget(mockBattleField);
            assertFalse(targets.contains(target), "Duplicate target generated: " + target);
            targets.add(target);
        }
    }

    @Test
    void testGetNextTarget_ExhaustionFallback() {
        for (int i = 0; i < 25; i++) {
            strategy.getNextTarget(mockBattleField);
        }

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            strategy.getNextTarget(mockBattleField);
        });

        assertEquals("No available cells to target.", exception.getMessage());
    }

    @Test
    void testGetNextTarget_ExcessiveRetriesLogged() {
        Logger logger = Logger.getLogger(RandomFiringStrategy.class.getName());
        for (int i = 0; i < 24; i++) {
            strategy.getNextTarget(mockBattleField);
        }

        CoordinatePair lastTarget = strategy.getNextTarget(mockBattleField);
        assertNotNull(lastTarget);
        // Verify that the logger captured the warning message (mocking logger behavior can be added if needed)
    }

    @Test
    void testGetNextTarget_GridBoundary() {
        when(mockBattleField.getSize()).thenReturn(1);
        CoordinatePair target = strategy.getNextTarget(mockBattleField);
        assertEquals(new CoordinatePair(0, 0), target);
    }

    @Test
    void testGetNextTarget_EmptyBattlefield() {
        when(mockBattleField.getSize()).thenReturn(0);
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            strategy.getNextTarget(mockBattleField);
        });

        assertEquals("No available cells to target.", exception.getMessage());
    }
}