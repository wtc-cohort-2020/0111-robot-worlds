

import com.google.gson.Gson;

import java.net.*;
import java.io.*;
import java.security.cert.TrustAnchor;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static String name;
    static String command;
    static String [] arguments = new String[2];
    static Scanner scanner = new Scanner(System.in);;



    public static void main(String[] args) {
        /*
       Separate try-catch for invalid args?
         */


        try (
                //Socket socket = new Socket("127.0.0.1", 1999);
                Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
                PrintStream out = new PrintStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()))
        )
        {
            name = getInput("Please enter your name: ");
            String input;
            out.println("Hello WeThinkCode");
            out.flush();
            String messageFromServer = in.readLine();
            System.out.println("Response: "+messageFromServer);


            while (true){


                input = scanner.nextLine();
                if(input.equalsIgnoreCase("exit")){
                    break;
                }
                else {
                    /*
                    Some code to handle the command
                     */
                    commandString(input);
                    transformtoJSOn();




                    out.println(input);
                    out.flush();
                }
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
//        return new ClientCommand(name, command, arguments);
    }


    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
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


    public static void transformtoJSOn() {
        Gson gson = new Gson();
        ClientCommand myCommand = new ClientCommand(name, command, arguments);
        String jsonInString = gson.toJson(myCommand);
        System.out.println(jsonInString);
    }

}