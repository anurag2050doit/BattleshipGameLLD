package com.game.state;

import com.game.service.GameService;

public class InitializedState implements GameState {
    private final GameService gameService;

    public InitializedState(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void handleState() {
        System.out.println("Game is initialized. Ready to start.");
        gameService.startGame();
    }
}