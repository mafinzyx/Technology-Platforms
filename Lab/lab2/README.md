# Java Multi-Client Chat Server
This project is a simple multi-client chat server implemented in Java. It allows multiple clients to connect, send messages, and interact in real time. The server efficiently manages client connections using multi-threading and executor services to ensure smooth communication.

## 📄 Features:
✅ Multi-client support using ExecutorService

✅ Thread-safe client handling with CopyOnWriteArrayList

✅ Server-side console with commands (status, exit)

✅ Broadcast messaging to all connected clients

✅ Graceful shutdown handling

## 🗂️ Project Structure
- **Server.java** – Manages client connections, broadcasts messages, and handles server commands.

- **ClientHandler.java** – Handles individual client communication.

- **Client.java** – Simple client-side application to connect and chat.

## ⚙️ Compilation:
### To run the server:

``` java -jar target/Lab-1.0-SNAPSHOT-jar-with-dependencies.jar```

### To run the client:
``` java -cp target/Lab-1.0-SNAPSHOT-jar-with-dependencies.jar org.example.Client ```

## </> Commands:
### On Server:

``` status ``` → Show connected clients

``` exit ``` → Shut down the server

### On Client:

``` exit ``` → Leave the chat