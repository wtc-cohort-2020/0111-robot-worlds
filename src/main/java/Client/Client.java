//package Client;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//
//import java.net.*;
//import java.io.*;
//import java.security.cert.TrustAnchor;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
//
//public class Client {
//    static String command;
//    static String[] arguments = new String[3];
//    static String name;
//
//
//    public static void main(String[] args) {
//        try (
//                Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
//                PrintStream out = new PrintStream(socket.getOutputStream());
//                BufferedReader in = new BufferedReader(new InputStreamReader(
//                        socket.getInputStream()))
//        ) {
//            Scanner scanner = new Scanner(System.in);
//
//
////            String messageFromServer = in.readLine();
////            System.out.println("Response: "+messageFromServer);
//            while (true) {
//                String input;
//                input = scanner.nextLine();
//                if (input.equalsIgnoreCase("quit")) {
//                    break;
//                }
//
//                JsonObject myRobot = new JsonObject();
//
//
//                splitCommand(input);
////                System.out.println(name);
////                System.out.println(command);
//                System.out.println(Arrays.toString(arguments));
//
//                Gson gson = new Gson();
//
//                myRobot.addProperty("name", name);
//                myRobot.addProperty("command", command);
//                String myArgs = gson.toJson(arguments);
//                myRobot.addProperty("arguments", myArgs);
//
//
//                out.println(myRobot.toString());
//                out.flush();
//
//                String messageFromServer = in.readLine();
//                System.out.println("Response: " + messageFromServer);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void commandString(String input) {
//        System.out.println(input);
//        splitCommand(input);
//
//    }
//
//
//    public static void splitCommand(String input) {
//        String[] commandAndArgs = input.split(" ");
//
//        switch (commandAndArgs.length) {
//            case 1:
//                command = commandAndArgs[0];
//                return;
//
//            case 2:
//
//                command = commandAndArgs[0];
//                arguments[0] = commandAndArgs[1];
//                return;
//
//
//            case 3:
//
//                command = commandAndArgs[0];
//                System.out.println(commandAndArgs[1]);
//                if (commandAndArgs[1].equals("pistol")) {
//                    arguments[0] = "pistol";
//                    arguments[1] = "3";
//                    arguments[2] = "4";
//                }
//
//
//                else if (commandAndArgs[1].equals("sniper")) {
//                    arguments[0] = "sniper";
//                    arguments[1] = "3";
//                    arguments[2] = "4";
//                }
//
//                else if (commandAndArgs[1].equals("standard")) {
//                    arguments[0] = "standard";
//                    arguments[1] = "3";
//                    arguments[2] = "3";
//                }
//
//                else {
//                        arguments[0] = "shouldBeAnError";
//                        arguments[1] = "3";
//                        arguments[2] = "3";
//                }
//                name = commandAndArgs[2];
//
//        }
//
//    }
//}

package Client;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
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
            String name;
            Scanner scanner = new Scanner(System.in);
            String input;
            System.out.println("Give me a name: ");
            input = scanner.nextLine();
            name = input;
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
                myRobot.addProperty("robot", name);

                splitCommand(input);
//                System.out.println(command);
//                System.out.println(arguments[0]);

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