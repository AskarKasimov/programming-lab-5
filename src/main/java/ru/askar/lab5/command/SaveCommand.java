package ru.askar.lab5.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.collection.LocalDateTimeAdapter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class SaveCommand extends Command {
    public SaveCommand() {
        super("save");
    }

    @Override
    public void execute(String[] args) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        String filePath = args[0];
        // TODO: обрабатывать ситуации с неправильным количеством аргументов
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(CollectionStorage.getInstance().getCollection(), writer); // Преобразуем коллекцию в JSON и записываем в файл
            outputWriter.writeOnSuccess("Коллекция успешно записана в файл: " + filePath);
        } catch (IOException e) {
            outputWriter.writeOnFail("Ошибка при записи коллекции в файл: " + e.getMessage());
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
