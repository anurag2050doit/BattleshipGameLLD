package com.game.entity;

import com.game.strategy.FiringStrategy;
import com.game.strategy.PredictiveFiringStrategy;

/**
 * AIPlayer represents the computer-controlled player in the game.
 */
public class AIPlayer extends Player {

    private FiringStrategy firingStrategy;

    public AIPlayer(String name, BattleField battleField) {
        super(name, battleField);
        this.firingStrategy = new PredictiveFiringStrategy(); // Default to predictive strategy
    }

    public void setFiringStrategy(FiringStrategy firingStrategy) {
        this.firingStrategy = firingStrategy;
    }

    @Override
    public CoordinatePair getNextTarget() {
        return firingStrategy.getNextTarget(getOpponentBattleField(), getPreviousHits());
    }
}