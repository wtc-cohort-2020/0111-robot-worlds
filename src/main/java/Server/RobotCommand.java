package Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RobotCommand {
    private Robot robot;
    private boolean isInWorld = false;
    private final Response response = new Response();
    private final Server server;
    private final World world;


    public RobotCommand(Server server, World world){
        this.server = server;
        this.world = world;
    }

    public void NewCommand(JsonObject newCommand){
        try {
            if(robot.getStatus() == RobotStatus.DEAD){
                isInWorld = false;
            }
        }
        catch (NullPointerException ignored) {

        }
        try {
            switch (newCommand.get("command").getAsString()) {

                case "launch" -> {
                    if(!isInWorld){
                        isInWorld = true;
                        robot = new Robot(newCommand.get("robot").getAsString(), world,
                                newCommand.get("arguments").getAsJsonArray().get(1).getAsInt(), newCommand.get("arguments").getAsJsonArray().get(2).getAsInt());
                        server.sendResponse(response.LaunchSuccess(robot));
                        server.setRobot(robot);
                    }
                }

                case "state" -> server.sendResponse(response.State(robot));

                case "forward" -> {
                    if(robot.getStatus()== RobotStatus.DEAD){
                        server.sendResponse(response.State(robot));
                        break;
                    }
                    int steps = newCommand.get("arguments").getAsJsonArray().get(0).getAsInt();
                    boolean isSuccessful = true;
                    for (int i = 0; i < steps; i++){
                        MovementStatus movementStatus = robot.moveForward();
                        if(movementStatus == MovementStatus.Obstructed){
                            isSuccessful = false;
                            server.sendResponse(response.MovementObstructed(robot));
                            break;
                        }
                        if(movementStatus == MovementStatus.Fell){
                            isSuccessful = false;
                            server.sendResponse(response.MovementIntoPit());
                            robot.setStatus(RobotStatus.DEAD);
                            world.RemoveRobot(robot);
                            break;
                        }
                        if (movementStatus == MovementStatus.Mine){
                            isSuccessful = false;
                            server.sendResponse(response.stepOnMine(robot));
                            break;
                        }
                    }
                    if(isSuccessful){
                        server.sendResponse(response.MovementSuccess(robot));
                    }
                }

                case "back" -> {
                    if(robot.getStatus()== RobotStatus.DEAD){
                        server.sendResponse(response.State(robot));
                        break;
                    }
                    int steps = -newCommand.get("arguments").getAsJsonArray().get(0).getAsInt();
                    boolean isSuccessful = true;
                    for (int i = 0; i < -steps; i++){
                        MovementStatus movementStatus = robot.moveBack();
                        if(movementStatus == MovementStatus.Obstructed){
                            isSuccessful = false;
                            server.sendResponse(response.MovementObstructed(robot));
                            break;
                        }
                        if(movementStatus == MovementStatus.Fell){
                            isSuccessful = false;
                            server.sendResponse(response.MovementIntoPit());
                            robot.setStatus(RobotStatus.DEAD);
                            world.RemoveRobot(robot);
                            break;
                        }
                        if (movementStatus == MovementStatus.Mine){
                            isSuccessful = false;
                            server.sendResponse(response.stepOnMine(robot));
                            break;
                        }
                    }
                    if(isSuccessful){
                        server.sendResponse(response.MovementSuccess(robot));
                    }

                }

                case "turn" -> {
                    if(robot.getStatus()== RobotStatus.DEAD){
                        server.sendResponse(response.State(robot));
                        break;
                    }
                    if(newCommand.get("arguments").getAsString().equals("right")){
                        robot.turn(true);
                        server.sendResponse(response.Turn(robot));
                    }
                    else if(newCommand.get("arguments").getAsString().equals("left")){
                        robot.turn(false);
                        server.sendResponse(response.Turn(robot));
                    }
                    else {
                        server.sendResponse(response.InvalidArguments());
                    }
                }

                case "look" -> {
                    if(robot.getStatus()== RobotStatus.DEAD){
                        server.sendResponse(response.State(robot));
                        break;
                    }

                    int range = 10;
                    HashMap<String, Object> object = new HashMap<>();
                    List<HashMap<String, Object>> objects = new ArrayList<>();
                    boolean robotFound = false;
                    for (int i = 1; i <= range; i++) {
                        if(robot.getY() + i == world.getWorldHeight()/2){
                            object.put("direction", Direction.NORTH);
                            object.put("type", ObjectTypes.EDGE);
                            object.put("distance", i);
                            objects.add(object);
                            break;
                        }
                        if (robot.pitAtPosition(robot.getX(), robot.getY() + i)){
                            object.put("direction", Direction.NORTH);
                            object.put("type", ObjectTypes.PIT);
                            object.put("distance", i);
                            objects.add(object);
                            break;
                        }
                        if (robot.obstacleAtPosition(robot.getX(), robot.getY() + i)){
                            object.put("direction", Direction.NORTH);
                            object.put("type", ObjectTypes.OBSTACLE);
                            object.put("distance", i);
                            objects.add(object);
                            break;
                        }
                        for (Robot otherRobot: world.getRobots()){
                            if (otherRobot.getX() == robot.getX() && otherRobot.getY() == robot.getY() + i){
                                object.put("direction", Direction.NORTH);
                                object.put("type", ObjectTypes.ROBOT);
                                object.put("distance", i);
                                objects.add(object);
                                robotFound = true;
                                break;
                            }
                        }
                        for (Mine mine: world.getMines()){
                            if (mine.getX() == robot.getX() && mine.getY() == robot.getY() + i){
                                object.put("direction", Direction.NORTH);
                                object.put("type", ObjectTypes.MINE);
                                object.put("distance", i);
                                objects.add(object);
                                break;
                            }
                        }
                        if(robotFound){
                            break;
                        }
                    }
                    object = new HashMap<>();
                    robotFound = false;
                    for (int i = 1; i <= range; i++) {
                        if(robot.getY() - i == -world.getWorldHeight()/2){
                            object.put("direction", Direction.SOUTH);
                            object.put("type", ObjectTypes.EDGE);
                            object.put("distance", i);
                            objects.add(object);
                            break;
                        }
                        if (robot.pitAtPosition(robot.getX(), robot.getY() - i)){
                            object.put("direction", Direction.SOUTH);
                            object.put("type", ObjectTypes.PIT);
                            object.put("distance", i);
                            objects.add(object);
                            break;
                        }
                        if (robot.obstacleAtPosition(robot.getX(), robot.getY() - i)){
                            object.put("direction", Direction.SOUTH);
                            object.put("type", ObjectTypes.OBSTACLE);
                            object.put("distance", i);
                            objects.add(object);
                            break;
                        }
                        for (Robot otherRobot: world.getRobots()){
                            if (otherRobot.getX() == robot.getX() && otherRobot.getY() == robot.getY() - i){
                                object.put("direction", Direction.SOUTH);
                                object.put("type", ObjectTypes.ROBOT);
                                object.put("distance", i);
                                objects.add(object);
                                robotFound = true;
                                break;
                            }
                        }
                        for (Mine mine: world.getMines()){
                            if (mine.getX() == robot.getX() && mine.getY() == robot.getY() - i){
                                object.put("direction", Direction.SOUTH);
                                object.put("type", ObjectTypes.MINE);
                                object.put("distance", i);
                                objects.add(object);
                                break;
                            }
                        }
                        if(robotFound){
                            break;
                        }
                    }
                    object = new HashMap<>();
                    robotFound = false;
                    for (int i = 1; i <= range; i++) {
                        if(robot.getX() + i == world.getWorldWidth()/2){
                            object.put("direction", Direction.EAST);
                            object.put("type", ObjectTypes.EDGE);
                            object.put("distance", i);
                            objects.add(object);
                            break;
                        }
                        if (robot.pitAtPosition(robot.getX() + i, robot.getY())){
                            object.put("direction", Direction.EAST);
                            object.put("type", ObjectTypes.PIT);
                            object.put("distance", i);
                            objects.add(object);
                            break;
                        }
                        if (robot.obstacleAtPosition(robot.getX() + i, robot.getY())){
                            object.put("direction", Direction.EAST);
                            object.put("type", ObjectTypes.OBSTACLE);
                            object.put("distance", i);
                            objects.add(object);
                            break;
                        }
                        for (Robot otherRobot: world.getRobots()){
                            if (otherRobot.getX() == robot.getX() + i && otherRobot.getY() == robot.getY()){
                                object.put("direction", Direction.EAST);
                                object.put("type", ObjectTypes.ROBOT);
                                object.put("distance", i);
                                objects.add(object);
                                robotFound = true;
                                break;
                            }
                        }
                        for (Mine mine: world.getMines()){
                            if (mine.getX() == robot.getX() + i && mine.getY() == robot.getY()){
                                object.put("direction", Direction.EAST);
                                object.put("type", ObjectTypes.MINE);
                                object.put("distance", i);
                                objects.add(object);
                                break;
                            }
                        }
                        if(robotFound){
                            break;
                        }
                    }
                    object = new HashMap<>();
                    robotFound = false;
                    for (int i = 1; i <= range; i++) {
                        if(robot.getX() - i == -world.getWorldWidth()/2){
                            object.put("direction", Direction.WEST);
                            object.put("type", ObjectTypes.EDGE);
                            object.put("distance", i);
                            objects.add(object);
                            break;
                        }
                        if (robot.pitAtPosition(robot.getX() - i, robot.getY())){
                            object.put("direction", Direction.WEST);
                            object.put("type", ObjectTypes.PIT);
                            object.put("distance", i);
                            objects.add(object);
                            break;
                        }
                        if (robot.obstacleAtPosition(robot.getX() - i, robot.getY())){
                            object.put("direction", Direction.WEST);
                            object.put("type", ObjectTypes.OBSTACLE);
                            object.put("distance", i);
                            objects.add(object);
                            break;
                        }
                        for (Robot otherRobot: world.getRobots()){
                            if (otherRobot.getX() == robot.getX() - i && otherRobot.getY() == robot.getY()){
                                object.put("direction", Direction.WEST);
                                object.put("type", ObjectTypes.ROBOT);
                                object.put("distance", i);
                                objects.add(object);
                                robotFound = true;
                                break;
                            }
                        }
                        for (Mine mine: world.getMines()){
                            if (mine.getX() == robot.getX() - i && mine.getY() == robot.getY()){
                                object.put("direction", Direction.WEST);
                                object.put("type", ObjectTypes.MINE);
                                object.put("distance", i);
                                objects.add(object);
                                break;
                            }
                        }
                        if(robotFound){
                            break;
                        }
                    }

                    server.sendResponse(response.Look(robot, objects));
                }

                case "fire" -> {
                    if (robot.getNumberShots() == 0) {
                        server.sendResponse(response.fireNoAmmo());
                        break;
                    }

                    boolean beenHit = robot.beenHit();

                    if(robot.getStatus()== RobotStatus.DEAD){
                        server.sendResponse(response.State(robot));
                        break;
                    }

                    if (beenHit) {
                        //int shields, int injuredShots, String robotStatus, int shots
                        server.sendResponse(
                                response.HitRobot(robot.getDistance(), robot.getNumberShots(),
                                        robot.returnHitRobot()));
                    } else {
                        server.sendResponse(response.MissedRobot(robot.getNumberShots()));
                    }
                }

                case "reload" -> {
                    if(robot.getStatus()== RobotStatus.DEAD){
                        server.sendResponse(response.State(robot));
                        break;
                    }
                    robot.reload();
                    server.sendResponse(response.Reload());
                }

                case "repair" -> {
                    if (robot.getStatus() == RobotStatus.DEAD) {
                        server.sendResponse(response.State(robot));
                        break;
                    }
                    robot.repair();
                    server.sendResponse(response.Repair());
                }

                case "mine" -> {
                    int x = robot.getX();
                    int y = robot.getY();
                    Mine mine = new Mine(x, y);
                    world.addMine(mine);
                    server.sendResponse(response.setMine());
                    robot.setMine();

                    String[] mySteps = new String[1];
                    mySteps[0] = String.valueOf(1);
                    Gson gson = new Gson();
                    JsonObject finalResponse = new JsonObject();

                    finalResponse.addProperty("robot",robot.getName());

                    finalResponse.addProperty("command","forward");
                    finalResponse.add("arguments", gson.toJsonTree(mySteps));

                    this.NewCommand(finalResponse);

                    if(robot.getX() == x && robot.getY() == y){
                        robot.steppedOnMine();
                        world.removeMine(mine);
                        server.sendResponse(response.stepOnMine(robot));
                    }
                }

                default -> {
                    if(robot.getStatus()== RobotStatus.DEAD){
                        server.sendResponse(response.State(robot));
                        break;
                    }
                    server.sendResponse(response.UnsupportedCommand());
                }
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

}
