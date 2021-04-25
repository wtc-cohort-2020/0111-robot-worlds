package Server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class World {
    ArrayList<Robot> allRobots = new ArrayList<>();
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    ArrayList<Pit> pits = new ArrayList<>();
    ArrayList<Mine> mines = new ArrayList<>();

    private int worldWidth;
    private int worldHeight;
    private int visibility;
    private int repairShield;
    private int reload;
    private int steMineTime;
    private int maxShield;

    HashMap<String, Integer> sniperRobot;
    HashMap<String, Integer> pistolRobot;
    HashMap<String, Integer> standardRobot;

    public World(){
        try {
            File input = new File("src/main/java/Server/WorldSpecs.json");
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            //Extracting basic fields

            worldHeight = fileObject.get("height").getAsInt();
            worldWidth = fileObject.get("width").getAsInt();
            visibility = fileObject.get("visibility").getAsInt();
            repairShield = fileObject.get("repair").getAsInt();
            reload = fileObject.get("reload").getAsInt();
            steMineTime = fileObject.get("mine").getAsInt();
            maxShield = fileObject.get("maxShield").getAsInt();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        createDesignedObstacles();
        createDesignedPits();
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

    public void AddRobot(Robot robot){
        allRobots.add(robot);
    }

    public int getWorldWidth(){
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public void addMine(Mine mine){
        mines.add(mine);
    }

    public void removeMine(Mine mine){
        mines.remove(mine);
    }

    public ArrayList<Mine> getMines(){
        return mines;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getReload() {
        return reload;
    }

    public int getMaxShield() {
        return maxShield;
    }

    public int getSteMineTime() {
        return steMineTime;
    }

    public int getRepairShield() {
        return repairShield;
    }
}