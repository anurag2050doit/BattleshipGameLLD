package com.game.entity;

import com.game.strategy.FiringStrategy;

public class AIPlayer extends Player {

    public AIPlayer(String name, FiringStrategy firingStrategy) {
        super(name, firingStrategy, 0, 0, 0);
    }
}