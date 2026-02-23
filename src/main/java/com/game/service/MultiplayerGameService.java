package com.game.service;

import com.game.entity.Game;
import com.game.entity.GameStatus;

public class MultiplayerGameService {
    private Game game;

    public MultiplayerGameService(Game game) {
        this.game = game;
    }

    public void handlePlayerAction(String playerId, String action) {
        // Process player actions (e.g., firing missiles, updating game state)
        System.out.println("Player " + playerId + " performed action: " + action);
        // Update game state based on action
    }

    public GameStatus getGameState() {
        return game.getGameStatus();
    }
}