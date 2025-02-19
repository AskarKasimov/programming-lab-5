package ru.askar.lab5.collection;

import ru.askar.lab5.object.Ticket;

import java.io.IOException;
import java.util.TreeMap;

public interface DataReader {
    void readData() throws IOException;

    TreeMap<Long, Ticket> getData();

    String getSource();
}
