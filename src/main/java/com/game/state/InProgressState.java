package com.game.state;

import com.game.service.GameService;

public class InProgressState implements GameState {
    private final GameService gameService;

    public InProgressState(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void handleState() {
        System.out.println("Game is in progress.");
        gameService.playTurn();
    }
}