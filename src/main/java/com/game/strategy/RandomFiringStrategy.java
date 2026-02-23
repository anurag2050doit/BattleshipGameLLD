package com.game.strategy;

import com.game.entity.CoordinatePair;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class RandomFiringStrategy implements FiringStrategy {
    final private int firingRange;
    final private int minX;
    final private int maxX;
    final private Set<CoordinatePair> firedCoordinates = new HashSet<>();
    final private SecureRandom secureRandom = new SecureRandom();

    public RandomFiringStrategy(int firingRange, int minX, int maxX) {
        this.firingRange = firingRange;
        this.minX = minX;
        this.maxX = maxX;
    }

    @Override
    public CoordinatePair fire() {
        int x = secureRandom.nextInt(maxX - minX) + minX;
        int y = secureRandom.nextInt(firingRange);
        CoordinatePair coordinatePair = new CoordinatePair(x, y);
        while (firedCoordinates.contains(coordinatePair)) {
            x = secureRandom.nextInt(maxX - minX) + minX;
            y = secureRandom.nextInt(firingRange);
            coordinatePair = new CoordinatePair(x, y);
        }
        firedCoordinates.add(coordinatePair);
        return coordinatePair;
    }
}