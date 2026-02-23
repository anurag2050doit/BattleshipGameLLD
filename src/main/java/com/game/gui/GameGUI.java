package com.game.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {

    private JFrame frame;
    private JPanel playerAPanel;
    private JPanel playerBPanel;
    private JLabel statusLabel;
    private JButton[][] playerAGrid;
    private JButton[][] playerBGrid;
    private boolean isPlayerATurn;

    public GameGUI(int gridSize) {
        frame = new JFrame("Battleship Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        playerAPanel = new JPanel(new GridLayout(gridSize, gridSize));
        playerBPanel = new JPanel(new GridLayout(gridSize, gridSize));
        statusLabel = new JLabel("Player A's Turn", SwingConstants.CENTER);
        playerAGrid = new JButton[gridSize][gridSize];
        playerBGrid = new JButton[gridSize][gridSize];
        isPlayerATurn = true;

        initializeGrid(playerAGrid, playerAPanel, "Player A");
        initializeGrid(playerBGrid, playerBPanel, "Player B");

        frame.add(playerAPanel, BorderLayout.WEST);
        frame.add(playerBPanel, BorderLayout.EAST);
        frame.add(statusLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void initializeGrid(JButton[][] grid, JPanel panel, String player) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                JButton button = new JButton();
                button.setBackground(Color.BLUE);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(button, player);
                    }
                });
                grid[i][j] = button;
                panel.add(button);
            }
        }
    }

    private void handleButtonClick(JButton button, String player) {
        if ((isPlayerATurn && player.equals("Player A")) || (!isPlayerATurn && player.equals("Player B"))) {
            if (button.getBackground() == Color.BLUE) {
                button.setBackground(Color.RED);
                statusLabel.setText((isPlayerATurn ? "Player B's Turn" : "Player A's Turn"));
                isPlayerATurn = !isPlayerATurn;
            } else {
                JOptionPane.showMessageDialog(frame, "This cell is already hit!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "It's not your turn!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new GameGUI(10);
    }
}