package com.game.strategy;

import com.game.entity.CoordinatePair;
import com.game.entity.BattleField;
import java.util.function.BiFunction;

public class CustomFiringStrategy implements FiringStrategy {

    private final BiFunction<BattleField, CoordinatePair, CoordinatePair> strategyFunction;

    public CustomFiringStrategy(BiFunction<BattleField, CoordinatePair, CoordinatePair> strategyFunction) {
        this.strategyFunction = strategyFunction;
    }

    @Override
    public CoordinatePair getNextTarget(BattleField opponentBattleField) {
        return strategyFunction.apply(opponentBattleField, null);
    }
}