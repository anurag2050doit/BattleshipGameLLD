package com.game.entity;

import com.game.strategy.PredictiveFiringStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AIPlayer.
 */
class AIPlayerTest {

    private AIPlayer aiPlayer;
    private BattleField mockBattleField;

    @BeforeEach
    void setUp() {
        mockBattleField = new BattleField(10); // 10x10 grid
        aiPlayer = new AIPlayer("AI", mockBattleField);
    }

    @Test
    void testDefaultFiringStrategy() {
        assertTrue(aiPlayer.getFiringStrategy() instanceof PredictiveFiringStrategy);
    }

    @Test
    void testSetCustomFiringStrategy() {
        FiringStrategy customStrategy = new RandomFiringStrategy();
        aiPlayer.setFiringStrategy(customStrategy);
        assertEquals(customStrategy, aiPlayer.getFiringStrategy());
    }

    @Test
    void testGetNextTargetUsingPredictiveStrategy() {
        aiPlayer.getPreviousHits().add(new CoordinatePair(5, 5));
        CoordinatePair target = aiPlayer.getNextTarget();
        assertNotNull(target);
        assertTrue(mockBattleField.isValidCoordinate(target));
    }
}