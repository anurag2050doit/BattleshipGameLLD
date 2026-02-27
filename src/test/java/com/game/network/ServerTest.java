package com.game.network;

import org.junit.jupiter.api.*;
import java.io.*;
import java.net.*;
import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
    private Server server;
    private Thread serverThread;

    @BeforeEach
    void setUp() {
        server = new Server();
        serverThread = new Thread(server::start);
        serverThread.start();
    }

    @AfterEach
    void tearDown() {
        server.stop();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    void testServerClientCommunication() throws IOException {
        try (Socket clientSocket = new Socket("localhost", 8080);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            out.println("Hello Server");
            String response = in.readLine();
            assertEquals("Echo: Hello Server", response);
        }
    }

    @Test
    void testServerHandlesMultipleClients() throws IOException, InterruptedException {
        Thread client1 = new Thread(() -> {
            try (Socket clientSocket = new Socket("localhost", 8080);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                out.println("Client1 Message");
                String response = in.readLine();
                assertEquals("Echo: Client1 Message", response);
            } catch (IOException e) {
                fail("Client1 failed: " + e.getMessage());
            }
        });

        Thread client2 = new Thread(() -> {
            try (Socket clientSocket = new Socket("localhost", 8080);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                out.println("Client2 Message");
                String response = in.readLine();
                assertEquals("Echo: Client2 Message", response);
            } catch (IOException e) {
                fail("Client2 failed: " + e.getMessage());
            }
        });

        client1.start();
        client2.start();

        client1.join();
        client2.join();
    }

    @Test
    void testServerHandlesClientDisconnection() throws IOException {
        try (Socket clientSocket = new Socket("localhost", 8080);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            out.println("Disconnecting Client");
            clientSocket.close();
            assertTrue(clientSocket.isClosed());
        }
    }
}