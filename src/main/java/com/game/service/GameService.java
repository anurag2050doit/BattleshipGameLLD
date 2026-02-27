package com.game.service;

import com.game.entity.GameSettings;
import com.game.entity.Game;

public class GameService {
    private GameSettings gameSettings;

    public void configureGameSettings(int gridSize, int numberOfShips, int[] shipSizes) {
        this.gameSettings = new GameSettings(gridSize, numberOfShips, shipSizes);
    }

    public Game initializeGame() {
        if (gameSettings == null) {
            throw new IllegalStateException("Game settings must be configured before initializing the game.");
        }
        // Initialize the game using the configured settings
        return new Game(gameSettings.getGridSize(), gameSettings.getNumberOfShips(), gameSettings.getShipSizes());
    }
}