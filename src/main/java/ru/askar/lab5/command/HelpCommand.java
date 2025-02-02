package ru.askar.lab5.command;

import ru.askar.lab5.cli.CommandExecutor;

public class HelpCommand implements Command {
    private final CommandExecutor executor;

    public HelpCommand(CommandExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(String[] args) {
        executor.getAllCommands().values().forEach(command -> System.out.println(command.getInfo()));
    }

    @Override
    public String getInfo() {
        return "help - вывести справку по доступным командам";
    }

    @Override
    public String getName() {
        return "help";
    }
}
