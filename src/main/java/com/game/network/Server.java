package com.game.network;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    private static final int PORT = 8080;
    private ServerSocket serverSocket;
    private ExecutorService clientHandlerPool;

    public Server() {
        clientHandlerPool = Executors.newFixedThreadPool(10);
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(PORT);
            logger.info("Server started on port {}", PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("New client connected: {}", clientSocket.getInetAddress());
                clientHandlerPool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            logger.error("Error starting server: {}", e.getMessage());
        } finally {
            stop();
        }
    }

    public void stop() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            clientHandlerPool.shutdown();
            logger.info("Server stopped.");
        } catch (IOException e) {
            logger.error("Error stopping server: {}", e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    logger.info("Received: {}", inputLine);
                    out.println("Echo: " + inputLine);
                }
            } catch (IOException e) {
                logger.error("Error handling client: {}", e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    logger.error("Error closing client socket: {}", e.getMessage());
                }
            }
        }
    }
}