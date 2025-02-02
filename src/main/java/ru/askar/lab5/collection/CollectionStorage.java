package ru.askar.lab5.collection;

import ru.askar.lab5.object.MusicBand;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class CollectionStorage {
    private static CollectionStorage instance;
    private final java.time.LocalDateTime dateOfCreation;
    private final LinkedList<MusicBand> collection;
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