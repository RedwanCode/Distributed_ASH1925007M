
package ManyToMany;

import java.io.BufferedReader;
import java.io.IOException;

class Receiver implements Runnable {
    private BufferedReader in;
    
    public Receiver(BufferedReader in) {
        this.in = in;
    }
    
    public void run() {
        try {
            while (true) {
                String message = in.readLine();
                if (message == null) {
                    break;
                }
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
