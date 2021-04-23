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

    private void createDesignedObstacles() {
        //Make obstacle list empty
        Obstacle newObstacle = new Obstacle(0 ,-47,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(16 ,-47,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(32 ,-47,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(44 ,-47,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(44 ,8,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(44 ,33,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(44 ,58,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(32 ,73,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(16 ,83,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(0 ,93,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(0 ,11,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(-5 ,13,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(-9 ,14,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(-13 ,8,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(-12 ,-1,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(-7 ,-9,5);
        obstacles.add(newObstacle);
        newObstacle = new Obstacle(0 ,-15,5);
        obstacles.add(newObstacle);
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

    private void createDesignedPits() {
        //Make obstacle list empty
        Pit newPit = new Pit(-16,-47,5);
        pits.add(newPit);
        newPit =  new Pit(-32,-47,5);
        pits.add(newPit);
        newPit =  new Pit(-44,-47,5);
        pits.add(newPit);
        newPit =  new Pit(-44,-17,5);
        pits.add(newPit);
        newPit =  new Pit(-44,8,5);
        pits.add(newPit);
        newPit =  new Pit(-44,33,5);
        pits.add(newPit);
        newPit =  new Pit(-44,58,5);
        pits.add(newPit);
        newPit =  new Pit(-32,73,5);
        pits.add(newPit);
        newPit =  new Pit(-16,83,5);
        pits.add(newPit);
        newPit =  new Pit(-7,-9,5);
        pits.add(newPit);
        newPit =  new Pit(-12,-1,5);
        pits.add(newPit);
        newPit =  new Pit(-13,8,5);
        pits.add(newPit);
        newPit =  new Pit(-9,14,5);
        pits.add(newPit);
        newPit =  new Pit(-5,13,5);
        pits.add(newPit);
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