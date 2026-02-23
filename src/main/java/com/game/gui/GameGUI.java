package com.game.gui;

import com.game.entity.BattleField;
import com.game.entity.Game;
import com.game.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GameGUI {

    private JFrame frame;
    private JPanel battlefieldPanel;
    private JLabel statusLabel;

    public GameGUI(Game game) {
        initialize(game);
    }

    private void initialize(Game game) {
        frame = new JFrame("BattleShip Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        battlefieldPanel = new JPanel();
        battlefieldPanel.setLayout(new GridLayout(game.getBattleField().getSize(), game.getBattleField().getSize()));
        populateBattlefield(game.getBattleField());

        statusLabel = new JLabel("Game Status: " + game.getStatus());
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        frame.add(battlefieldPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void populateBattlefield(BattleField battleField) {
        int size = battleField.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton cell = new JButton();
                cell.setBackground(Color.BLUE);
                battlefieldPanel.add(cell);
            }
        }
    }

    public void updateBattlefield(BattleField battleField) {
        battlefieldPanel.removeAll();
        populateBattlefield(battleField);
        battlefieldPanel.revalidate();
        battlefieldPanel.repaint();
    }

    public void updateStatus(String status) {
        statusLabel.setText("Game Status: " + status);
    }
}