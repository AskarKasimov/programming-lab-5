package ru.askar.lab5.command;

import ru.askar.lab5.cli.CommandExecutor;

public class HelpCommand extends Command {
    private final CommandExecutor executor;

    public HelpCommand(CommandExecutor executor) {
        super("help");
        this.executor = executor;
    }

    @Override
    public void execute(String[] args) {
        executor.getAllCommands().values().forEach(command -> outputWriter.writeOnSuccess(command.getInfo()));
    }

    @Override
    public String getInfo() {
        return "help - вывести справку по доступным командам";
    }
}
