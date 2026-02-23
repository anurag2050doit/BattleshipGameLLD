package com.game.service;

import com.game.state.GameOverState;
import com.game.state.InProgressState;
import com.game.state.InitializedState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService();
    }

    @Test
    void startGame_shouldTransitionToInProgressState() {
        gameService.startGame();
        assertTrue(gameService.getCurrentState() instanceof InProgressState);
    }

    @Test
    void playTurn_shouldTransitionToGameOverStateWhenGameOver() {
        GameService spyService = Mockito.spy(gameService);
        Mockito.doReturn(true).when(spyService).checkGameOver();

        spyService.playTurn();
        assertTrue(spyService.getCurrentState() instanceof GameOverState);
    }

    @Test
    void handleState_shouldDelegateToCurrentState() {
        InitializedState mockState = Mockito.mock(InitializedState.class);
        gameService.setState(mockState);

        gameService.handleState();
        verify(mockState).handleState();
    }
}