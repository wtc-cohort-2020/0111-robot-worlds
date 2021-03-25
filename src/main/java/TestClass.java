import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestClass {


    public static void main(String[] args) {

        // Create World Object from World instance, using fromJson()
        // Known as deserialisation
        try {
            Gson gson = new Gson();
            World world = gson.fromJson(new FileReader("src/main/java/WorldSpecs.json"), World.class);
            System.out.println(world);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //Reader reader = Files.newBufferedReader(Paths.get(WorldSpecs.json));

    }


}
