package com.game.strategy;

import com.game.entity.BattleField;
import com.game.entity.CoordinatePair;

import java.util.Optional;

public class TargetedFiringStrategy implements FiringStrategy {

    private CoordinatePair lastHit;

    public TargetedFiringStrategy() {
        this.lastHit = null;
    }

    @Override
    public CoordinatePair getNextTarget(BattleField opponentField) {
        if (lastHit != null) {
            Optional<CoordinatePair> nextTarget = findAdjacentTarget(opponentField, lastHit);
            if (nextTarget.isPresent()) {
                return nextTarget.get();
            }
        }
        return new RandomFiringStrategy().getNextTarget(opponentField);
    }

    public void setLastHit(CoordinatePair hit) {
        this.lastHit = hit;
    }

    private Optional<CoordinatePair> findAdjacentTarget(BattleField field, CoordinatePair hit) {
        // Logic to find an adjacent coordinate to the last hit
        // Returns an Optional of CoordinatePair
        return Optional.empty(); // Placeholder for actual implementation
    }
}