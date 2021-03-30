package Server;

import Server.Server;

import java.net.*;
import java.io.*;

public class MultiServers {
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        World world = new World();
        ServerSocket s = new ServerSocket( Server.PORT);
        System.out.println("Server.Server running & waiting for client connections.");
        while(true) {
            try {
                Socket socket = s.accept();
                System.out.println("Connection: " + socket);

                Runnable r = new Server(socket, world);
                Thread task = new Thread(r);
                task.start();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}