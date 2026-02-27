package com.game.entity;

public class GameSettings {
    private int gridSize;
    private int numberOfShips;
    private int[] shipSizes;

    public GameSettings(int gridSize, int numberOfShips, int[] shipSizes) {
        this.gridSize = gridSize;
        this.numberOfShips = numberOfShips;
        this.shipSizes = shipSizes;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public int getNumberOfShips() {
        return numberOfShips;
    }

    public void setNumberOfShips(int numberOfShips) {
        this.numberOfShips = numberOfShips;
    }

    public int[] getShipSizes() {
        return shipSizes;
    }

    public void setShipSizes(int[] shipSizes) {
        this.shipSizes = shipSizes;
    }
}