package ru.askar.lab5;


import com.fasterxml.jackson.databind.JsonMappingException;
import ru.askar.lab5.cli.CommandExecutor;
import ru.askar.lab5.cli.CommandParser;
import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.cli.output.Stdout;
import ru.askar.lab5.collection.CollectionManager;
import ru.askar.lab5.collection.DataReader;
import ru.askar.lab5.collection.JsonReader;
import ru.askar.lab5.command.*;
import ru.askar.lab5.exception.InvalidCollectionFileException;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        OutputWriter outputWriter = new Stdout();
        String filePath = System.getenv("lab5");
        if (filePath == null || filePath.isEmpty()) {
            outputWriter.writeOnFail("Переменная окружения lab5 не установлена");
            return;
        }
        outputWriter.write("Используется файл: " + filePath);

        DataReader dataReader = new JsonReader();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            dataReader.readData(bufferedInputStream);
            outputWriter.writeOnSuccess("Файл успешно захаван:)))");
        } catch (JsonMappingException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                outputWriter.writeOnFail("Критическая ошибка поля структуры: " + cause.getMessage());
            } else {
                outputWriter.writeOnFail("Неизвестная ошибка считывания данных из файла: " + e.getOriginalMessage());
            }
            outputWriter.writeOnWarning("Внимание: используется пустая коллекция");
        } catch (InvalidCollectionFileException e) {
            outputWriter.writeOnFail("Критическая ошибка читаемого файла: " + e.getMessage());
            outputWriter.writeOnWarning("Внимание: используется пустая коллекция");
        } catch (IOException e) {
            outputWriter.write("Ошибка при чтении файла: " + e.getMessage());
            outputWriter.writeOnWarning("Внимание: используется пустая коллекция");
        }

        CollectionManager collectionManager = new CollectionManager(dataReader.getData());

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