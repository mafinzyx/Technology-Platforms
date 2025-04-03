package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.example.Server.*;


public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }


    @Override
    public void run() {
//        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown)); //no need for hook cuz while shutting down executes finally
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            nickname = in.readLine();
            out.println("Welcome to the chat, " + nickname + "!");
            out.println("To exit just use the command: exit");
            System.out.println(nickname + " has joined the chat.");
            broadcast(nickname + " has joined the chat.", this);

            String message;
            while (!clientSocket.isClosed() && (message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                String formattedMessage = "[" + nickname + "]: " + message;
                System.out.println(formattedMessage);
                broadcast(formattedMessage, this);
                out.println("[SERVER]: Your message has been sent successfully.");
            }

        } catch (IOException e) {
            log("Error handling client connection: " + e.getMessage()); // TODO: ask if ok if handled, but occurs
        } finally {
            shutdown();
        }
    }

    public void shutdown(){
        try {
            if (!clientSocket.isClosed()){
                clientSocket.close();
                log( nickname +" connection was closed");
            }
            in.close();
            out.close();
        } catch (IOException e) {
            log("Error closing client socket: " + e.getMessage());
        }
        Server.clients.remove(this);
        broadcast(nickname + " has left the chat.", this);
    }


    void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }
}

