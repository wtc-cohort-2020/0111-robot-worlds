package Server;

import Server.Server;

import java.net.*;
import java.io.*;
import java.util.Arrays;

public class MultiServers {
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        World world = new World();
        System.out.println("Obstacles");
        for(Obstacle obstacle: world.getObstacles()){
            System.out.println("("+obstacle.getBottomLeftX()+","+obstacle.getBottomLeftY()+")");
        }
        System.out.println("\nPits\n");
        for(Pit pit: world.getPits()){
            System.out.println("("+pit.getBottomLeftX()+","+pit.getBottomLeftY()+")");
        }
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