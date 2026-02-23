package com.game.strategy;

import com.game.entity.BattleField;
import com.game.entity.CoordinatePair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomFiringStrategyTest {

    @Test
    void testCustomStrategy() {
        BattleField mockBattleField = new BattleField(5);
        CustomFiringStrategy strategy = new CustomFiringStrategy((battleField, lastTarget) -> new CoordinatePair(2, 2));

        CoordinatePair target = strategy.getNextTarget(mockBattleField);
        assertEquals(2, target.getX());
        assertEquals(2, target.getY());
    }

    @Test
    void testNullBattleField() {
        CustomFiringStrategy strategy = new CustomFiringStrategy((battleField, lastTarget) -> {
            if (battleField == null) {
                return new CoordinatePair(0, 0);
            }
            return new CoordinatePair(1, 1);
        });

        CoordinatePair target = strategy.getNextTarget(null);
        assertEquals(0, target.getX());
        assertEquals(0, target.getY());
    }

    @Test
    void testDynamicStrategyFunction() {
        BattleField mockBattleField = new BattleField(5);
        CustomFiringStrategy strategy = new CustomFiringStrategy((battleField, lastTarget) -> {
            if (lastTarget == null) {
                return new CoordinatePair(3, 3);
            }
            return new CoordinatePair(lastTarget.getX() + 1, lastTarget.getY() + 1);
        });

        CoordinatePair firstTarget = strategy.getNextTarget(mockBattleField);
        assertEquals(3, firstTarget.getX());
        assertEquals(3, firstTarget.getY());

        CoordinatePair secondTarget = strategy.getNextTarget(mockBattleField);
        assertEquals(4, secondTarget.getX());
        assertEquals(4, secondTarget.getY());
    }
}