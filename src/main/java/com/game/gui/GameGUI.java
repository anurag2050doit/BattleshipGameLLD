package com.game.gui;

import javax.swing.*;
import java.awt.*;
import com.game.entity.BattleField;

public class GameGUI {

    private JFrame frame;
    private JPanel battlefieldPanel;

    public GameGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("BattleShip Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        battlefieldPanel = new JPanel();
        battlefieldPanel.setLayout(new GridLayout(10, 10));
        frame.add(battlefieldPanel, BorderLayout.CENTER);

        JButton[][] gridButtons = new JButton[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gridButtons[i][j] = new JButton();
                battlefieldPanel.add(gridButtons[i][j]);
            }
        }

        frame.setVisible(true);
    }

    public void updateBattleField(BattleField battleField) {
        // Logic to update the GUI based on the battlefield state
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::new);
    }
}