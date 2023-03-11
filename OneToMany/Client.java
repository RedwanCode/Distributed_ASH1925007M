
package OneToMany;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String hostname, int port) {
        try {
            socket = new Socket(hostname, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server: " + socket);
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }

    public void start() {
        try (BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {
            String message;

            while ((message = consoleIn.readLine()) != null) {
                out.println(message);
                System.out.println("Sent message to server: " + message);
            }

            socket.close();
            System.out.println("Disconnected from server: " + socket);
        } catch (IOException e) {
            System.out.println("Error sending message to server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client1 = new Client("localhost", 12345);
        client1.start();

        Client client2 = new Client("localhost", 12345);
        client2.start();

        Client client3 = new Client("localhost", 12345);
        client3.start();
    }
}
