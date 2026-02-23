package com.game.strategy;

import com.game.entity.BattleField;
import com.game.entity.CoordinatePair;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

public class RandomFiringStrategy implements FiringStrategy {

    private static final Logger LOGGER = Logger.getLogger(RandomFiringStrategy.class.getName());
    private final Random random = new Random();
    private final Set<CoordinatePair> targetedCoordinates = new HashSet<>();

    @Override
    public CoordinatePair getNextTarget(BattleField opponentBattleField) {
        int gridSize = opponentBattleField.getSize();
        int maxRetries = 100;
        int retries = 0;

        while (retries < maxRetries) {
            int x = random.nextInt(gridSize);
            int y = random.nextInt(gridSize);
            CoordinatePair coordinate = new CoordinatePair(x, y);

            if (!targetedCoordinates.contains(coordinate)) {
                targetedCoordinates.add(coordinate);
                return coordinate;
            }

            retries++;
        }

        LOGGER.warning("Exceeded maximum retries for generating a random target. Defaulting to first untargeted cell.");

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                CoordinatePair coordinate = new CoordinatePair(x, y);
                if (!targetedCoordinates.contains(coordinate)) {
                    targetedCoordinates.add(coordinate);
                    return coordinate;
                }
            }
        }

        throw new IllegalStateException("No available cells to target.");
    }
}