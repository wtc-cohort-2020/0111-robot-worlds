package Server;

import java.io.*;

public class RunServer {
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        World world = new World();
        System.out.println("Obstacles");
        for(Obstacle obstacle: world.getObstacles()){
            System.out.println("("+obstacle.getBottomLeftX()+","+obstacle.getBottomLeftY()+")");
        }
        System.out.println("\nPits");
        for(Pit pit: world.getPits()){
            System.out.println("("+pit.getBottomLeftX()+","+pit.getBottomLeftY()+")");
        }

        Runnable r = new AcceptClients(world);
        Thread task = new Thread(r);
        task.start();

        WorldCommands worldCommands = new WorldCommands(world);
        worldCommands.handleWorldCommands();

        System.exit(0);
    }
}