package com.game.exception;

public class OutOfAreaException extends Exception {
    public OutOfAreaException() {
        super("Coordinates are out of the allowed area.");
    }

    public static void logDetailedMessage(String detailedMessage) {
        // Log the detailed message securely (e.g., to a file or monitoring system)
        System.err.println("DEBUG: " + detailedMessage); // Replace with actual logging framework
    }
}