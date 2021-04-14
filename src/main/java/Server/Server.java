package Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.*;

public class Server implements Runnable {

    public static final int PORT = 1999;
    private final BufferedReader in;
    private final PrintStream out;
    private final String clientMachine;
    private Robot robot;
    private RobotCommand command;
    private World world;

    public Server(Socket socket, World world) throws IOException {
        this.world = world;
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
            JsonObject jsonMessage;
            command = new RobotCommand(robot, this, world);
            while((messageFromClient = in.readLine()) != null) {
                if(messageFromClient.equals("exit")){
                    break;
                }
                System.out.println("Message \"" + messageFromClient + "\" from " + clientMachine);
                JsonObject jobject = JsonParser.parseString(messageFromClient).getAsJsonObject();

                command.NewCommand(jobject);
            }


        } catch(IOException ex) {
            System.out.println("Shutting down single client server");
        } finally {
            closeQuietly();
        }
    }

    private void closeQuietly() {
        try { in.close(); out.close();
        } catch(IOException ignored) {}
    }

    public void sendResponse(String response){
        out.println(response);
        out.flush();
    }
}