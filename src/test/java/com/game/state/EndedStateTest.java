package com.game.state;

import com.game.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class EndedStateTest {

    private GameService mockGameService;
    private EndedState endedState;

    @BeforeEach
    void setUp() {
        mockGameService = mock(GameService.class);
        endedState = new EndedState(mockGameService);
    }

    @Test
    void testHandleStateCallsEndGame() {
        endedState.handleState();
        verify(mockGameService, times(1)).endGame();
    }
}