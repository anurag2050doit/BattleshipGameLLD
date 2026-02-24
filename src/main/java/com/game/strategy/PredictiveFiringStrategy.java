package com.game.strategy;

import com.game.entity.BattleField;
import com.game.entity.CoordinatePair;
import java.util.List;
import java.util.Random;

/**
 * PredictiveFiringStrategy implements a firing strategy where the AI predicts
 * the next target based on previous hits.
 */
public class PredictiveFiringStrategy implements FiringStrategy {

    private final Random random = new Random();

    @Override
    public CoordinatePair getNextTarget(BattleField opponentBattleField, List<CoordinatePair> previousHits) {
        if (!previousHits.isEmpty()) {
            CoordinatePair lastHit = previousHits.get(previousHits.size() - 1);
            // Predict next target based on the last hit
            CoordinatePair[] potentialTargets = {
                new CoordinatePair(lastHit.getX() + 1, lastHit.getY()),
                new CoordinatePair(lastHit.getX() - 1, lastHit.getY()),
                new CoordinatePair(lastHit.getX(), lastHit.getY() + 1),
                new CoordinatePair(lastHit.getX(), lastHit.getY() - 1)
            };

            for (CoordinatePair target : potentialTargets) {
                if (opponentBattleField.isValidCoordinate(target) && !opponentBattleField.isAlreadyTargeted(target)) {
                    return target;
                }
            }
        }

        // Fallback to random targeting if no prediction is possible
        return getRandomTarget(opponentBattleField);
    }

    private CoordinatePair getRandomTarget(BattleField opponentBattleField) {
        int size = opponentBattleField.getSize();
        CoordinatePair target;
        do {
            target = new CoordinatePair(random.nextInt(size), random.nextInt(size));
        } while (opponentBattleField.isAlreadyTargeted(target));
        return target;
    }
}