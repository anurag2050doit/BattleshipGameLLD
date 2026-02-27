package com.game;

import com.game.service.GameService;

public class BattleShipGameApplication {
    public static void main(String[] args) {
        GameService gameService = new GameService();

        // Example configuration
        int gridSize = 10;
        int numberOfShips = 5;
        int[] shipSizes = {2, 3, 3, 4, 5};

        gameService.configureGameSettings(gridSize, numberOfShips, shipSizes);
        gameService.initializeGame();

        System.out.println("Game initialized with custom settings.");
    }
}