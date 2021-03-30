package Server;

import com.google.gson.*;

public class RobotCommand {
    private Robot robot;
    private boolean isInWorld = false;
    private Response response = new Response();
    private Server server;



    public RobotCommand(Robot robot, Server server){
        this.robot = robot;
        this.server = server;
    }

    public void NewCommand(JsonObject newCommand){
        switch (newCommand.get("command").getAsString()) {
            case "launch" -> {
                if(!isInWorld){
                    isInWorld = true;
                    robot = new Robot(newCommand.get("robot").getAsString());
                    server.sendResponse(response.LaunchSuccess(
                            robot.getX(), robot.getY(), robot.getCurrentDirection()));
                }
            }
            case "forward" -> {
                robot.updatePosition(newCommand.get("arguments").getAsInt());
                server.sendResponse(response.MovementSuccess(
                        robot.getX(), robot.getY(), robot.getCurrentDirection()));
            }
            case "back" -> {
                robot.updatePosition(-newCommand.get("arguments").getAsInt());
                server.sendResponse(response.MovementSuccess(
                        robot.getX(), robot.getY(), robot.getCurrentDirection()));
            }
            case "turn" -> {
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
                server.sendResponse(response.Look());
            }
            default -> server.sendResponse(response.UnsupportedCommand());
        }
    }

}
