package com.game.gui;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameGUITest {

    @Test
    public void testGameGUIInitialization() {
        GameGUI gui = new GameGUI();
        assertNotNull(gui, "GameGUI should be initialized successfully.");
    }

    @Test
    public void testBattlefieldPanelExists() {
        GameGUI gui = new GameGUI();
        JFrame frame = (JFrame) SwingUtilities.getRoot(gui);
        assertNotNull(frame.getContentPane().getComponent(0), "Battlefield panel should exist.");
    }

    @Test
    public void testGridButtonsInitialization() {
        GameGUI gui = new GameGUI();
        JPanel battlefieldPanel = (JPanel) gui.getBattlefieldPanel();
        assertEquals(100, battlefieldPanel.getComponentCount(), "Battlefield panel should contain 100 buttons.");
    }

    @Test
    public void testUpdateBattleField() {
        GameGUI gui = new GameGUI();
        BattleField mockBattleField = new BattleField();
        gui.updateBattleField(mockBattleField);
        // Add assertions to verify the GUI updates correctly based on the battlefield state
    }

    @Test
    public void testWindowProperties() {
        GameGUI gui = new GameGUI();
        JFrame frame = (JFrame) SwingUtilities.getRoot(gui);
        assertEquals("BattleShip Game", frame.getTitle(), "Window title should be 'BattleShip Game'.");
        assertEquals(800, frame.getWidth(), "Window width should be 800.");
        assertEquals(600, frame.getHeight(), "Window height should be 600.");
    }
}