package ru.askar.lab5;


import ru.askar.lab5.cli.CommandExecutor;
import ru.askar.lab5.cli.CommandParser;
import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.cli.output.Stdout;
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

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        InputReader inputReader = new InputReader(commandExecutor, commandParser, bufferedReader);

        commandExecutor.register(new HelpCommand(commandExecutor));
        commandExecutor.register(new InfoCommand());
        commandExecutor.register(new ShowCommand());
        commandExecutor.register(new InsertCommand(inputReader));
        commandExecutor.register(new UpdateCommand(inputReader));
        commandExecutor.register(new RemoveByKeyCommand());
        commandExecutor.register(new ClearCommand());
        commandExecutor.register(new SaveCommand());
        commandExecutor.register(new ScriptCommand(commandExecutor, commandParser));
        commandExecutor.register(new ExitCommand());
        commandExecutor.register(new RemoveLowerCommand(inputReader));
        commandExecutor.register(new ReplaceIfGreaterCommand(inputReader));
        commandExecutor.register(new RemoveGreaterKeyCommand());
        commandExecutor.register(new FilterStartsWithNameCommand());
        commandExecutor.register(new PrintFieldAscendingEventCommand());
        commandExecutor.register(new PrintFieldDescendingTypeCommand());

        String filePath = System.getenv("lab5");

        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Переменная окружения lab5 не установлена");
            return;
        }

//        CollectionManager.getInstance().loadFromFile(filePath);

        inputReader.process();
    }
}