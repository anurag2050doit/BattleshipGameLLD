package com.game.network;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    @Test
    void testServerClientCommunication() throws IOException {
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

            out.println("Test Message");
            String response = in.readLine();
            assertNotNull(response);
            assertTrue(response.contains("Echo"));
        }
    }

    @Test
    void testServerHandlesMultipleClients() throws IOException {
        Thread serverThread = new Thread(() -> {
            try {
                new Server().start();
            } catch (IOException e) {
                fail("Server failed to start: " + e.getMessage());
            }
        });
        serverThread.start();

        try (Socket client1 = new Socket("localhost", 12345);
             Socket client2 = new Socket("localhost", 12345);
             BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
             PrintWriter out1 = new PrintWriter(client1.getOutputStream(), true);
             BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
             PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true)) {

            out1.println("Client1 Message");
            out2.println("Client2 Message");

            String response1 = in1.readLine();
            String response2 = in2.readLine();

            assertNotNull(response1);
            assertNotNull(response2);
            assertTrue(response1.contains("Echo"));
            assertTrue(response2.contains("Echo"));
        }
    }

    @Test
    void testServerHandlesClientDisconnection() throws IOException {
        Thread serverThread = new Thread(() -> {
            try {
                new Server().start();
            } catch (IOException e) {
                fail("Server failed to start: " + e.getMessage());
            }
        });
        serverThread.start();

        try (Socket clientSocket = new Socket("localhost", 12345);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("Disconnect Test");
            clientSocket.close();

            assertTrue(clientSocket.isClosed());
        }
    }
}