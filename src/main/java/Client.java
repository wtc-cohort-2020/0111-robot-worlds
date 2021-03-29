

import java.net.*;
import java.io.*;
import java.security.cert.TrustAnchor;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static String name;
    private static String command;
    static String [] arguments;
    static Scanner scanner;



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
//            Scanner scanner = new Scanner(System.in);
//            String name = getInput("What do you want to name your robot?");
            out.println("Hello WeThinkCode");
            out.flush();
            String messageFromServer = in.readLine();
            System.out.println("Response: "+messageFromServer);
            while (true){
                // Get name of robot
                name = getInput("Please enter your name: ");
                command = getInput("Start Up!" + name + ", enter a command: ");
                if(command.equalsIgnoreCase("exit")){
                    break;
                }
                else {
                    /*
                    Some code to handle the command
                     */
                    




                    out.println(command);
                    out.flush();
                }
                messageFromServer = in.readLine();
                System.out.println("Response: "+messageFromServer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientCommand commandString() {
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();



        return new ClientCommand(name, command, arguments);
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


}