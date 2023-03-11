
package OneToMany;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private ArrayList<PrintWriter> clients;

    public Server() {
        clients = new ArrayList<PrintWriter>();
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                clients.add(out);

                Thread t = new Thread(new ClientHandler(clientSocket, this));
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    public void broadcast(String message) {
        for (PrintWriter client : clients) {
            client.println(message);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(12345);
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Server server;

    public ClientHandler(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String message;

            while ((message = in.readLine()) != null) {
                System.out.println("Received message from client " + clientSocket + ": " + message);
                server.broadcast(message);
            }

            System.out.println("Client disconnected: " + clientSocket);
        } catch (IOException e) {
            System.out.println("Error handling client " + clientSocket + ": " + e.getMessage());
        }
    }
}
