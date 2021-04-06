package Server;

import Server.Server;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.*;
import java.io.*;

public class MultiServers {
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        World world = new World();
        ServerSocket s = new ServerSocket( Server.PORT);
        System.out.println("Server.Server running & waiting for client connections.");
        while(true) {
            try {
                Socket socket = s.accept();
                System.out.println("Connection: " + socket);

                Runnable r = new Server(socket, world);
                Thread task = new Thread(r);
                task.start();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void getDataFromJSon(){
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

            int worldHeight = fileObject.get("height").getAsInt();
            int worldWidth = fileObject.get("height").getAsInt();
            System.out.println("height: " + worldHeight);
            System.out.println("width: " + worldWidth);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}