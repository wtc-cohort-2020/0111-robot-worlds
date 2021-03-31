package Client;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.*;
import java.io.*;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", 1999);
                PrintStream out = new PrintStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()))
        )
        {
            Scanner scanner = new Scanner(System.in);
            String input;
            JsonObject myRobot = new JsonObject();
            myRobot.addProperty("robot", "Gert");
            myRobot.addProperty("command", "launch");
            myRobot.addProperty("arguments", "");
            out.println(myRobot.toString());
            out.flush();
            String messageFromServer = in.readLine();
            System.out.println("Response: "+messageFromServer);
            while (true){
                input = scanner.nextLine();
                if(input.equalsIgnoreCase("quit")){
                    break;
                }

                myRobot = new JsonObject();
                myRobot.addProperty("robot", "Gert");

                if(input.equalsIgnoreCase("forward")){
                    myRobot.addProperty("command", "forward");
                    myRobot.addProperty("arguments", 5);
                    out.println(myRobot.toString());
                    out.flush();
                }
                if(input.equalsIgnoreCase("back")){
                    myRobot.addProperty("command", "back");
                    myRobot.addProperty("arguments", 5);
                    out.println(myRobot.toString());
                    out.flush();
                }
                if(input.equalsIgnoreCase("right")){
                    myRobot.addProperty("command", "turn");
                    myRobot.addProperty("arguments", "right");
                    out.println(myRobot.toString());
                    out.flush();
                }
                if(input.equalsIgnoreCase("left")){
                    myRobot.addProperty("command", "turn");
                    myRobot.addProperty("arguments", "left");
                    out.println(myRobot.toString());
                    out.flush();
                }
                messageFromServer = in.readLine();
                System.out.println("Response: "+messageFromServer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}