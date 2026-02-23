package com.game.entity;

import com.game.strategy.FiringStrategy;
import com.game.strategy.RandomFiringStrategy;
import com.game.strategy.TargetedFiringStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AIPlayerTest {

    @Test
    public void testAIPlayerInitialization() {
        FiringStrategy strategy = new RandomFiringStrategy();
        AIPlayer aiPlayer = new AIPlayer("AI_Test", strategy);

        assertNotNull(aiPlayer);
        assertEquals("AI_Test", aiPlayer.getName());
        assertEquals(strategy, aiPlayer.getFiringStrategy());
    }

    @Test
    public void testAIPlayerWithTargetedFiringStrategy() {
        FiringStrategy strategy = new TargetedFiringStrategy();
        AIPlayer aiPlayer = new AIPlayer("AI_Hard", strategy);

        assertTrue(aiPlayer.getFiringStrategy() instanceof TargetedFiringStrategy);
    }
}