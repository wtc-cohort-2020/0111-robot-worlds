package Server;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.Locale;

public class RobotCommand {
    private Robot robot;
    private boolean isInWorld = false;
    private Response response = new Response();
    private Server server;
    private World world;


    public RobotCommand(Robot robot, Server server, World world){
        this.robot = robot;
        this.server = server;
        this.world = world;
    }

    public void NewCommand(JsonObject newCommand){
        switch (newCommand.get("command").getAsString()) {
            case "launch" -> {
                /*
                A try catch block for checking that launch <make> <name> structure
                is followed, as well that make is a valid make and robot
                 with <name> does not already exist.
                 */
                JsonArray arguments = newCommand.getAsJsonArray("arguments");
                ArrayList<String> list = new ArrayList<String>();

                if (arguments != null) {
                    int len = newCommand.getAsJsonArray("arguments").size();
                    for (int i=0;i<len;i++){
                        list.add(arguments.get(i).toString());
                    }
                }
                for(Robot robot: world.getRobots()) {

                    if (list.get(0).equals(robot.getName())) {
                        // send a response saying request was invalid.
                        server.sendResponse(response.InvalidArguments());
                    }

                }

                if (list.get(1).trim().equalsIgnoreCase("sniper") &&
                        list.get(1).trim().equalsIgnoreCase("standard") &&
                        list.get(1).trim().equalsIgnoreCase("pistol")) {
                    //send response saying request was invalid.
                    server.sendResponse(response.InvalidArguments());
                }



                if(!isInWorld){
                    isInWorld = true;
                    robot = new Robot(newCommand.get("robot").getAsString(), world, list.get(1).trim().toLowerCase());
                    server.sendResponse(response.LaunchSuccess(
                            robot.getX(), robot.getY(), robot.getCurrentDirection()));
                }
            }
            case "forward" -> {
                if(robot.getStatus()== RobotStatus.DEAD){
                    server.sendResponse(response.MovementIntoPit());
                    break;
                }
                int steps = newCommand.get("arguments").getAsInt();
                boolean isSuccessful = true;
                for (int i = 0; i < steps; i++){
                    MovementStatus movementStatus = robot.moveForward();
                    if(movementStatus == MovementStatus.Obstructed){
                        isSuccessful = false;
                        server.sendResponse(response.MovementObstructed(
                                robot.getX(), robot.getY(), robot.getCurrentDirection()));
                        break;
                    }
                    if(movementStatus == MovementStatus.Fell){
                        isSuccessful = false;
                        server.sendResponse(response.MovementIntoPit());
                        robot.setStatus(RobotStatus.DEAD);
                        break;
                    }
                }
                if(isSuccessful){
                    server.sendResponse(response.MovementSuccess(
                            robot.getX(), robot.getY(), robot.getCurrentDirection()));
                }
            }
            case "back" -> {
                if(robot.getStatus()== RobotStatus.DEAD){
                    server.sendResponse(response.MovementIntoPit());
                    break;
                }
                int steps = - newCommand.get("arguments").getAsInt();
                boolean isSuccessful = true;
                for (int i = 0; i < -steps; i++){
                    MovementStatus movementStatus = robot.moveBack();
                    if(movementStatus == MovementStatus.Obstructed){
                        isSuccessful = false;
                        server.sendResponse(response.MovementObstructed(
                                robot.getX(), robot.getY(), robot.getCurrentDirection()));
                        break;
                    }
                    if(movementStatus == MovementStatus.Fell){
                        isSuccessful = false;
                        server.sendResponse(response.MovementIntoPit());
                        robot.setStatus(RobotStatus.DEAD);
                        break;
                    }
                }
                if(isSuccessful){
                    server.sendResponse(response.MovementSuccess(
                            robot.getX(), robot.getY(), robot.getCurrentDirection()));
                }

            }
            case "turn" -> {
                if(robot.getStatus()== RobotStatus.DEAD){
                    server.sendResponse(response.MovementIntoPit());
                    break;
                }
                if(newCommand.get("arguments").getAsString().equals("right")){
                    robot.turn(true);
                    server.sendResponse(response.Turn(
                            robot.getX(), robot.getY(), robot.getCurrentDirection()));
                }
                else if(newCommand.get("arguments").getAsString().equals("left")){
                    robot.turn(false);
                    server.sendResponse(response.Turn(
                            robot.getX(), robot.getY(), robot.getCurrentDirection()));
                }
                else {
                    server.sendResponse(response.InvalidArguments());
                }
            }
            case "look" -> {
                if(robot.getStatus()== RobotStatus.DEAD){
                    server.sendResponse(response.MovementIntoPit());
                    break;
                }
                server.sendResponse(response.Look());
            }

            default -> {
                if(robot.getStatus()== RobotStatus.DEAD){
                    server.sendResponse(response.MovementIntoPit());
                    break;
                }
                server.sendResponse(response.UnsupportedCommand());
            }
        }
    }

}
