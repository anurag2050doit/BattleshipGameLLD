package com.game.state;

import com.game.service.GameService;

public class InitiatedState implements GameState {
    private final GameService gameService;

    public InitiatedState(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void handleState() {
        System.out.println("Game is in INITIATED state.");
        gameService.setupGame();
        gameService.setState(gameService.getStartedState());
    }
}