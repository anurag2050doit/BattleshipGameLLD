package com.game.strategy;

import java.security.SecureRandom;

public class RandomFiringStrategy implements FiringStrategy {

    private final SecureRandom secureRandom;

    public RandomFiringStrategy() {
        this.secureRandom = new SecureRandom();
        byte[] seed = new byte[16];
        new SecureRandom().nextBytes(seed);
        this.secureRandom.setSeed(seed);
    }

    @Override
    public int[] getNextTarget(int battlefieldSize) {
        int x = secureRandom.nextInt(battlefieldSize);
        int y = secureRandom.nextInt(battlefieldSize);
        return new int[]{x, y};
    }
}