package Client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Arrays;

public class Request {
    public String forward(String steps, String name) {
        String[] mySteps = new String[1];
        mySteps[0] = steps;
        Gson gson = new Gson();
        JsonObject finalResponse = new JsonObject();

        finalResponse.addProperty("robot",name);

        finalResponse.addProperty("command","forward");
        finalResponse.add("arguments", gson.toJsonTree(mySteps));


        return finalResponse.toString();
    }

    public String back(String steps, String name) {
        String[] mySteps = new String[1];
        mySteps[0] = steps;
        Gson gson = new Gson();
        JsonObject finalResponse = new JsonObject();

        finalResponse.addProperty("robot",name);

        finalResponse.addProperty("command","back");
        finalResponse.add("arguments", gson.toJsonTree(mySteps));


        return finalResponse.toString();
    }

    public String fire(String name) {
        JsonObject finalResponse = new JsonObject();
        int[] nothing = new int[0];

        finalResponse.addProperty("robot",name);
        finalResponse.addProperty("command","fire");
        finalResponse.addProperty("arguments", Arrays.toString(nothing));


        return finalResponse.toString();
    }

    public String repair(String name) {
        JsonObject finalResponse = new JsonObject();
        int[] nothing = new int[0];

        finalResponse.addProperty("robot",name);
        finalResponse.addProperty("command","repair");
        finalResponse.addProperty("arguments", Arrays.toString(nothing));


        return finalResponse.toString();
    }

    public String reload(String name) {
        JsonObject finalResponse = new JsonObject();
        int[] nothing = new int[0];

        finalResponse.addProperty("robot",name);
        finalResponse.addProperty("command","reload");
        finalResponse.addProperty("arguments", Arrays.toString(nothing));


        return finalResponse.toString();
    }

    public String setMine(String name) {
        JsonObject finalResponse = new JsonObject();
        int[] nothing = new int[0];

        finalResponse.addProperty("robot",name);
        finalResponse.addProperty("command","mine");
        finalResponse.addProperty("arguments", Arrays.toString(nothing));

        return finalResponse.toString();
    }

    public String launch(String type, String name) {
        Gson gson = new Gson();
        JsonObject finalResponse = new JsonObject();
        finalResponse.addProperty("robot",name);
        finalResponse.addProperty("command","launch");

        String[] arguments = new String[3];

        if (type.equals("sniper")) {
            arguments[0] = "sniper";
            arguments[1] = "3";
            arguments[2] = "2";

        }
        else if (type.equals("pistol")) {
            arguments[0] = "pistol";
            arguments[1] = "3";
            arguments[2] = "4";
        }
        else if(type.equals("bomber")){
            arguments[0] = "bomber";
            arguments[1] = "3";
            arguments[2] = "0";
        }
        else {
            arguments[0] = "standard";
            arguments[1] = "3";
            arguments[2] = "3";
        }
        finalResponse.add("arguments", gson.toJsonTree(arguments));

        return finalResponse.toString();
    }

    public String look(String name) {
        JsonObject finalResponse = new JsonObject();
        finalResponse.addProperty("robot",name);
        finalResponse.addProperty("command","look");
        finalResponse.addProperty("arguments", Arrays.toString(new int[0]));

        return finalResponse.toString();
    }

    public String state(String name) {
        JsonObject finalResponse = new JsonObject();
        finalResponse.addProperty("robot",name);
        finalResponse.addProperty("command","state");
        finalResponse.addProperty("arguments", Arrays.toString(new int[0]));

        return finalResponse.toString();
    }

    public String right(String name) {
        JsonObject finalResponse = new JsonObject();

        finalResponse.addProperty("robot",name);
        finalResponse.addProperty("command","turn");

        finalResponse.addProperty("arguments", "right");

        return finalResponse.toString();
    }

    public String left(String name) {
        JsonObject finalResponse = new JsonObject();

        finalResponse.addProperty("robot",name);
        finalResponse.addProperty("command","turn");

        finalResponse.addProperty("arguments", "left");

        return finalResponse.toString();
    }

}
