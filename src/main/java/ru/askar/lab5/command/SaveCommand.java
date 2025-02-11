package ru.askar.lab5.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.askar.lab5.collection.CollectionManager;

import java.io.FileOutputStream;
import java.io.IOException;

public class SaveCommand extends Command {
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save", 1);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        // Отключаем вывод даты в виде массива
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try (FileOutputStream fileOutputStream = new FileOutputStream(args[0])) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileOutputStream, collectionManager.getCollection().values());
            outputWriter.writeOnSuccess("JSON успешно записан в файл " + args[0]);
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
