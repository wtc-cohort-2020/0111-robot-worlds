package Server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.Socket;

public class Server implements Runnable {

    private final BufferedReader in;
    private final PrintStream out;
    private Robot robot;
    private final World world;

    public Server(Socket socket, World world) throws IOException {
        this.world = world;
        String clientMachine = socket.getInetAddress().getHostName();
        System.out.println("Connection from " + clientMachine);

        out = new PrintStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
    }

    public void run() {
        try {
            String messageFromClient;
            JsonObject jsonMessage;
            RobotCommand command = new RobotCommand(this, world);
            while((messageFromClient = in.readLine()) != null) {
//                System.out.println("Message \"" + messageFromClient + "\" from " + clientMachine);
                jsonMessage  = JsonParser.parseString(messageFromClient).getAsJsonObject();
                command.NewCommand(jsonMessage);
            }
        } catch(IOException ex) {
            System.out.println("Shutting down single client server");
        } finally {
            robot.setStatus(RobotStatus.DEAD);
            world.RemoveRobot(robot);
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

    public void setRobot(Robot robot){
        this.robot = robot;
    }
}