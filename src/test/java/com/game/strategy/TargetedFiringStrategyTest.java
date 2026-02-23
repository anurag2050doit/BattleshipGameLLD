package com.game.strategy;

import com.game.entity.BattleField;
import com.game.entity.CoordinatePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TargetedFiringStrategyTest {

    private TargetedFiringStrategy strategy;
    private BattleField mockBattleField;

    @BeforeEach
    public void setUp() {
        strategy = new TargetedFiringStrategy();
        mockBattleField = mock(BattleField.class);
    }

    @Test
    public void testGetNextTargetWithoutLastHit() {
        CoordinatePair target = strategy.getNextTarget(mockBattleField);
        assertNotNull(target);
    }

    @Test
    public void testGetNextTargetWithLastHit() {
        CoordinatePair lastHit = new CoordinatePair(2, 3);
        strategy.setLastHit(lastHit);

        when(mockBattleField.isValidCoordinate(any(CoordinatePair.class))).thenReturn(true);
        Optional<CoordinatePair> adjacentTarget = Optional.of(new CoordinatePair(2, 4));
        doReturn(adjacentTarget).when(strategy).findAdjacentTarget(mockBattleField, lastHit);

        CoordinatePair target = strategy.getNextTarget(mockBattleField);
        assertEquals(adjacentTarget.get(), target);
    }

    @Test
    public void testSetLastHit() {
        CoordinatePair hit = new CoordinatePair(1, 1);
        strategy.setLastHit(hit);
        assertNotNull(strategy);
    }
}