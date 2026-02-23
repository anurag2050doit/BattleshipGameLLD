package com.game.service;

import com.game.entity.*;
import com.game.factory.ShipFactory;
import com.game.exception.OutOfAreaException;
import com.game.exception.PositionOverlapException;
import com.game.strategy.RandomFiringStrategy;
import com.game.validation.GameValidator;
import com.game.validation.ShipPositionValidator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameService {

    private Game currentGame;
    private final GameValidator gameValidator = new GameValidator();
    private final List<CoordinatePair> occupiedCoordinates = new ArrayList<>();

    public void initGame(int size) {
        gameValidator.isValidBattleFieldSize(size);
        currentGame = new Game(size);

        // Create two players with random firing strategy
        Player player1 = new Player("Player 1", new RandomFiringStrategy(), 0, size / 2 - 1, size);
        Player player2 = new Player("Player 2", new RandomFiringStrategy(), size / 2, size - 1, size);
        currentGame.getBattleField().addPlayer(player1);
        currentGame.getBattleField().addPlayer(player2);

        System.out.println("Game initialized with battlefield size: " + size);
    }

    public void addShip(String name, int size, int x, int y, int playerIndex, int battlefieldSize) {
        try {
            ShipPositionValidator validator = new ShipPositionValidator(occupiedCoordinates);
            Ship ship = ShipFactory.createShip(name, size, x, y, currentGame.getBattleField().getSize(), validator);
            List<Player> players = currentGame.getBattleField().getPlayers();
            if (playerIndex >= 0 && playerIndex < players.size()) {
                players.get(playerIndex).addShip(ship);
                // Track occupied coordinates
                for (int i = ship.getTopLeft().getX(); i <= ship.getBottomRight().getX(); i++) {
                    for (int j = ship.getBottomRight().getY(); j <= ship.getTopLeft().getY(); j++) {
                        occupiedCoordinates.add(new CoordinatePair(i, j));
                    }
                }
                System.out.println("Ship '" + name + "' added for " + players.get(playerIndex).getName());
            } else {
                // Add to first player by default
                players.get(0).addShip(ship);
                System.out.println("Ship '" + name + "' added for " + players.get(0).getName());
            }
        } catch (OutOfAreaException | PositionOverlapException e) {
            System.err.println("Failed to add ship: " + e.getMessage());
        }
    }

    public void viewBattleField() {
        if (currentGame != null && currentGame.getBattleField() != null) {
            System.out.println("\n=== Battlefield ===");
            currentGame.getBattleField().viewBattleField();
            System.out.println("==================\n");
        }
    }

    public void startGame() {
        if (currentGame == null) {
            System.err.println("Game not initialized.");
            return;
        }
        currentGame.setGameStatus(GameStatus.IN_PROGRESS);
        System.out.println("Game started!");

        // Play turns until a player has no ships remaining
        int maxTurns = 100;
        int turn = 0;
        while (turn < maxTurns) {
            turn++;
            boolean gameOver = playTurn();
            if (gameOver) break;
        }
    }

    public boolean playTurn() {
        BattleField bf = currentGame.getBattleField();
        List<Player> players = bf.getPlayers();
        if (players.size() < 2) return true;

        Player attacker = players.get(0);
        Player defender = players.get(1);

        // Player 1 attacks Player 2
        CoordinatePair target = attacker.fire(bf);
        currentGame.addReplayLog(attacker.getName() + " fires at " + target);
        boolean hit = processAttack(target, defender);
        if (hit) {
            currentGame.addReplayLog("HIT!");
            System.out.println(attacker.getName() + " fires at " + target + " - HIT!");
        } else {
            currentGame.addReplayLog("MISS");
            System.out.println(attacker.getName() + " fires at " + target + " - MISS");
        }

        if (defender.remainingShips() == 0) {
            currentGame.setGameStatus(GameStatus.GAME_OVER);
            System.out.println(attacker.getName() + " wins!");
            return true;
        }

        // Player 2 attacks Player 1
        target = defender.fire(bf);
        currentGame.addReplayLog(defender.getName() + " fires at " + target);
        hit = processAttack(target, attacker);
        if (hit) {
            currentGame.addReplayLog("HIT!");
            System.out.println(defender.getName() + " fires at " + target + " - HIT!");
        } else {
            currentGame.addReplayLog("MISS");
            System.out.println(defender.getName() + " fires at " + target + " - MISS");
        }

        if (attacker.remainingShips() == 0) {
            currentGame.setGameStatus(GameStatus.GAME_OVER);
            System.out.println(defender.getName() + " wins!");
            return true;
        }

        return false;
    }

    private boolean processAttack(CoordinatePair target, Player defender) {
        for (Ship ship : defender.shipFleet) {
            if (!ship.isDestroyed() && ship.isHit(target)) {
                ship.setDestroyed(true);
                return true;
            }
        }
        return false;
    }

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
}