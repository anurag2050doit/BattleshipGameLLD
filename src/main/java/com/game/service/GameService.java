package com.game.service;

import com.game.entity.Game;
import com.game.validation.GameValidator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GameService {

    private static final Logger LOGGER = Logger.getLogger(GameService.class.getName());
    private static final ConcurrentHashMap<String, Long> requestTimestamps = new ConcurrentHashMap<>();
    private static final long RATE_LIMIT_INTERVAL_MS = 1000; // 1 request per second

    private final GameValidator gameValidator;

    public GameService(GameValidator gameValidator) {
        this.gameValidator = gameValidator;
    }

    public synchronized Game initGame(String clientId, int size) {
        if (!isRequestAllowed(clientId)) {
            LOGGER.warning("Rate limit exceeded for client: " + clientId);
            throw new IllegalStateException("Rate limit exceeded. Please try again later.");
        }

        LOGGER.info("Initializing game for client: " + clientId);
        Game game = new Game(size);
        gameValidator.validateGame(game);
        return game;
    }

    private boolean isRequestAllowed(String clientId) {
        long currentTime = System.currentTimeMillis();
        Long lastRequestTime = requestTimestamps.get(clientId);

        if (lastRequestTime == null || (currentTime - lastRequestTime) > RATE_LIMIT_INTERVAL_MS) {
            requestTimestamps.put(clientId, currentTime);
            return true;
        }
        return false;
    }
}