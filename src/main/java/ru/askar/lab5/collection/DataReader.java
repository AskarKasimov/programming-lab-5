package ru.askar.lab5.collection;

import ru.askar.lab5.object.Ticket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.TreeMap;

public interface DataReader {
    void readData(BufferedInputStream bufferedInputStream) throws IOException;

    TreeMap<Long, Ticket> getData();

    String getSource();
}
