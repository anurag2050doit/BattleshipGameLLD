package com.game.state;

import com.game.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class InitiatedStateTest {

    private GameService mockGameService;
    private InitiatedState initiatedState;

    @BeforeEach
    void setUp() {
        mockGameService = mock(GameService.class);
        initiatedState = new InitiatedState(mockGameService);
    }

    @Test
    void testHandleStateTransitionsToStartedState() {
        initiatedState.handleState();
        verify(mockGameService, times(1)).setupGame();
        verify(mockGameService, times(1)).setState(mockGameService.getStartedState());
    }
}