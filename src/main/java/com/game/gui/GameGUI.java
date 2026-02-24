package com.game.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.*;

public class GameGUI extends JFrame {

    private JPanel battlefieldPanel;
    private JLabel gameStatusLabel;

    public GameGUI() {
        setTitle("BattleShip Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeBattlefield();
        initializeGameStatus();

        setVisible(true);
    }

    private void initializeBattlefield() {
        battlefieldPanel = new JPanel();
        battlefieldPanel.setLayout(new GridLayout(10, 10));
        battlefieldPanel.setBorder(BorderFactory.createTitledBorder("Battlefield"));

        for (int i = 0; i < 100; i++) {
            JLabel cell = new JLabel();
            cell.setPreferredSize(new Dimension(50, 50));
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cell.setTransferHandler(new TransferHandler("text"));
            battlefieldPanel.add(cell);
        }

        DragSource ds = new DragSource();
        ds.createDefaultDragGestureRecognizer(battlefieldPanel, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                // Drag-and-drop logic for ship placement
            }
        });

        add(battlefieldPanel, BorderLayout.CENTER);
    }

    private void initializeGameStatus() {
        gameStatusLabel = new JLabel("Game Status: Waiting for players...");
        gameStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameStatusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(gameStatusLabel, BorderLayout.SOUTH);
    }

    public void updateGameStatus(String status) {
        gameStatusLabel.setText("Game Status: " + status);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::new);
    }
}