package ru.askar.lab5;


import ru.askar.lab5.cli.CommandExecutor;
import ru.askar.lab5.cli.CommandParser;
import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.cli.output.Stdout;
import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.command.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setOut(new PrintStream(System.out, true, "CP1251"));

        OutputWriter outputWriter = new Stdout();
        CommandExecutor commandExecutor = new CommandExecutor(outputWriter);
        CommandParser commandParser = new CommandParser();

        commandExecutor.register(new InfoCommand());
        commandExecutor.register(new HelpCommand(commandExecutor));
        commandExecutor.register(new AppendCommand());
        commandExecutor.register(new SaveCommand());
        commandExecutor.register(new ShowCommand());
        commandExecutor.register(new RemoveCommand());
        commandExecutor.register(new ClearCommand());
        commandExecutor.register(new ShuffleCommand());
        commandExecutor.register(new ReorderCommand());
        commandExecutor.register(new ExitCommand());
        commandExecutor.register(new RemoveIndexCommand());

        InputReader inputReader = new InputReader(commandExecutor, commandParser);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        CollectionStorage.getInstance().loadFromFile("collection.json");

        inputReader.process(bufferedReader);
    }
}