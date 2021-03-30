package Server;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class TestingStuff {

    public static void main(String[] args) {
        Response rep = new Response();
        JsonObject stat = new JsonObject();

        System.out.println(rep.State(5,5, Direction.NORTH));

        JsonObject jsonMessage = new JsonParser().parse(rep.State(5,5, Direction.NORTH)).getAsJsonObject();
        stat = jsonMessage.get("state").getAsJsonObject();
        System.out.println(stat.get("position").getAsString());
    }
}
