package com.game.entity;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private GameStatus gameStatus;
    private BattleField battleField;
    private final List<String> replayLog;

    public Game() {
        this.gameStatus = GameStatus.INITIALIZED;
        this.replayLog = new ArrayList<>();
    }

    public Game(int battleFieldSize) {
        this.gameStatus = GameStatus.INITIALIZED;
        this.battleField = new BattleField(battleFieldSize);
        this.replayLog = new ArrayList<>();
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public void setBattleField(BattleField battleField) {
        this.battleField = battleField;
    }

    public String getStatus() {
        return gameStatus.name();
    }

    public void addReplayLog(String logEntry) {
        replayLog.add(logEntry);
    }

    public List<String> getReplayLog() {
        return replayLog;
    }
}