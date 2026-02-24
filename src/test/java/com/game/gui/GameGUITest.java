package com.game.gui;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameGUITest {

    @Test
    public void testGameGUIInitialization() {
        SwingUtilities.invokeLater(() -> {
            GameGUI gui = new GameGUI();
            assertNotNull(gui);
            assertNotNull(gui.getContentPane().getComponent(0));
            assertTrue(gui.getContentPane().getComponent(0) instanceof JPanel);
            assertTrue(gui.getContentPane().getComponent(1) instanceof JLabel);
        });
    }

    @Test
    public void testUpdateGameStatus() {
        SwingUtilities.invokeLater(() -> {
            GameGUI gui = new GameGUI();
            gui.updateGameStatus("Player A's Turn");
            JLabel statusLabel = (JLabel) gui.getContentPane().getComponent(1);
            assertEquals("Game Status: Player A's Turn", statusLabel.getText());
        });
    }

    @Test
    public void testBattlefieldPanelInitialization() {
        SwingUtilities.invokeLater(() -> {
            GameGUI gui = new GameGUI();
            JPanel battlefieldPanel = (JPanel) gui.getContentPane().getComponent(0);
            assertEquals(100, battlefieldPanel.getComponentCount());
            for (Component component : battlefieldPanel.getComponents()) {
                assertTrue(component instanceof JLabel);
                JLabel cell = (JLabel) component;
                assertNotNull(cell.getTransferHandler());
            }
        });
    }

    @Test
    public void testDragAndDropFunctionality() {
        SwingUtilities.invokeLater(() -> {
            GameGUI gui = new GameGUI();
            JPanel battlefieldPanel = (JPanel) gui.getContentPane().getComponent(0);
            DragSource ds = new DragSource();
            assertNotNull(ds.createDefaultDragGestureRecognizer(battlefieldPanel, DnDConstants.ACTION_MOVE, dge -> {
                assertNotNull(dge);
            }));
        });
    }

    @Test
    public void testGameStatusLabelInitialization() {
        SwingUtilities.invokeLater(() -> {
            GameGUI gui = new GameGUI();
            JLabel gameStatusLabel = (JLabel) gui.getContentPane().getComponent(1);
            assertEquals("Game Status: Waiting for players...", gameStatusLabel.getText());
            assertEquals(SwingConstants.CENTER, gameStatusLabel.getHorizontalAlignment());
        });
    }
}