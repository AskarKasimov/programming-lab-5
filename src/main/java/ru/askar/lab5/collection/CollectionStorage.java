package ru.askar.lab5.collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.askar.lab5.object.MusicBand;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class CollectionStorage {
    private static CollectionStorage instance;
    private final java.time.LocalDateTime dateOfCreation;
    private LinkedList<MusicBand> collection;
    // TODO: поменять LinkedList под вариант

    private CollectionStorage() {
        dateOfCreation = java.time.LocalDateTime.now();
        collection = new LinkedList<>();
    }

    public static CollectionStorage getInstance() {
        if (instance == null) {
            instance = new CollectionStorage();
        }
        return instance;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public LinkedList<MusicBand> getCollection() {
        return getInstance().collection;
    }

    public void loadFromFile(String filePath) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        FileReader reader = new FileReader(filePath);
        this.collection = gson.fromJson(reader, new TypeToken<LinkedList<MusicBand>>() {
        }.getType());
    }
//
//    public void removeById(long id) throws NoSuchIdException {
//        if (!collection.removeIf(item -> item.getId() == id)) {
//            throw new NoSuchIdException();
//        }
//    }
//
//    public void removeByFrontMan(Person frontMan) {
//        collection.removeIf(item -> item.getFrontMan().equals(frontMan));
//    }
//
//    public int countByNumberOfParticipants(int numberOfParticipants) {
//        int count = 0;
//        for (MusicBand item : collection) {
//            if (item.getNumberOfParticipants() == numberOfParticipants) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    public String filterByAlbumsCount(int albumsCount) {
//        StringBuilder result = new StringBuilder();
//        for (MusicBand item : collection) {
//            if (item.getAlbumsCount() == albumsCount) {
//                result.append(item).append("\n");
//            }
//        }
//        return result.toString();
//    }
//
//    public void updateById(long id, MusicBand newMusicBand) throws NoSuchIdException {
//        collection.forEach(item -> {
//            if (item.getId() == id) {
//                item = newMusicBand;
//            }
//        });
//    }
//
//    public void shuffle() {
//        Collections.shuffle(collection);
//    }
//
//    public void reorder() {
//        Collections.reverse(collection);
//    }
//
//    public void removeAtIndex(int index) {
//        collection.remove(index);
//    }
//
//    public void clear() {
//        collection.clear();
//    }
//
//    public void save(String path) {
//        // TODO: реализовать сохранение коллекции в файл
//    }
//
//    public String getAllElementsToString() {
//        StringBuilder result = new StringBuilder();
//        collection.forEach(item -> result.append(item).append("\n"));
//        return result.toString();
//    }
}