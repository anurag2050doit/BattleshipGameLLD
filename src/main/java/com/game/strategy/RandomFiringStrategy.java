package com.game.strategy;

import com.game.entity.BattleField;
import com.game.entity.CoordinatePair;

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
    public CoordinatePair getNextTarget(BattleField opponentBattleField) {
        int size = opponentBattleField.getSize();
        int x = secureRandom.nextInt(size);
        int y = secureRandom.nextInt(size);
        return new CoordinatePair(x, y);
    }
}