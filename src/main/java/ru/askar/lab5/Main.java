package ru.askar.lab5;


import ru.askar.lab5.cli.CommandExecutor;
import ru.askar.lab5.cli.CommandParser;
import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.cli.output.Stdout;
import ru.askar.lab5.collection.CollectionManager;
import ru.askar.lab5.collection.DataReader;
import ru.askar.lab5.collection.JsonReader;
import ru.askar.lab5.command.*;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = System.getenv("lab5");
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Переменная окружения lab5 не установлена");
            return;
        }
        System.out.println("Используется файл: " + filePath);

        DataReader dataReader = new JsonReader();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
        dataReader.readData(bufferedInputStream);
        bufferedInputStream.close();

        CollectionManager collectionManager = new CollectionManager(dataReader.getData());
        OutputWriter outputWriter = new Stdout();
        CommandExecutor commandExecutor = new CommandExecutor(outputWriter);
        CommandParser commandParser = new CommandParser();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        InputReader inputReader = new InputReader(commandExecutor, commandParser, bufferedReader);

        commandExecutor.register(new HelpCommand(commandExecutor));
        commandExecutor.register(new InfoCommand(collectionManager));
        commandExecutor.register(new ShowCommand(collectionManager));
        commandExecutor.register(new InsertCommand(collectionManager, inputReader));
        commandExecutor.register(new UpdateCommand(collectionManager, inputReader));
        commandExecutor.register(new RemoveByKeyCommand(collectionManager));
        commandExecutor.register(new ClearCommand(collectionManager));
        commandExecutor.register(new SaveCommand(collectionManager));
        commandExecutor.register(new ScriptCommand(commandExecutor, commandParser));
        commandExecutor.register(new ExitCommand());
        commandExecutor.register(new RemoveLowerCommand(collectionManager, inputReader));
        commandExecutor.register(new ReplaceIfGreaterCommand(collectionManager, inputReader));
        commandExecutor.register(new RemoveGreaterKeyCommand(collectionManager));
        commandExecutor.register(new FilterStartsWithNameCommand(collectionManager));
        commandExecutor.register(new PrintFieldAscendingEventCommand(collectionManager));
        commandExecutor.register(new PrintFieldDescendingTypeCommand(collectionManager));

        inputReader.process();
    }
}