package ru.askar.lab5.cli;

import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.command.Command;
import ru.askar.lab5.exception.NoSuchCommandException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final OutputWriter outputWriter;
    private final Map<String, Command> commands = new HashMap<>();

    public CommandExecutor(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    public void register(Command command) {
        command.setOutputWriter(this.outputWriter);
        commands.put(command.getName(), command);
    }

    public Command getCommand(String name) throws NoSuchCommandException {
        Command command = commands.get(name);
        if (command == null) {
            throw new NoSuchCommandException();
        }
        return commands.get(name);
    }

    public Map<String, Command> getAllCommands() {
        return new HashMap<>(commands);
    }
}