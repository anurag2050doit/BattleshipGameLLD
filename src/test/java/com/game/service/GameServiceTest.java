package com.game.service;

import com.game.state.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceTest {

    private GameService gameService;
    private GameState mockInitiatedState;
    private GameState mockStartedState;
    private GameState mockEndedState;

    @BeforeEach
    void setUp() {
        gameService = new GameService();
        mockInitiatedState = mock(GameState.class);
        mockStartedState = mock(GameState.class);
        mockEndedState = mock(GameState.class);
    }

    @Test
    void testInitGameTransitionsToStartedState() {
        gameService.setState(mockInitiatedState);
        gameService.initGame(6);
        verify(mockInitiatedState, times(1)).handleState();
    }

    @Test
    void testSetupGameCalledInInitiatedState() {
        gameService.setState(gameService.getInitiatedState());
        gameService.initGame(6);
        assertTrue(gameService.getStartedState() instanceof GameState);
    }

    @Test
    void testPlayGameCalledInStartedState() {
        gameService.setState(gameService.getStartedState());
        gameService.getStartedState().handleState();
        assertTrue(gameService.getEndedState() instanceof GameState);
    }

    @Test
    void testEndGameCalledInEndedState() {
        gameService.setState(gameService.getEndedState());
        gameService.getEndedState().handleState();
        assertEquals(gameService.getEndedState(), gameService.getEndedState());
    }

    @Test
    void testInvalidStateTransition() {
        gameService.setState(mockStartedState);
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            gameService.setState(null);
        });
        assertEquals("State cannot be null", exception.getMessage());
    }
}