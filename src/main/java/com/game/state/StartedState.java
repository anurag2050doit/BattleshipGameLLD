package com.game.state;

import com.game.service.GameService;

public class StartedState implements GameState {
    private final GameService gameService;

    public StartedState(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void handleState() {
        System.out.println("Game is in STARTED state.");
        gameService.playGame();
        gameService.setState(gameService.getEndedState());
    }
}