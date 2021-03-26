

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Server implements Runnable {

    public static final int PORT = 1999;
    private final BufferedReader in;
    private final PrintStream out;
    private final String clientMachine;
    private static int worldHeight = 0;
    private static int worldWidth = 0;



    public Server(Socket socket) throws IOException {
        clientMachine = socket.getInetAddress().getHostName();
        System.out.println("Connection from " + clientMachine);

        out = new PrintStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        System.out.println("Waiting for client...");
    }

    public void run() {
        try {
            String messageFromClient;
            while((messageFromClient = in.readLine()) != null) {
                System.out.println("Message \"" + messageFromClient + "\" from " + clientMachine);
                out.println("Thanks for this message: "+messageFromClient);
            }
        } catch(IOException ex) {
            System.out.println("Shutting down single client server");
        } finally {
            closeQuietly();
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

            worldHeight = fileObject.get("height").getAsInt();
            worldWidth = fileObject.get("height").getAsInt();
            System.out.println("height: " + worldHeight);
            System.out.println("width: " + worldWidth);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void closeQuietly() {
        try { in.close(); out.close();
        } catch(IOException ignored) {}
    }
}