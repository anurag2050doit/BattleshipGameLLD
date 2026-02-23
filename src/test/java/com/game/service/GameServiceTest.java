package com.game.service;

import com.game.entity.Game;
import com.game.validation.GameValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameServiceTest {

    private GameService gameService;
    private GameValidator gameValidator;

    @BeforeEach
    public void setUp() {
        gameValidator = mock(GameValidator.class);
        gameService = new GameService(gameValidator);
    }

    @Test
    public void testInitGameRateLimiting() {
        String clientId = "testClient";

        // First request should succeed
        Game game1 = gameService.initGame(clientId, 10);
        assertNotNull(game1);

        // Second request within rate limit interval should fail
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            gameService.initGame(clientId, 10);
        });
        assertEquals("Rate limit exceeded. Please try again later.", exception.getMessage());
    }

    @Test
    public void testInitGameAfterRateLimitInterval() throws InterruptedException {
        String clientId = "testClient";

        // First request should succeed
        Game game1 = gameService.initGame(clientId, 10);
        assertNotNull(game1);

        // Wait for rate limit interval to pass
        Thread.sleep(1100);

        // Second request should succeed after interval
        Game game2 = gameService.initGame(clientId, 10);
        assertNotNull(game2);
    }

    @Test
    public void testInitGameWithDifferentClients() {
        String clientId1 = "client1";
        String clientId2 = "client2";

        // Both clients should be able to initialize games independently
        Game game1 = gameService.initGame(clientId1, 10);
        assertNotNull(game1);

        Game game2 = gameService.initGame(clientId2, 10);
        assertNotNull(game2);
    }

    @Test
    public void testInitGameInvalidSize() {
        String clientId = "testClient";

        // Mock validation to throw an exception for invalid game size
        doThrow(new IllegalArgumentException("Invalid game size.")).when(gameValidator).validateGame(any(Game.class));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            gameService.initGame(clientId, -1);
        });
        assertEquals("Invalid game size.", exception.getMessage());
    }

    @Test
    public void testInitGameConcurrentRequests() throws InterruptedException {
        String clientId = "testClient";

        // First request should succeed
        Game game1 = gameService.initGame(clientId, 10);
        assertNotNull(game1);

        // Simulate concurrent requests within the rate limit interval
        Thread thread = new Thread(() -> {
            Exception exception = assertThrows(IllegalStateException.class, () -> {
                gameService.initGame(clientId, 10);
            });
            assertEquals("Rate limit exceeded. Please try again later.", exception.getMessage());
        });
        thread.start();
        thread.join();
    }
}