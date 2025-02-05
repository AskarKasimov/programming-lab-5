package ru.askar.lab5.command;

import ru.askar.lab5.cli.CommandExecutor;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends Command {
    private final CommandExecutor executor;

    public HelpCommand(CommandExecutor executor) {
        super("help", 0);
        this.executor = executor;
    }

    @Override
    public void execute(String[] args) {
        List<Command> commands = new ArrayList<>(executor.getAllCommands().values());
        commands.forEach(command -> outputWriter.writeOnSuccess(command.getInfo()));
    }

    @Override
    public String getInfo() {
        return "help - вывести справку по доступным командам";
    }
}
