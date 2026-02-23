package com.game.state;

import com.game.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class InProgressStateTest {

    private GameService gameService;
    private InProgressState inProgressState;

    @BeforeEach
    void setUp() {
        gameService = Mockito.mock(GameService.class);
        inProgressState = new InProgressState(gameService);
    }

    @Test
    void handleState_shouldPlayTurn() {
        inProgressState.handleState();
        verify(gameService).playTurn();
    }
}