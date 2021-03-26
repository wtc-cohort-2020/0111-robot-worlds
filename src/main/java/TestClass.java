import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestClass {


    public static void main(String[] args) {

        // Create World Object from World instance, using fromJson()
        // Known as deserialisation
        try {

            /*
            Establish file to be used as input.
            Create a JSon element from the file.
            Create JSon object from the element.
             */

            File input = new File("src/main/java/WorldSpecs.json");
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            //Extracting basic fields

            int height = fileObject.get("height").getAsInt();
            int width = fileObject.get("height").getAsInt();
            System.out.println("height: " + height);
            System.out.println("width: " + width);

        } catch (Exception ex) {
            ex.printStackTrace();
        }




    }


}
