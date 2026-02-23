package com.game.gui;

import com.game.entity.BattleField;
import com.game.state.GameState;
import com.game.state.InProgressState;
import com.game.state.GameOverState;
import com.game.state.InitializedState;
import javax.swing.*;
import java.awt.*;

public class GameGUI {

    private JFrame frame;
    private JPanel battlefieldPanel;
    private JLabel statusLabel;
    private BattleField playerBattleField;
    private BattleField opponentBattleField;

    public GameGUI(BattleField playerBattleField, BattleField opponentBattleField) {
        this.playerBattleField = playerBattleField;
        this.opponentBattleField = opponentBattleField;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Battleship Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        battlefieldPanel = new JPanel(new GridLayout(playerBattleField.getSize(), playerBattleField.getSize()));
        statusLabel = new JLabel("Game Initialized", SwingConstants.CENTER);

        frame.add(battlefieldPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setVisible(true);

        renderBattlefield();
    }

    public void updateGameState(GameState gameState) {
        if (gameState instanceof InProgressState) {
            statusLabel.setText("Game In Progress");
        } else if (gameState instanceof GameOverState) {
            statusLabel.setText("Game Over");
        } else if (gameState instanceof InitializedState) {
            statusLabel.setText("Game Initialized");
        }
        renderBattlefield();
    }

    private void renderBattlefield() {
        battlefieldPanel.removeAll();
        for (int i = 0; i < playerBattleField.getSize(); i++) {
            for (int j = 0; j < playerBattleField.getSize(); j++) {
                JButton cell = new JButton();
                if (playerBattleField.isHit(i, j)) {
                    cell.setBackground(Color.RED);
                } else if (playerBattleField.hasShip(i, j)) {
                    cell.setBackground(Color.GRAY);
                } else {
                    cell.setBackground(Color.BLUE);
                }
                battlefieldPanel.add(cell);
            }
        }
        battlefieldPanel.revalidate();
        battlefieldPanel.repaint();
    }
}