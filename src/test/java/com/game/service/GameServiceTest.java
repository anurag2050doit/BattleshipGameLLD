package com.game.service;

import com.game.entity.Game;
import com.game.entity.GameStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {

    private GameService gameService;
    private Game game;

    @BeforeEach
    public void setUp() {
        gameService = new GameService();
        game = new Game();
        game.setGameStatus(GameStatus.GAME_OVER);
        game.addReplayLog("PlayerA fires at (3, 3): Hit");
        game.addReplayLog("PlayerB fires at (4, 4): Miss");
    }

    @Test
    public void testSaveGameReplay() throws IOException {
        String filePath = "test_replay.log";
        gameService.saveGameReplay(filePath);

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        assertEquals(2, lines.size());
        assertEquals("PlayerA fires at (3, 3): Hit", lines.get(0));
        assertEquals("PlayerB fires at (4, 4): Miss", lines.get(1));

        Files.delete(Paths.get(filePath));
    }

    @Test
    public void testLoadGameReplay() throws IOException {
        String filePath = "test_replay.log";
        Files.write(Paths.get(filePath), game.getReplayLog());

        gameService.loadGameReplay(filePath);

        Files.delete(Paths.get(filePath));
    }

    @Test
    public void testSaveGameReplayWhenGameNotOver() {
        game.setGameStatus(GameStatus.IN_PROGRESS);
        String filePath = "test_replay.log";

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            gameService.saveGameReplay(filePath);
        });

        assertEquals("Game must be over to save replay.", exception.getMessage());
    }

    @Test
    public void testSaveGameReplayWithNoGame() {
        gameService = new GameService();
        String filePath = "test_replay.log";

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            gameService.saveGameReplay(filePath);
        });

        assertEquals("Game must be over to save replay.", exception.getMessage());
    }

    @Test
    public void testLoadGameReplayWithInvalidFilePath() {
        String invalidFilePath = "non_existent_file.log";

        IOException exception = assertThrows(IOException.class, () -> {
            gameService.loadGameReplay(invalidFilePath);
        });

        assertTrue(exception.getMessage().contains("non_existent_file.log"));
    }
}