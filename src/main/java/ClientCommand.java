import java.util.List;

public class ClientCommand {

    private String name;
    private static String command;
    private static String[] arguments;
    private static final String [] valid_commands = ["launch","look"];

    public ClientCommand (String name, String command, String[] arguments){
        this.name = name;
        this.command = command;
        this.arguments = arguments;
    }

    public void handleCommand() {
        if (command.equals("lau"))
    }
}
