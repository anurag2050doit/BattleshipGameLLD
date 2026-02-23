package com.game.service;

import com.game.entity.Game;
import com.game.state.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MultiplayerGameServiceTest {

    private Game mockGame;
    private MultiplayerGameService service;

    @BeforeEach
    void setUp() {
        mockGame = mock(Game.class);
        service = new MultiplayerGameService(mockGame);
    }

    @Test
    void testHandlePlayerAction() {
        service.handlePlayerAction("player1", "fire");
        verify(mockGame, times(1)).updateState(any());
    }

    @Test
    void testGetGameState() {
        GameState mockState = mock(GameState.class);
        when(mockGame.getState()).thenReturn(mockState);

        GameState state = service.getGameState();
        assertNotNull(state);
        assertEquals(mockState, state);
    }
}