package org.example;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 5001;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    static final CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();
    private static ServerSocket serverSocket;
    private static final ExecutorService clientPool = Executors.newCachedThreadPool();


    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(Server::shutdown));

        ExecutorService consoleExecutor = Executors.newSingleThreadExecutor();
        consoleExecutor.execute(Server::handleServerConsole);
        try  {
            serverSocket = new ServerSocket(PORT);
            log("Chat server started on port " + PORT);
            log("Waiting for client connections...");

            while (!serverSocket.isClosed()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    log("New client connected from " + clientSocket.getInetAddress());

                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    clients.add(clientHandler);
                    clientPool.execute(clientHandler);
                } catch (IOException e) {
                    log("Error accepting client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            log("Server error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // only after exit command we are here
//            System.out.println("WE ARE IN FINALLY BLOCK!");
            consoleExecutor.shutdown();
            if (!serverSocket.isClosed()){ //cuz rundown hook does this, just in case
                shutdown();
            }
        }
    }

    private static void handleServerConsole() {
        System.out.println("List of comands:\n \tstatus\n\texit");
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = scanner.nextLine().trim().toLowerCase();

                switch (command) {
                    case "status":
                        log("Connected clients: " + clients.size());
                        for (ClientHandler client : clients){
                            log('\t' + client.getNickname());
                        }
                        break;

                    case "exit":
                        log("Exit command received.");
                        shutdown();
                        System.exit(0);
                        break;

                    default:
                        log("Unknown command.");
                }
            }
        }
    }


    public static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public static void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("[" + timestamp + "] " + message);
    }

    private static void shutdown() {
        log("Server is shutting down...");
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            log("Error closing server socket: " + e.getMessage());
        }
        clientPool.shutdown();


        log("Server shutdown complete.");
    }

}



