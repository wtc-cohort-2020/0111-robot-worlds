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
    public String HitRobot(int shields, int injuredShots, String robotStatus, int shots) {
        JsonObject finalResponse = new JsonObject();
        JsonObject state = new JsonObject();
        JsonObject state_2 = new JsonObject();

        finalResponse.addProperty("response","OK");


        int[] position = new int[]{x,y};
        state_2.addProperty("position", Arrays.toString(position));
        state_2.addProperty("direction", String.valueOf(currentDirection));
        state_2.addProperty("shots", String.valueOf(injuredShots));
        state_2.addProperty("shields", String.valueOf(shields));
        state_2.addProperty("status", robotStatus);

        state.addProperty("shots",String.valueOf(shots));

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
