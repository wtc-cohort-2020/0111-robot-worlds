package Server;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;

public class World {
    Hashtable<String,Robot> allRobots = new Hashtable<>();
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    ArrayList<Pit> pits = new ArrayList<>();

    int worldWidth;
    int worldHeight;

    public World(){
        worldHeight = 200;
        worldWidth = 200;
        CreateObstacles();
        CreatePits();
    }

    public void AddRobot(Robot robot, String name){
        allRobots.put(name, robot);
    }

    public void RemoveRobot(String name){
        allRobots.remove(name);
    }

    private void CreateObstacles(){
        while (obstacles.size()<10){
            boolean isBlocked = false;
            int randomX = ThreadLocalRandom.current().nextInt(-(worldWidth/2)/5,
                    (worldWidth/2)/5);
            int randomY = ThreadLocalRandom.current().nextInt(-(worldHeight/2)/5,
                    (worldHeight/2)/5);

            Obstacle newObstacle = new Obstacle(randomX*5, randomY*5, 5);

            for(Obstacle obstFromList: obstacles){
                if(obstFromList.blocksPosition(randomX*5, randomY*5)){
                    isBlocked = true;
                    break;
                }
            }

            if(!isBlocked){
                obstacles.add(newObstacle);
            }
        }
    }

    private void CreatePits(){
        while (pits.size()<10){
            boolean isBlocked = false;
            int randomX = ThreadLocalRandom.current().nextInt(-(worldWidth/2)/5,
                    (worldWidth/2)/5);
            int randomY = ThreadLocalRandom.current().nextInt(-(worldHeight/2)/5,
                    (worldHeight/2)/5);

            Pit newPit = new Pit(randomX*5, randomY*5, 5);

            for(Pit pitFromList: pits){
                if(pitFromList.blocksPosition(randomX*5, randomY*5)){
                    isBlocked = true;
                    break;
                }
            }

            for(Obstacle obstFromList: obstacles){
                if(obstFromList.blocksPosition(randomX*5, randomY*5)){
                    isBlocked = true;
                    break;
                }
            }

            if(!isBlocked){
                pits.add(newPit);
            }
        }
    }
}