
package ManyToMany;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static ArrayList<Socket> clients = new ArrayList<>();
    
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1234);
            System.out.println("Server started on port 1234");
            
            while (true) {
                Socket client = server.accept();
                clients.add(client);
                new ClientHandler(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void broadcast(Socket sender, String message) {
        for (Socket client : clients) {
            if (client != sender) {
                try {
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    out.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}