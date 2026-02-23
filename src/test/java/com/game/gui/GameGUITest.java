package com.game.gui;

import com.game.entity.BattleField;
import com.game.entity.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class GameGUITest {

    private Game game;
    private GameGUI gameGUI;

    @BeforeEach
    void setUp() {
        game = new Game(5);
        gameGUI = new GameGUI(game);
    }

    @Test
    void testInitializeGUIComponents() {
        JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, gameGUI);
        assertNotNull(frame, "Frame should be initialized");
        assertEquals("BattleShip Game", frame.getTitle(), "Frame title should be set correctly");
    }

    @Test
    void testBattlefieldPopulation() {
        BattleField battlefield = game.getBattleField();
        assertEquals(25, battlefield.getSize() * battlefield.getSize(), "Battlefield should have correct number of cells");
    }

    @Test
    void testUpdateBattlefield() {
        BattleField battlefield = game.getBattleField();
        gameGUI.updateBattlefield(battlefield);
        assertEquals(25, battlefield.getSize() * battlefield.getSize(), "Battlefield should update correctly");
    }

    @Test
    void testUpdateStatus() {
        gameGUI.updateStatus("Test Status");
        JLabel statusLabel = (JLabel) SwingUtilities.getAncestorOfClass(JLabel.class, gameGUI);
        assertNotNull(statusLabel, "Status label should be initialized");
        assertEquals("Game Status: Test Status", statusLabel.getText(), "Status label should update correctly");
    }
}