package ru.askar.lab5.collection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.askar.lab5.exception.InvalidCollectionFileException;
import ru.askar.lab5.object.Ticket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonReader implements DataReader {
    private TreeMap<Long, Ticket> collection;

    @Override
    public void readData(BufferedInputStream inputStream) throws IOException {
        validateJsonKeys(inputStream);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Map<String, Ticket> tempMap = objectMapper.readValue(inputStream, new TypeReference<>() {
        });
        TreeMap<Long, Ticket> tickets = new TreeMap<>();
        for (Map.Entry<String, Ticket> entry : tempMap.entrySet()) {
            tickets.put(Long.parseLong(entry.getKey()), entry.getValue());
        }
        this.collection = tickets;
    }

    @Override
    public TreeMap<Long, Ticket> getData() {
        return collection;
    }

    @Override
    public void validateData() throws InvalidCollectionFileException {
        TreeMap<Long, Ticket> redactedTickets = new TreeMap<>();
        ArrayList<Integer> eventsIds = new ArrayList<>(collection.values().stream().map((ticket -> ticket.getEvent() != null ? ticket.getEvent().getId() : 0)).toList());
        collection.forEach((id, ticket) -> {
            if (ticket.getEvent() != null && Collections.frequency(eventsIds, ticket.getEvent().getId()) > 1) {
                throw new InvalidCollectionFileException("Ошибка: были обнаружены события (Event) с одинаковыми id");
            }
            if (!Objects.equals(ticket.getId(), id)) {
                System.out.println("Внимание: был обнаружен билет с несовпадением ключа и id. Исправляю путём замены ключа на id...");
                if (redactedTickets.containsKey(ticket.getId())) {
                    throw new InvalidCollectionFileException("Ошибка: были обнаружены билеты (Ticket) с одинаковыми id");
                }
                redactedTickets.put(ticket.getId(), ticket);
            } else redactedTickets.put(id, ticket);
        });
        redactedTickets.forEach((id, ticket) -> System.out.println(id + ": " + ticket));
    }

    private static void validateJsonKeys(BufferedInputStream bufferedInputStream) throws IOException {
        String json = readAllLines(bufferedInputStream);
        Matcher matcher = Pattern.compile("\"(\\d+)\":").matcher(json);
        Set<String> keys = new HashSet<>();

        while (matcher.find()) {
            String key = matcher.group(1);
            if (!keys.add(key)) {
                throw new InvalidCollectionFileException("Ошибка: дублирующийся ключ: " + key);
            }
        }
    }

    private static String readAllLines(BufferedInputStream bis) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(bis))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString().trim();
    }

}