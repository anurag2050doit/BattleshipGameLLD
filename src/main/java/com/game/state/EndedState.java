package com.game.state;

import com.game.service.GameService;

public class EndedState implements GameState {
    private final GameService gameService;

    public EndedState(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void handleState() {
        System.out.println("Game is in ENDED state.");
        gameService.endGame();
    }
}