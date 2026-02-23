package com.game.entity;

import com.game.strategy.FiringStrategy;

public class AIPlayer extends Player {

    private final FiringStrategy firingStrategy;

    public AIPlayer(String name, FiringStrategy firingStrategy) {
        super(name);
        this.firingStrategy = firingStrategy;
    }

    public FiringStrategy getFiringStrategy() {
        return firingStrategy;
    }
}