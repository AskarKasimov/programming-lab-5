package ru.askar.lab5.collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.askar.lab5.object.Ticket;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.TreeMap;

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

    public void loadFromFile(String filePath) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        FileReader reader = new FileReader(filePath);
        this.collection = gson.fromJson(reader, new TypeToken<TreeMap<Long, Ticket>>() {
        }.getType());
    }
}