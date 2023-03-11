
package ManyToMany;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ClientHandler extends Thread {
    private Socket client;
    
    public ClientHandler(Socket client) {
        this.client = client;
    }
    
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            while (true) {
                String message = in.readLine();
                if (message == null) {
                    break;
                }
                Server.broadcast(client, message);
            }
            
            client.close();
            Server.clients.remove(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}