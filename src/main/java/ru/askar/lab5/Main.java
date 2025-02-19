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
    public static void main(String[] args) {
        OutputWriter outputWriter = new Stdout();
        String filePath = System.getenv("lab5");
        if (filePath == null || filePath.isEmpty()) {
            outputWriter.writeOnFail("Переменная окружения lab5 не установлена");
            return;
        }
        outputWriter.write("Используется файл: " + filePath);

        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
        } catch (FileNotFoundException | SecurityException e) {
            outputWriter.writeOnFail("Файл не удаётся прочитать: " + e.getMessage());
        }

        DataReader dataReader = new JsonReader(filePath, bufferedInputStream);
        if (bufferedInputStream == null) {
            dataReader = null;
        }

        CollectionManager collectionManager = null;
        try {
            collectionManager = new CollectionManager(dataReader);
        } catch (Exception e) {
            outputWriter.writeOnFail(e.getMessage());
        } finally {
            try {
                if (bufferedInputStream != null) bufferedInputStream.close();
            } catch (IOException e) {
                outputWriter.writeOnFail("Ошибка при закрытии файла: " + e.getMessage());
            }
        }
        if (collectionManager == null) {
            try {
                collectionManager = new CollectionManager(null);
            } catch (Exception e) {
                // ignored
            }
        }
        if (collectionManager.getCollection().isEmpty()) {
            outputWriter.writeOnWarning("Коллекция пуста");
        }
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
        commandExecutor.register(new SaveCommand(collectionManager, inputReader));
        commandExecutor.register(new ScriptCommand(commandExecutor, commandParser));
        commandExecutor.register(new ExitCommand());
        commandExecutor.register(new RemoveLowerCommand(collectionManager, inputReader));
        commandExecutor.register(new ReplaceIfGreaterCommand(collectionManager, inputReader));
        commandExecutor.register(new RemoveGreaterKeyCommand(collectionManager));
        commandExecutor.register(new FilterStartsWithNameCommand(collectionManager));
        commandExecutor.register(new PrintFieldAscendingEventCommand(collectionManager));
        commandExecutor.register(new PrintFieldDescendingTypeCommand(collectionManager));

        try {
            inputReader.process();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}