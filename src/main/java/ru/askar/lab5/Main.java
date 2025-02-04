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

        commandExecutor.register(new HelpCommand(commandExecutor));
        commandExecutor.register(new InfoCommand());
        commandExecutor.register(new ShowCommand());
//        commandExecutor.register(new AddCommand());
//        commandExecutor.register(new UpdateCommand());
        commandExecutor.register(new RemoveByIdCommand());
        commandExecutor.register(new ClearCommand());
        commandExecutor.register(new SaveCommand());
        commandExecutor.register(new ScriptCommand(commandExecutor, commandParser));
        commandExecutor.register(new ExitCommand());
        commandExecutor.register(new RemoveByIndexCommand());
        commandExecutor.register(new ShuffleCommand());
        commandExecutor.register(new ReorderCommand());
//        commandExecutor.register(new RemoveByFrontManCommand());
        commandExecutor.register(new CountByNumberOfParticipantsCommand());
        commandExecutor.register(new FilterByAlbumsCountCommand());

        InputReader inputReader = new InputReader(commandExecutor, commandParser);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String filePath = System.getenv("lab5");

        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Переменная окружения lab5 не установлена");
            return;
        }

        CollectionStorage.getInstance().loadFromFile(filePath);

        inputReader.process(bufferedReader);
    }
}