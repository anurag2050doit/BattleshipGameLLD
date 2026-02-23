package com.game;

import com.game.service.GameService;

public class BattleShipGameApplication {
    public static void main(String[] args) {
        GameService gameService = new GameService();
        gameService.initGame(6);
        gameService.addShip("SH1", 2, 1, 5, 0, 6);  // Add ship for Player 1
        gameService.addShip("SH2", 2, 4, 5, 1, 6);  // Add ship for Player 2
        gameService.viewBattleField();
        gameService.startGame();
        gameService.viewBattleField();
    }
}
