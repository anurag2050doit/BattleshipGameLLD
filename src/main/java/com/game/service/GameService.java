package com.game.service;

import com.game.entity.Game;
import com.game.entity.GameStatus;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GameService {

    private Game currentGame;

    public void saveGameReplay(String filePath) throws IOException {
        if (currentGame == null || currentGame.getGameStatus() != GameStatus.GAME_OVER) {
            throw new IllegalStateException("Game must be over to save replay.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            List<String> replayLog = currentGame.getReplayLog();
            for (String logEntry : replayLog) {
                writer.write(logEntry);
                writer.newLine();
            }
        }
    }

    public void loadGameReplay(String filePath) throws IOException {
        List<String> replayLog = Files.readAllLines(Paths.get(filePath));
        replayLog.forEach(System.out::println);
    }

    // Other existing methods
}