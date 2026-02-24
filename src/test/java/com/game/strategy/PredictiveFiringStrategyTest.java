package com.game.strategy;

import com.game.entity.BattleField;
import com.game.entity.CoordinatePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PredictiveFiringStrategy.
 */
class PredictiveFiringStrategyTest {

    private PredictiveFiringStrategy strategy;
    private BattleField mockBattleField;
    private List<CoordinatePair> previousHits;

    @BeforeEach
    void setUp() {
        strategy = new PredictiveFiringStrategy();
        mockBattleField = new BattleField(10); // 10x10 grid
        previousHits = new ArrayList<>();
    }

    @Test
    void testPredictiveTargeting() {
        previousHits.add(new CoordinatePair(5, 5));
        CoordinatePair target = strategy.getNextTarget(mockBattleField, previousHits);
        assertNotNull(target);
        assertTrue(mockBattleField.isValidCoordinate(target));
    }

    @Test
    void testFallbackToRandomTargeting() {
        CoordinatePair target = strategy.getNextTarget(mockBattleField, previousHits);
        assertNotNull(target);
        assertTrue(mockBattleField.isValidCoordinate(target));
    }

    @Test
    void testAvoidAlreadyTargetedCoordinates() {
        previousHits.add(new CoordinatePair(5, 5));
        mockBattleField.markAsTargeted(new CoordinatePair(6, 5));
        CoordinatePair target = strategy.getNextTarget(mockBattleField, previousHits);
        assertNotEquals(new CoordinatePair(6, 5), target);
    }

    @Test
    void testEdgeCaseWithNoValidCoordinates() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mockBattleField.markAsTargeted(new CoordinatePair(i, j));
            }
        }
        CoordinatePair target = strategy.getNextTarget(mockBattleField, previousHits);
        assertNull(target);
    }
}