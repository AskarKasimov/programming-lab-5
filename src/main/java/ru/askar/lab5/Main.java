package ru.askar.lab5;


import ru.askar.lab5.cli.CommandExecutor;
import ru.askar.lab5.cli.CommandParser;
import ru.askar.lab5.command.HelpCommand;
import ru.askar.lab5.command.InfoCommand;

public class Main {
    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        CommandParser commandParser = new CommandParser();

        commandExecutor.register(new InfoCommand());
        commandExecutor.register(new HelpCommand(commandExecutor));

        System.out.println("Hello, Maven!");
    }
}