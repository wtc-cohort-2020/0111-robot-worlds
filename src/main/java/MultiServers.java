

import java.net.*;
import java.io.*;

public class MultiServers {
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        ServerSocket s = new ServerSocket( Server.PORT);
        System.out.println("Server running & waiting for client connections.");
        while(true) {
            try {
                Socket socket = s.accept();
                System.out.println("Connection: " + socket);

                Runnable r = new Server(socket);
                Thread task = new Thread(r);
                task.start();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}