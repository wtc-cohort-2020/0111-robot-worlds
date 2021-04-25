package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String name = "";
        String type = "";
        Scanner scanner = new Scanner(System.in);

        Socket socket;
        PrintStream out = null;
        BufferedReader in = null;
        HandleCommand handleCommand;

        String input;

        try {
            socket = new Socket(args[0], Integer.parseInt(args[1]));
            out = new PrintStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("\nCould not connect to server. " +
                    "Please make sure you run the program with the correct IP address and Port as arguments.");
            System.exit(0);
        }

        //Ask for a name for the robot
        while (name.equals("")){
            System.out.println("Give me a name: ");
            input = scanner.nextLine();
            name = input;
        }

        //Ask for the type of robot
        while (type.equals("")){
            System.out.println("What type of robot do you want{sniper, pistol, standard, bomber}: ");
            input = scanner.nextLine();
            type = input;
        }

        //Run ReceiveMessages in new thread to be able to constantly receive messages from server
        Runnable r = new ReceiveMessages(in);
        Thread task = new Thread(r);
        task.start();
        handleCommand = new HandleCommand(name, type, out);

        handleCommand.newCommand("launch");

        //Get input from user and call handleCommand with input
        while (true){
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("quit")){
                System.exit(0);
            }
            handleCommand.newCommand(input.toLowerCase(Locale.ROOT));
        }
    }
}

//                Socket socket = new Socket("127.0.0.1", 1999);