package Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Response {
    public String UnsupportedCommand(){
        JsonObject finalResponse = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "Unsupported command");
        finalResponse.addProperty("result", "ERROR");
        finalResponse.add("data", data);
        return finalResponse.toString();
    }

    public String InvalidArguments(){
        JsonObject finalResponse = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "Could not parse arguments");
        finalResponse.addProperty("result", "ERROR");
        finalResponse.add("data", data);
        return finalResponse.toString();
    }

    public String Look(Robot robot, List<HashMap<String, Object>> myObjects){
        int[] position = new int[]{robot.getX(), robot.getY()};
        JsonObject finalResponse = new JsonObject();
        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        Gson gson = new Gson();
        data.addProperty("objects", gson.toJson(myObjects));
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state.addProperty("shields", String.valueOf(robot.getShields()));
        state.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state.addProperty("status", String.valueOf(robot.getStatus()));
        finalResponse.addProperty("result", "OK");
        finalResponse.add("state", state);
        finalResponse.add("data", data);
        return finalResponse.toString();
    }

    public String MovementSuccess(Robot robot){
        int[] position = new int[]{robot.getX(), robot.getY()};
        JsonObject finalResponse = new JsonObject();
        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "Done");
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state.addProperty("shields", String.valueOf(robot.getShields()));
        state.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state.addProperty("status", String.valueOf(robot.getStatus()));
        finalResponse.addProperty("result", "OK");
        finalResponse.add("state", state);
        finalResponse.add("data", data);
        return finalResponse.toString();
    }

    public String MovementObstructed(Robot robot){
        int[] position = new int[]{robot.getX(), robot.getY()};
        JsonObject finalResponse = new JsonObject();
        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "Obstructed");
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state.addProperty("shields", String.valueOf(robot.getShields()));
        state.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state.addProperty("status", String.valueOf(robot.getStatus()));
        finalResponse.addProperty("result", "OK");
        finalResponse.add("state", state);
        finalResponse.add("data", data);
        return finalResponse.toString();
    }

    public String MovementIntoPit(){
        JsonObject finalResponse = new JsonObject();
        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "Fell");
        state.addProperty("status", "DEAD");
        finalResponse.addProperty("result", "OK");
        finalResponse.add("state", state);
        finalResponse.add("data", data);
        return finalResponse.toString();
    }

    public String Turn(Robot robot){
        int[] position = new int[]{robot.getX(), robot.getY()};
        JsonObject finalResponse = new JsonObject();
        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "DONE");
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state.addProperty("shields", String.valueOf(robot.getShields()));
        state.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state.addProperty("status", String.valueOf(robot.getStatus()));
        finalResponse.addProperty("result", "OK");
        finalResponse.add("state", state);
        finalResponse.add("data", data);
        return finalResponse.toString();
    }

    public String State(Robot robot){
        int[] position = new int[]{robot.getX(), robot.getY()};
        JsonObject state = new JsonObject();
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state.addProperty("shields", String.valueOf(robot.getShields()));
        state.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state.addProperty("status", String.valueOf(robot.getStatus()));
        JsonObject finalResponse = new JsonObject();
        finalResponse.add("state", state);
        return finalResponse.toString();
    }

    public String LaunchSuccess(Robot robot){
        int[] position = new int[]{robot.getX(), robot.getY()};
        JsonObject finalResponse = new JsonObject();
        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("position", Arrays.toString(position));
        data.addProperty("visibility", String.valueOf(robot.getVisibility()));
        data.addProperty("reload", String.valueOf(robot.getRELOAD_TIME()));
        data.addProperty("repair", String.valueOf(robot.getREPAIR_TIME()));
        data.addProperty("mine", String.valueOf(robot.getSET_MINE_TIME()));
        data.addProperty("shields", String.valueOf(robot.getShields()));

        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state.addProperty("shields", String.valueOf(robot.getShields()));
        state.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state.addProperty("status", String.valueOf(robot.getStatus()));
        finalResponse.addProperty("result", "OK");
        finalResponse.add("state", state);
        finalResponse.add("data", data);
        return finalResponse.toString();
    }

    public String fireNoAmmo(){
        return "{ \"result\": \"ERROR\", \"data\": { \"message\": \"You are out of shots.\" } }";
    }

    public String LaunchNoSpace(){
        return "{ \"result\": \"ERROR\", \"data\": { \"message\": \"No more space in this world\" } }";
    }

    public String LaunchNameTaken(){
        return "{ \"result\": \"ERROR\", \"data\": { \"message\": \"Too many of you in this world\" } }";
    }

    public String HitRobot(int distance, int shots, Robot robot) {
        JsonObject finalResponse = new JsonObject();
        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        JsonObject state_hit = new JsonObject();

        finalResponse.addProperty("result","OK");

        int[] position = new int[]{robot.getX(),robot.getY()};
        state_hit.addProperty("position", Arrays.toString(position));
        state_hit.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state_hit.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state_hit.addProperty("shields", String.valueOf(robot.getShields()));
        state_hit.addProperty("status", robot.getStatus().toString());

        data.addProperty("message","hit");
        data.addProperty("distance", distance);
        data.addProperty("robot",robot.getName());
        data.add("status",state_hit);


        state.addProperty("shots",String.valueOf(shots));

        finalResponse.add("data",data);
        finalResponse.add("state",state);

        return finalResponse.toString();
    }

    public String MissedRobot(Robot robot) {
        JsonObject finalResponse = new JsonObject();

        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "Miss");

        int[] position = new int[]{robot.getX(),robot.getY()};
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state.addProperty("shields", String.valueOf(robot.getShields()));
        state.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state.addProperty("status", String.valueOf(robot.getStatus()));

        finalResponse.add("state", state);
        finalResponse.add("data", data);
        return finalResponse.toString();
    }

    public String Repair (Robot robot){
        JsonObject finalResponse = new JsonObject();

        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "Done");
        int[] position = new int[]{robot.getX(),robot.getY()};
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state.addProperty("shields", String.valueOf(robot.getShields()));
        state.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state.addProperty("status", String.valueOf(robot.getStatus()));

        finalResponse.add("data",data);
        finalResponse.add("state",state);

        return finalResponse.toString();
    }

    public String Reload(Robot robot) {
        JsonObject finalResponse = new JsonObject();

        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "Done");
        int[] position = new int[]{robot.getX(),robot.getY()};
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state.addProperty("shields", String.valueOf(robot.getShields()));
        state.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state.addProperty("status", String.valueOf(robot.getStatus()));

        finalResponse.add("data",data);
        finalResponse.add("state",state);

        return finalResponse.toString();
    }

    public String setMine(Robot robot){
        JsonObject finalResponse = new JsonObject();

        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "Done");
        int[] position = new int[]{robot.getX(),robot.getY()};
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state.addProperty("shields", String.valueOf(robot.getShields()));
        state.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state.addProperty("status", String.valueOf(robot.getStatus()));
        finalResponse.addProperty("result", "OK");
        finalResponse.add("data",data);
        finalResponse.add("state",state);

        return finalResponse.toString();
    }

    public String stepOnMine(Robot robot){
        JsonObject finalResponse = new JsonObject();

        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "You stepped on a mine.");

        int[] position = new int[]{robot.getX(),robot.getY()};
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state.addProperty("shields", String.valueOf(robot.getShields()));
        state.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state.addProperty("status", String.valueOf(robot.getStatus()));

        finalResponse.add("data",data);
        finalResponse.add("state",state);

        return finalResponse.toString();
    }

    public String hasBeenShot(Robot robot){
        JsonObject finalResponse = new JsonObject();

        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "You have been shot.");

        int[] position = new int[]{robot.getX(),robot.getY()};
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state.addProperty("shields", String.valueOf(robot.getShields()));
        state.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state.addProperty("status", String.valueOf(robot.getStatus()));

        finalResponse.add("data",data);
        finalResponse.add("state",state);

        return finalResponse.toString();
    }

    public String cannotSetMines(Robot robot){
        JsonObject finalResponse = new JsonObject();

        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "You are not able to set mines.");
        int[] position = new int[]{robot.getX(),robot.getY()};
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(robot.getCurrentDirection()));
        state.addProperty("shields", String.valueOf(robot.getShields()));
        state.addProperty("shots", String.valueOf(robot.getNumberShots()));
        state.addProperty("status", String.valueOf(robot.getStatus()));
        finalResponse.addProperty("result", "OK");
        finalResponse.add("data",data);
        finalResponse.add("state",state);

        return finalResponse.toString();
    }
}
