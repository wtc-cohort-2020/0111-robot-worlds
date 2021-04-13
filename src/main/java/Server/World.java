package Server;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class World {
    private ArrayList<Robot> allRobots = new ArrayList<>();
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private ArrayList<Pit> pits = new ArrayList<>();


    int worldWidth;
    int worldHeight;

    public World(){
        worldHeight = 200;
        worldWidth = 200;
        CreateObstacles();
        CreatePits();
    }

    public World(ArrayList<Pit> pits,ArrayList<Obstacle> obstacles){
        this.pits = pits;
        this.obstacles = obstacles;
    }

    public void AddRobot(Robot robot){
        allRobots.add(robot);
    }

    public void RemoveRobot(Robot robot){
        allRobots.remove(robot);
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

    public ArrayList<Obstacle> getObstacles(){
        return obstacles;
    }

    public ArrayList<Pit> getPits(){
        return pits;
    }

    public ArrayList<Robot> getAllRobots(){
        return allRobots;
    }
}