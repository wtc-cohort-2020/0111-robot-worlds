package Client;

import com.google.gson.JsonObject;

import java.util.Arrays;

public class Request {
    public String move(int x, String name, String[] arguments) {
        JsonObject finalResponse = new JsonObject();

        finalResponse.addProperty("name",name);

        finalResponse.addProperty("command","forward");
        finalResponse.addProperty("arguments", Arrays.toString(arguments));


        return finalResponse.toString();
    }



    public String fire(String name) {
        JsonObject finalResponse = new JsonObject();
        int[] nothing = new int[0];

        finalResponse.addProperty("name",name);
        finalResponse.addProperty("command","fire");
        finalResponse.addProperty("arguments", Arrays.toString(nothing));


        return finalResponse.toString();
    }

    public String repair(String name) {
        JsonObject finalResponse = new JsonObject();
        int[] nothing = new int[0];

        finalResponse.addProperty("name",name);
        finalResponse.addProperty("command","repair");
        finalResponse.addProperty("arguments", Arrays.toString(nothing));


        return finalResponse.toString();
    }

    public String reload(String name) {
        JsonObject finalResponse = new JsonObject();
        int[] nothing = new int[0];

        finalResponse.addProperty("name",name);
        finalResponse.addProperty("command","reload");
        finalResponse.addProperty("arguments", Arrays.toString(nothing));


        return finalResponse.toString();
    }

    public String setMine(String name) {
        JsonObject finalResponse = new JsonObject();
        int[] nothing = new int[0];

        finalResponse.addProperty("name",name);
        finalResponse.addProperty("command","mine");
        finalResponse.addProperty("arguments", Arrays.toString(nothing));

        return finalResponse.toString();
    }

    public String launch(String type, String name) {
        JsonObject finalResponse = new JsonObject();
        finalResponse.addProperty("name",name);
        finalResponse.addProperty("command","launch");

        String[] arguments = new String[3];

        if (type.equals("sniper")) {
            arguments[0] = "sniper";
            arguments[1] = "3";
            arguments[2] = "2";

        }
        if (type.equals("pistol")) {
            arguments[0] = "pistol";
            arguments[1] = "3";
            arguments[2] = "4";
        }
        if (type.equals("standard")) {
            arguments[0] = "standard";
            arguments[1] = "3";
            arguments[2] = "3";
        }
        finalResponse.addProperty("arguments", Arrays.toString(arguments));

        return finalResponse.toString();
    }

    public String look(String name) {
        JsonObject finalResponse = new JsonObject();
        finalResponse.addProperty("name",name);
        finalResponse.addProperty("command","look");
        finalResponse.addProperty("arguments", Arrays.toString(new int[0]));

        return finalResponse.toString();
    }

    public String state(String name) {
        JsonObject finalResponse = new JsonObject();
        finalResponse.addProperty("name",name);
        finalResponse.addProperty("command","state");
        finalResponse.addProperty("arguments", Arrays.toString(new int[0]));

        return finalResponse.toString();
    }

    public String turn(String name, String[] arguments) {
        JsonObject finalResponse = new JsonObject();

        finalResponse.addProperty("name",name);
        finalResponse.addProperty("command","turn");

        finalResponse.addProperty("arguments", Arrays.toString(arguments));

        return finalResponse.toString();
    }

    public String turn(String name, String command, String[] arguments) {
        JsonObject finalResponse = new JsonObject();

        finalResponse.addProperty("name",name);
        finalResponse.addProperty("command",command);
        finalResponse.addProperty("arguments", Arrays.toString(arguments));

        return finalResponse.toString();
    }

}
