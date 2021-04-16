package Server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class World {
    ArrayList<Robot> allRobots = new ArrayList<>();
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    ArrayList<Pit> pits = new ArrayList<>();
    private JsonObject fileObject;


    int worldWidth;
    int worldHeight;

    HashMap<String, Integer> sniperRobot;
    HashMap<String, Integer> pistolRobot;
    HashMap<String, Integer> standardRobot;

    public World(){
        try {
            File input = new File("src/main/java/Server/WorldSpecs.json");
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            this.fileObject = fileElement.getAsJsonObject();

            //Extracting basic fields

            worldHeight = fileObject.get("height").getAsInt();
            worldWidth = fileObject.get("width").getAsInt();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        CreateObstacles();
        CreatePits();
        setRobotParams();
    }

    public World(ArrayList<Pit> pits, ArrayList<Obstacle> obstacles){
        this.pits = pits;
        this.obstacles = obstacles;
    }

    public void setRobotParams() {
        //Sets config details based on robot type.

        sniperRobot = new HashMap<>();
        sniperRobot.put("shot-distance",4);
        sniperRobot.put("shots",2);
        sniperRobot.put("shield-strength",3);


        pistolRobot = new HashMap<>();
        pistolRobot.put("shot-distance",2);
        pistolRobot.put("shots",4);
        pistolRobot.put("shield-strength",3);


        standardRobot = new HashMap<>();
        standardRobot.put("shot-distance",3);
        standardRobot.put("shots",3);
        standardRobot.put("shield-strength",3);
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

    public ArrayList<Robot> getRobots() { return allRobots; }

    public HashMap<String, Integer> getSniper() {
        return sniperRobot;
    }

    public HashMap<String, Integer> getStandard() {
        return standardRobot;
    }

    public HashMap<String, Integer> getPistol() {
        return pistolRobot;
    }

    public void AddRobot(Robot robot){
        allRobots.add(robot);
    }

    public int getWorldWidth(){
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }
}