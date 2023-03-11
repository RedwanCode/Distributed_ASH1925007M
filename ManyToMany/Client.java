
package ManyToMany;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket server = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter out = new PrintWriter(server.getOutputStream(), true);
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            
            new Thread(new Receiver(in)).start();
            
            while (true) {
                String message = scanner.nextLine();
                out.println(name + ": " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}