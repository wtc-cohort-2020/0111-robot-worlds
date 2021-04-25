package Server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptClients implements Runnable{
    private final World world;
    private ServerSocket s;

    public AcceptClients(World world) throws IOException{
        File input = new File("src/main/java/Server/WorldSpecs.json");
        JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
        JsonObject fileObject = fileElement.getAsJsonObject();
        int PORT = fileObject.get("port").getAsInt();
        System.out.println("Port");
        System.out.println(PORT);
        this.world = world;
        s = new ServerSocket(PORT);
        System.out.println("Server.Server running & waiting for client connections.");
    }

    public void run() {
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
}
