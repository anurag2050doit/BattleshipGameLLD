package com.game.network;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testClientConnectionToServer() throws IOException {
        Thread serverThread = new Thread(() -> {
            try {
                new Server().start();
            } catch (IOException e) {
                fail("Server failed to start: " + e.getMessage());
            }
        });
        serverThread.start();

        try (Socket clientSocket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            assertTrue(clientSocket.isConnected());
            out.println("Hello Server");
            String response = in.readLine();
            assertNotNull(response);
            assertTrue(response.contains("Echo"));
        }
    }

    @Test
    void testClientHandlesServerShutdown() throws IOException {
        Thread serverThread = new Thread(() -> {
            try {
                Server server = new Server();
                server.start();
            } catch (IOException e) {
                fail("Server failed to start: " + e.getMessage());
            }
        });
        serverThread.start();

        try (Socket clientSocket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            serverThread.interrupt();
            String response = in.readLine();
            assertNull(response);
        }
    }
}