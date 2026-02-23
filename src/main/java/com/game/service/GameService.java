package com.game.service;

import com.game.state.GameState;
import com.game.state.InitializedState;
import com.game.state.InProgressState;
import com.game.state.GameOverState;

public class GameService {
    private GameState currentState;

    public GameService() {
        this.currentState = new InitializedState(this);
    }

    public void setState(GameState state) {
        this.currentState = state;
    }

    public void startGame() {
        System.out.println("Starting the game...");
        setState(new InProgressState(this));
    }

    public void playTurn() {
        System.out.println("Playing a turn...");
        // Logic to determine if the game is over
        boolean isGameOver = checkGameOver();
        if (isGameOver) {
            setState(new GameOverState());
        }
    }

    public void handleState() {
        currentState.handleState();
    }

    private boolean checkGameOver() {
        // Placeholder logic for game over condition
        return false;
    }
}