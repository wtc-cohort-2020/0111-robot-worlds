package Client;

import com.google.gson.JsonObject;

import java.util.Arrays;

public class Request {
    public String moveForward(int x, String name) {
        JsonObject finalResponse = new JsonObject();
        int[] steps = new int[]{x};
        JsonObject arguments = new JsonObject();

        finalResponse.addProperty("name",name);
        finalResponse.addProperty("command","forward");
        finalResponse.addProperty("arguments", Arrays.toString(steps));


        return finalResponse.toString();
    }

    public String moveBack(int x, String name) {
        JsonObject finalResponse = new JsonObject();
        int[] steps = new int[]{x};
        JsonObject arguments = new JsonObject();

        finalResponse.addProperty("name",name);
        finalResponse.addProperty("command","back");
        finalResponse.addProperty("arguments", Arrays.toString(steps));

    }

    public String attack(String name) {
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

    public String look() {
        //
    }

    public String state() {
        
    }

}
