package ru.askar.lab5.collection;

import ru.askar.lab5.object.Event;
import ru.askar.lab5.object.Ticket;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Manager для коллекции билетов.
 */
public class CollectionManager {
    private final java.time.LocalDateTime dateOfInitialization;
    private final TreeMap<Long, Ticket> collection;

    public CollectionManager(TreeMap<Long, Ticket> collection) {
        dateOfInitialization = java.time.LocalDateTime.now();
        this.collection = collection;
    }

    public Long generateNextTicketId() {
        long min = 1;
        while (collection.containsKey(min)) {
            min++;
        }
        return min;
    }

    public Integer generateNextEventId() {
        Set<Integer> ids = collection.values().stream()
                .map(Ticket::getEvent)
                .filter(Objects::nonNull)
                .map(Event::getId)
                .collect(Collectors.toSet());

        int min = 1;
        while (ids.contains(min)) {
            min++;
        }
        return min;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfInitialization;
    }

    public TreeMap<Long, Ticket> getCollection() {
        return collection;
    }
}