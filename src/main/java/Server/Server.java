package Server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Server implements Runnable {

    public static final int PORT = 1999;
    private final BufferedReader in;
    private final PrintStream out;
    private final String clientMachine;
    private Robot robot;
    private RobotCommand command;
    private final World world;

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
            command = new RobotCommand(this, world);
            while((messageFromClient = in.readLine()) != null) {
                System.out.println("Message \"" + messageFromClient + "\" from " + clientMachine);
                jsonMessage  = JsonParser.parseString(messageFromClient).getAsJsonObject();
//                jsonMessage = (messageFromClient).getAsJsonObject();
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