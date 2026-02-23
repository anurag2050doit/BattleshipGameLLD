package com.game.gui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GameGUITest {

    private GameGUI gameGUI;

    @BeforeEach
    void setUp() {
        gameGUI = new GameGUI(10);
    }

    @Test
    void testInitialTurn() {
        JLabel statusLabel = getStatusLabel();
        assertEquals("Player A's Turn", statusLabel.getText(), "Initial turn should be Player A's.");
    }

    @Test
    void testValidMove() {
        JButton[][] playerAGrid = getPlayerAGrid();
        JButton button = playerAGrid[0][0];
        button.doClick();
        assertEquals(Color.RED, button.getBackground(), "Button should turn red after a valid move.");
        JLabel statusLabel = getStatusLabel();
        assertEquals("Player B's Turn", statusLabel.getText(), "Turn should switch to Player B.");
    }

    @Test
    void testInvalidMoveOutOfTurn() {
        JButton[][] playerBGrid = getPlayerBGrid();
        JButton button = playerBGrid[0][0];
        button.doClick();
        JLabel statusLabel = getStatusLabel();
        assertEquals("Player A's Turn", statusLabel.getText(), "Turn should remain Player A's after an invalid move.");
    }

    @Test
    void testInvalidMoveAlreadyHitCell() {
        JButton[][] playerAGrid = getPlayerAGrid();
        JButton button = playerAGrid[0][0];
        button.doClick();
        button.doClick();
        assertEquals(Color.RED, button.getBackground(), "Button color should remain red after clicking an already hit cell.");
    }

    private JLabel getStatusLabel() {
        for (Component component : gameGUI.frame.getContentPane().getComponents()) {
            if (component instanceof JLabel) {
                return (JLabel) component;
            }
        }
        throw new IllegalStateException("Status label not found.");
    }

    private JButton[][] getPlayerAGrid() {
        return gameGUI.playerAGrid;
    }

    private JButton[][] getPlayerBGrid() {
        return gameGUI.playerBGrid;
    }
}