import cli.CommandExecutor;
import cli.CommandParser;
import command.HelpCommand;
import command.InfoCommand;

public class Main {
    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        CommandParser commandParser = new CommandParser();

        commandExecutor.register(new InfoCommand());
        commandExecutor.register(new HelpCommand(commandExecutor));


    }
}