package com.game.network;

import java.io.*;
import java.net.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;
    private Socket socket;

    public void connect() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            logger.info("Connected to server at {}:{}", SERVER_ADDRESS, SERVER_PORT);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

                String userInput;
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput);
                    logger.info("Server response: {}", in.readLine());
                }
            }
        } catch (IOException e) {
            logger.error("Error connecting to server: {}", e.getMessage());
        } finally {
            disconnect();
        }
    }

    public void disconnect() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            logger.info("Disconnected from server.");
        } catch (IOException e) {
            logger.error("Error disconnecting from server: {}", e.getMessage());
        }
    }
}