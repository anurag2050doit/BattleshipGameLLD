package com.game.network;

import org.junit.jupiter.api.*;
import java.io.*;
import java.net.*;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Server server;
    private Thread serverThread;
    private Client client;

    @BeforeEach
    void setUp() {
        server = new Server();
        serverThread = new Thread(server::start);
        serverThread.start();
        client = new Client();
    }

    @AfterEach
    void tearDown() {
        client.disconnect();
        server.stop();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    void testClientConnectsToServer() {
        assertDoesNotThrow(() -> client.connect());
    }

    @Test
    void testClientSendsAndReceivesMessages() throws IOException {
        new Thread(client::connect).start();

        try (Socket clientSocket = new Socket("localhost", 8080);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            out.println("Hello from Test Client");
            String response = in.readLine();
            assertEquals("Echo: Hello from Test Client", response);
        }
    }

    @Test
    void testClientHandlesServerDisconnection() {
        client.connect();
        server.stop();
        assertTrue(client.disconnect());
    }
}