package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 5001;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your nickname: ");
            String nickname = scanner.nextLine();
            out.println(nickname);

            System.out.println("Connected to chat as " + nickname);

            Thread listenerThread = new Thread(() -> { // читаем с сокета
                try {
                    String response = null;
                    while (!socket.isClosed() && (response = in.readLine()) != null) {
                        System.out.println(response);
                    }
                    if (socket.isClosed()) {
                        System.out.println("Server closed the connection.");
                    } else if (response == null) {
                        System.out.println("Connection closed by the server.");
                    }

                } catch (IOException e) {
                    System.out.println("Connection lost.");
                } finally {
                    try {
                        if (!socket.isClosed()) {
                            socket.close();
                            System.out.println("Your socket was closed");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
            });

            listenerThread.start();


            String message;
            while (true) { // чтобы писать
                message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    out.println("exit");
                    System.out.println("Exiting chat...");
                    break;
                }
                out.println(message);
            }
            listenerThread.interrupt();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
