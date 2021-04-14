package Server;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Arrays;

public class Response {
    public String UnsupportedCommand(){
        return "{ \"result\": \"ERROR\", \"data\": { message: \"Unsupported command\" } }";
    }

    public String InvalidArguments(){
        return "{ \"result\": \"ERROR\", \"data\": { message: \"Could not parse arguments\" } }";
    }

    public String Look(){
        return null;
    }

    public String MovementSuccess(int x, int y, Direction currentDirection){
        int[] position = new int[]{x,y};
        JsonObject finalResponse = new JsonObject();
        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "Done");
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(currentDirection));
        state.addProperty("status", "NORMAL");
        finalResponse.addProperty("result", "OK");
        finalResponse.add("state", state);
        finalResponse.add("data", data);
        return finalResponse.toString();
    }

    public String MovementObstructed(int x, int y, Direction currentDirection){
        int[] position = new int[]{x,y};
        JsonObject finalResponse = new JsonObject();
        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "Obstructed");
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(currentDirection));
        state.addProperty("status", "NORMAL");
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

    public String Turn(int x, int y, Direction currentDirection){
        int[] position = new int[]{x,y};
        JsonObject finalResponse = new JsonObject();
        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "DONE");
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(currentDirection));
        state.addProperty("status", "NORMAL");
        finalResponse.addProperty("result", "OK");
        finalResponse.add("state", state);
        finalResponse.add("data", data);
        return finalResponse.toString();
    }

    public String State(int x, int y, Direction currentDirection){
        int[] position = new int[]{x,y};
        JsonObject state = new JsonObject();
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(currentDirection));
        state.addProperty("status", "NORMAL");
        JsonObject finalResponse = new JsonObject();
        finalResponse.add("state", state);
        return finalResponse.toString();
    }

    public String LaunchSuccess(int x, int y, Direction currentDirection){
        int[] position = new int[]{x,y};
        JsonObject finalResponse = new JsonObject();
        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("position", Arrays.toString(position));
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(currentDirection));
        state.addProperty("status", "NORMAL");
        finalResponse.addProperty("result", "OK");
        finalResponse.add("state", state);
        finalResponse.add("data", data);
        return finalResponse.toString();
    }

    /*
    {
  "result": "OK",
  "data": {
    "message": "Hit",
    "distance": steps,
    "robot": name
    "state": { ... }
  },
  "state": {
    "shots":  shots
  }
}

 "state: {
    "position": [x,y],
    "direction": "direction that the robot is facing",
    "shields": number of hits that can be absorbed,
    "shots": number of shots left,
    "status": "operational status that the robot is in"
   }
     */
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

    public String LaunchNoSpace(){
        return "{ \"result\": \"ERROR\", \"data\": { \"message\": \"No more space in this world\" } }";
    }

    public String LaunchNameTaken(){
        return "{ \"result\": \"ERROR\", \"data\": { \"message\": \"Too many of you in this world\" } }";
    }

    public String MissedRobot(int shots) {
        JsonObject finalResponse = new JsonObject();

        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        data.addProperty("message", "Miss");
        state.addProperty("shots", String.valueOf(shots));

        finalResponse.add("state", state);
        finalResponse.add("data", data);
        return finalResponse.toString();
    }
}
