package cli;

import command.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Map<String, Command> commands = new HashMap<>();

    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }

    public Map<String, Command> getAllCommands() {
        return new HashMap<>(commands);
    }
}