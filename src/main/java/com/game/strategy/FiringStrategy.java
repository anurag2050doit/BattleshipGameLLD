package com.game.strategy;

import com.game.entity.CoordinatePair;
import com.game.entity.BattleField;

public interface FiringStrategy {
    CoordinatePair getNextTarget(BattleField opponentBattleField);
}