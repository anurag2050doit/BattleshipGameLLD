package com.game.validation;

public class GameValidator {

    public boolean isValidBattleFieldSize(int size) {
        // Battlefield size must be a positive even number
        if (size <= 0 || size % 2 != 0) {
            throw new IllegalArgumentException("Battlefield size must be a positive even number.");
        }
        return true;
    }
}