package com.game.service;

import com.game.entity.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {

    @Test
    public void testConfigureAndInitializeGame() {
        GameService gameService = new GameService();

        int gridSize = 8;
        int numberOfShips = 4;
        int[] shipSizes = {2, 3, 3, 4};

        gameService.configureGameSettings(gridSize, numberOfShips, shipSizes);
        Game game = gameService.initializeGame();

        assertNotNull(game);
        assertEquals(gridSize, game.getGridSize());
        assertEquals(numberOfShips, game.getNumberOfShips());
        assertArrayEquals(shipSizes, game.getShipSizes());
    }

    @Test
    public void testInitializeGameWithoutSettings() {
        GameService gameService = new GameService();

        Exception exception = assertThrows(IllegalStateException.class, gameService::initializeGame);
        assertEquals("Game settings must be configured before initializing the game.", exception.getMessage());
    }

    @Test
    public void testInvalidGridSize() {
        GameService gameService = new GameService();

        int gridSize = -1;
        int numberOfShips = 4;
        int[] shipSizes = {2, 3, 3, 4};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> gameService.configureGameSettings(gridSize, numberOfShips, shipSizes));
        assertEquals("Grid size must be positive.", exception.getMessage());
    }

    @Test
    public void testInvalidNumberOfShips() {
        GameService gameService = new GameService();

        int gridSize = 8;
        int numberOfShips = -1;
        int[] shipSizes = {2, 3, 3, 4};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> gameService.configureGameSettings(gridSize, numberOfShips, shipSizes));
        assertEquals("Number of ships must be positive.", exception.getMessage());
    }

    @Test
    public void testMismatchedShipSizes() {
        GameService gameService = new GameService();

        int gridSize = 8;
        int numberOfShips = 4;
        int[] shipSizes = {2, 3};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> gameService.configureGameSettings(gridSize, numberOfShips, shipSizes));
        assertEquals("Number of ships does not match the length of ship sizes array.", exception.getMessage());
    }
}