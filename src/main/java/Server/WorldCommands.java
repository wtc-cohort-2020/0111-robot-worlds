package Server;

import com.google.gson.JsonObject;

import java.util.Scanner;

public class WorldCommands{
    static String command;
    static String[] arguments = new String[2];

    public WorldCommands() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true){
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("quit")){
                break;
            }

            System.out.println(input);
        }
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
