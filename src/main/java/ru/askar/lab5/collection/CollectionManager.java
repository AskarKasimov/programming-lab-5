package ru.askar.lab5.collection;

import ru.askar.lab5.object.Event;
import ru.askar.lab5.object.Ticket;

import java.io.BufferedInputStream;
import java.io.IOException;
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
    private final DataReader starterDataReader;

    public CollectionManager(DataReader dataReader, BufferedInputStream bufferedInputStream) throws IOException {
        dateOfInitialization = java.time.LocalDateTime.now();
        this.starterDataReader = dataReader;
        this.starterDataReader.readData(bufferedInputStream);
        this.collection = this.starterDataReader.getData();
    }

    public String getStarterSource() {
        return starterDataReader.getSource();
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