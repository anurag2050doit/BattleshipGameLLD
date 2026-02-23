package com.game.exception;

public class PositionOverlapException extends Exception {
    public PositionOverlapException() {
        super("Position overlap detected. Please check the ship coordinates.");
    }

    public static void logDetailedMessage(String detailedMessage) {
        // Log the detailed message securely (e.g., to a file or monitoring system)
        System.err.println("DEBUG: " + detailedMessage); // Replace with actual logging framework
    }
}