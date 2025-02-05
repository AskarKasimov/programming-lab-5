package ru.askar.lab5.collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.askar.lab5.object.Event;
import ru.askar.lab5.object.Ticket;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.TreeMap;

/**
 * Singleton для управления главной коллекцией всея CLI
 */
public class CollectionStorage {
    private static CollectionStorage instance;
    private final java.time.LocalDateTime dateOfInitialization;
    private TreeMap<Long, Ticket> collection;

    private CollectionStorage() {
        dateOfInitialization = java.time.LocalDateTime.now();
        collection = new TreeMap<>();
    }

    public static CollectionStorage getInstance() {
        if (instance == null) {
            instance = new CollectionStorage();
        }
        return instance;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfInitialization;
    }

    public TreeMap<Long, Ticket> getCollection() {
        return getInstance().collection;
    }

    /**
     * Заполнить коллекцию json-данными из файла
     *
     * @param filePath - путь к файлу
     * @throws IOException - ошибка чтения
     */
    public void loadFromFile(String filePath) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            byte[] data = bis.readAllBytes();
            String json = new String(data, StandardCharsets.UTF_8);

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();
            this.collection = gson.fromJson(json, new TypeToken<TreeMap<Long, Ticket>>() {
            }.getType());
            this.collection.values().forEach(ticket -> {
                if (ticket.getId() >= Ticket.getNextId()) {
                    Ticket.setNextId(ticket.getId() + 1);
                }
                if (ticket.getEvent() != null && ticket.getEvent().getId() >= Event.getNextId()) {
                    Event.setNextId(ticket.getEvent().getId() + 1);
                }
            });
            if (this.collection == null) {
                this.collection = new TreeMap<>();
            }
        } catch (IOException e) {
            throw e;
        }
    }
}