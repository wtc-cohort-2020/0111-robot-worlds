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
    static String command;
    static String[] arguments = new String[2];


    public static void main(String[] args) {
        try (
                Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
                PrintStream out = new PrintStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()))
        )
        {
            Scanner scanner = new Scanner(System.in);
            String input;
            System.out.println("Give me a name: ");
            input = scanner.nextLine();
            JsonObject myRobot = new JsonObject();
            myRobot.addProperty("robot", input);
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

                splitCommand(input);
                System.out.println(command);
                System.out.println(arguments[0]);

                myRobot.addProperty("command", command);

                if (arguments.length == 2) {
                    myRobot.addProperty("arguments", arguments[0]);
                }
                if (arguments.length==1) {
                    myRobot.addProperty("arguments", arguments[0]);
                }
                out.println(myRobot.toString());
                out.flush();

                messageFromServer = in.readLine();
                System.out.println("Response: "+messageFromServer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void commandString(String input) {
        System.out.println(input);
        splitCommand(input);

    }




    public static void splitCommand(String input) {
        String [] commandAndArgs = input.split(" ");

        switch(commandAndArgs.length) {
            case 1:
                command = commandAndArgs[0];
                return;

            case 2:

                command = commandAndArgs[0];
                arguments[0] = commandAndArgs[1];
                return;


            case 3:
                command = commandAndArgs[0];
                arguments[0] = commandAndArgs[1];
                arguments[1] = commandAndArgs[2];
                return;
        }
    }

}

//                Socket socket = new Socket("127.0.0.1", 1999);