package com.game.network;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    private static final int PORT = 12345;
    private ServerSocket serverSocket;
    private ExecutorService clientPool;

    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT);
        clientPool = Executors.newFixedThreadPool(10);
    }

    public void start() {
        System.out.println("Server started on port " + PORT);
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                clientPool.execute(new ClientHandler(clientSocket));
            } catch (IOException e) {
                System.err.println("Error accepting client connection: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Server().start();
        } catch (IOException e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("Welcome to the Battleship game server!");
            String input;
            while ((input = in.readLine()) != null) {
                System.out.println("Received: " + input);
                out.println("Echo: " + input);
            }
        } catch (IOException e) {
            System.err.println("Client connection error: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}