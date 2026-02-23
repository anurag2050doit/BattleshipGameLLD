package com.game.state;

public class GameOverState implements GameState {

    @Override
    public void handleState() {
        System.out.println("Game over. Thank you for playing!");
    }
}