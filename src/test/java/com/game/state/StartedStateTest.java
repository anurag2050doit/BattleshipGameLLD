package com.game.state;

import com.game.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class StartedStateTest {

    private GameService mockGameService;
    private StartedState startedState;

    @BeforeEach
    void setUp() {
        mockGameService = mock(GameService.class);
        startedState = new StartedState(mockGameService);
    }

    @Test
    void testHandleStateTransitionsToEndedState() {
        startedState.handleState();
        verify(mockGameService, times(1)).playGame();
        verify(mockGameService, times(1)).setState(mockGameService.getEndedState());
    }
}