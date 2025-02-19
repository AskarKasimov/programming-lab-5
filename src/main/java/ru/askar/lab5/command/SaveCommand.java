package ru.askar.lab5.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.collection.CollectionManager;

import java.io.FileOutputStream;
import java.io.IOException;

public class SaveCommand extends Command {
    private final CollectionManager collectionManager;
    private final InputReader inputReader;

    public SaveCommand(CollectionManager collectionManager, InputReader inputReader) {
        super("save", 0);
        this.collectionManager = collectionManager;
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String[] args) throws IOException {
        String newFileName;
        if (collectionManager.getStarterSource() == null || collectionManager.getStarterSource().isEmpty()) {
            outputWriter.writeOnFail("Невозможно сохранить коллекцию в файл, так как исходный файл не был указан. Введите новое имя файла:");
            newFileName = inputReader.getInputString();
        } else {
            newFileName = collectionManager.getStarterSource();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        // Отключаем вывод даты в виде массива
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try (FileOutputStream fileOutputStream = new FileOutputStream(newFileName)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileOutputStream, collectionManager.getCollection().values());
            outputWriter.writeOnSuccess("JSON успешно записан в первоначальный файл " + newFileName);
        } catch (IOException e) {
            throw new IOException("Ошибка при записи коллекции в файл " + e.getMessage());
        }
    }

    @Override
    public String getInfo() {
        return "save - сохранить коллекцию в файл";
    }

    @Override
    public String getName() {
        return "save";
    }
}
