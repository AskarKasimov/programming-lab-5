package ru.askar.lab5.collection;

import ru.askar.lab5.object.Ticket;

import java.time.LocalDateTime;
import java.util.TreeMap;

/**
 * Manager для коллекции билетов.
 */
public class CollectionManager {
    private final java.time.LocalDateTime dateOfInitialization;
    private final TreeMap<Long, Ticket> collection;

    private CollectionManager(TreeMap<Long, Ticket> collection) {
        dateOfInitialization = java.time.LocalDateTime.now();
        this.collection = collection;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfInitialization;
    }

    public TreeMap<Long, Ticket> getCollection() {
        return collection;
    }
}