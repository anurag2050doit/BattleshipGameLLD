package com.game.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameOverStateTest {

    private final GameOverState gameOverState = new GameOverState();

    @Test
    void handleState_shouldPrintGameOverMessage() {
        assertDoesNotThrow(() -> gameOverState.handleState());
    }
}