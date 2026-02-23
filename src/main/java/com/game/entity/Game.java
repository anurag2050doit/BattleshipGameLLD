package com.game.entity;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private GameStatus gameStatus;
    private final List<String> replayLog;

    public Game() {
        this.gameStatus = GameStatus.INITIALIZED;
        this.replayLog = new ArrayList<>();
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void addReplayLog(String logEntry) {
        replayLog.add(logEntry);
    }

    public List<String> getReplayLog() {
        return replayLog;
    }

    // Other existing methods
}