package Client;

import java.io.PrintStream;


public class HandleCommand {
    private final String robotType;
    private final String robotName;
    private final PrintStream out;
    private String command;
    private final String[] arguments = new String[2];
    private final Request request = new Request();


    public HandleCommand(String robotName,String type, PrintStream out){
        this.robotName = robotName;
        this.robotType = type;
        this.out = out;
    }


    public void newCommand(String input){
        //This function sends the appropriate message to the sever according to user input
        splitCommand(input);
        switch (command){
            case "fire" -> {
                out.println(request.fire(robotName));
                out.flush();
            }
            case "launch" -> {
                out.println(request.launch(robotType, robotName));
                out.flush();
            }
            case "state" -> {
                out.println(request.state(robotName));
                out.flush();
            }
            case "look" -> {
                out.println(request.look(robotName));
                out.flush();
            }
            case "forward" -> {
                out.println(request.forward(arguments[0], robotName));
                out.flush();
            }
            case "back" -> {
                out.println(request.back(arguments[0], robotName));
                out.flush();
            }
            case "mine" -> {
                out.println(request.setMine(robotName));
                out.flush();
            }
            case "repair" -> {
                out.println(request.repair(robotName));
                out.flush();
            }
            case "reload" -> {
                out.println(request.reload(robotName));
                out.flush();
            }
            case "right" -> {
                out.println(request.right(robotName));
                out.flush();
            }
            case "left" -> {
                out.println(request.left(robotName));
                out.flush();
            }
            default -> System.out.println("I did not understand: " + input);
        }

    }

    private void splitCommand(String input) {
        //This function splits the input from the user into the command and arguments
        String [] commandAndArgs = input.split(" ");

        switch (commandAndArgs.length) {
            case 1 -> command = commandAndArgs[0];
            case 2 -> {
                command = commandAndArgs[0];
                arguments[0] = commandAndArgs[1];
            }
            case 3 -> {
                command = commandAndArgs[0];
                arguments[0] = commandAndArgs[1];
                arguments[1] = commandAndArgs[2];
            }
        }
    }
}
