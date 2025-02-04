package ru.askar.lab5.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.collection.LocalDateTimeAdapter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class SaveCommand extends Command {
    public SaveCommand() {
        super("save", 1);
    }

    @Override
    public void execute(String[] args) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        String filePath = args[0];
        String json = gson.toJson(CollectionStorage.getInstance().getCollection()); // Преобразуем коллекцию в JSON и записываем в файл
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(json.getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Файл не найден!");
        } catch (SecurityException e) {
            throw new SecurityException("Недостаточно прав для записи в файл!");
        } catch (IOException e) {
            throw e;
        }
        outputWriter.writeOnSuccess("Коллекция успешно записана в файл!");
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
