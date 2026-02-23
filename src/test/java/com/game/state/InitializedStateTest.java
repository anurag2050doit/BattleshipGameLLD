package com.game.state;

import com.game.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class InitializedStateTest {

    private GameService gameService;
    private InitializedState initializedState;

    @BeforeEach
    void setUp() {
        gameService = Mockito.mock(GameService.class);
        initializedState = new InitializedState(gameService);
    }

    @Test
    void handleState_shouldStartGame() {
        initializedState.handleState();
        verify(gameService).startGame();
    }
}