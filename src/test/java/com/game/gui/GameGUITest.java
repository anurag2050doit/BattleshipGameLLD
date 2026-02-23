package com.game.gui;

import com.game.entity.BattleField;
import com.game.state.GameOverState;
import com.game.state.InitializedState;
import com.game.state.InProgressState;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameGUITest {

    @Test
    public void testGameGUIInitialization() {
        BattleField playerBattleField = new BattleField(6);
        BattleField opponentBattleField = new BattleField(6);
        GameGUI gameGUI = new GameGUI(playerBattleField, opponentBattleField);
        assertNotNull(gameGUI);
    }

    @Test
    public void testUpdateGameStateToInitialized() {
        BattleField playerBattleField = new BattleField(6);
        BattleField opponentBattleField = new BattleField(6);
        GameGUI gameGUI = new GameGUI(playerBattleField, opponentBattleField);
        gameGUI.updateGameState(new InitializedState());
        JLabel statusLabel = (JLabel) SwingUtilities.getAncestorOfClass(JLabel.class, gameGUI);
        assertEquals("Game Initialized", statusLabel.getText());
    }

    @Test
    public void testUpdateGameStateToInProgress() {
        BattleField playerBattleField = new BattleField(6);
        BattleField opponentBattleField = new BattleField(6);
        GameGUI gameGUI = new GameGUI(playerBattleField, opponentBattleField);
        gameGUI.updateGameState(new InProgressState());
        JLabel statusLabel = (JLabel) SwingUtilities.getAncestorOfClass(JLabel.class, gameGUI);
        assertEquals("Game In Progress", statusLabel.getText());
    }

    @Test
    public void testUpdateGameStateToGameOver() {
        BattleField playerBattleField = new BattleField(6);
        BattleField opponentBattleField = new BattleField(6);
        GameGUI gameGUI = new GameGUI(playerBattleField, opponentBattleField);
        gameGUI.updateGameState(new GameOverState());
        JLabel statusLabel = (JLabel) SwingUtilities.getAncestorOfClass(JLabel.class, gameGUI);
        assertEquals("Game Over", statusLabel.getText());
    }

    @Test
    public void testRenderBattlefield() {
        BattleField playerBattleField = new BattleField(6);
        BattleField opponentBattleField = new BattleField(6);
        playerBattleField.placeShip(0, 0);
        playerBattleField.hit(0, 0);
        GameGUI gameGUI = new GameGUI(playerBattleField, opponentBattleField);
        JPanel battlefieldPanel = (JPanel) SwingUtilities.getAncestorOfClass(JPanel.class, gameGUI);
        JButton cell = (JButton) battlefieldPanel.getComponent(0);
        assertEquals("java.awt.Color[r=255,g=0,b=0]", cell.getBackground().toString());
    }
}