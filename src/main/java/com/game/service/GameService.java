package com.game.service;

import com.game.state.GameState;
import com.game.state.InitiatedState;
import com.game.state.StartedState;
import com.game.state.EndedState;

public class GameService {
    private GameState initiatedState;
    private GameState startedState;
    private GameState endedState;
    private GameState currentState;

    public GameService() {
        this.initiatedState = new InitiatedState(this);
        this.startedState = new StartedState(this);
        this.endedState = new EndedState(this);
        this.currentState = initiatedState;
    }

    public void setState(GameState state) {
        this.currentState = state;
    }

    public GameState getInitiatedState() {
        return initiatedState;
    }

    public GameState getStartedState() {
        return startedState;
    }

    public GameState getEndedState() {
        return endedState;
    }

    public void initGame(int size) {
        System.out.println("Initializing game with size: " + size);
        currentState.handleState();
    }

    public void setupGame() {
        System.out.println("Setting up the game...");
    }

    public void playGame() {
        System.out.println("Playing the game...");
    }

    public void endGame() {
        System.out.println("Ending the game...");
    }
}