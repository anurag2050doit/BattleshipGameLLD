package com.game.validation;

import com.game.exception.InvalidBattleFieldSizeException;

public class GameValidator {
    public static boolean isValidBattleFieldSize(int size) {
        if (size <= 0 || size % 2 != 0) {
            throw new InvalidBattleFieldSizeException("Battlefield size must be a positive even number.");
        }
        return true;
    }
}