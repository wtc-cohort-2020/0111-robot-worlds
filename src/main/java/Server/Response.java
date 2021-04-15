package Server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Response {
    public String UnsupportedCommand(){
        return "{ \"result\": \"ERROR\", \"data\": { message: \"Unsupported command\" } }";
    }

    public String InvalidArguments(){
        return "{ \"result\": \"ERROR\", \"data\": { message: \"Could not parse arguments\" } }";
    }

    public String Look(int x, int y, Direction currentDirection, List<HashMap<String, Object>> myObjects){
        int[] position = new int[]{x,y};
        JsonObject finalResponse = new JsonObject();
        JsonObject state = new JsonObject();
        JsonObject data = new JsonObject();
        Gson gson = new Gson();
        data.addProperty("objects", gson.toJson(myObjects));
        state.addProperty("position", Arrays.toString(position));
        state.addProperty("direction", String.valueOf(currentDirection));
        state.addProperty("status", "NORMAL");
        finalResponse.addProperty("result", "OK");
        finalResponse.add("state", state);
        finalResponse.add("data", data);
        return finalResponse.toString();
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

    public String LaunchNoSpace(){
        return "{ \"result\": \"ERROR\", \"data\": { \"message\": \"No more space in this world\" } }";
    }

    public String LaunchNameTaken(){
        return "{ \"result\": \"ERROR\", \"data\": { \"message\": \"Too many of you in this world\" } }";
    }
}
