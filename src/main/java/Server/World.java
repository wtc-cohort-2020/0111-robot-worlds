package Server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
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
        File input = new File("src/main/java/WorldSpecs.json");
        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();

        //Extracting basic fields

        worldHeight = fileObject.get("height").getAsInt();
        worldWidth = fileObject.get("height").getAsInt();
        CreateObstacles();
        CreatePits();
    }

    public World(ArrayList<Pit> pits,ArrayList<Obstacle> obstacles){
        this.pits = pits;
        this.obstacles = obstacles;
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

    public ArrayList<Obstacle> getObstacles(){
        return obstacles;
    }

    public ArrayList<Pit> getPits(){
        return pits;
    }
}