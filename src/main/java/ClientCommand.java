import java.util.Arrays;
import java.util.List;

public class ClientCommand {

    private String name;
    private static String command;
    private static String[] arguments = new String[2];


    public ClientCommand (String name, String command, String[] arguments){
        this.name = name;
        this.command = command.toLowerCase();
        this.arguments = arguments;

        System.out.println(name);
        System.out.println(command);
        System.out.println(Arrays.toString(arguments));
    }




}
