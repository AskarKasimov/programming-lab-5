package ru.askar.lab5.collection;

import ru.askar.lab5.exception.NoSuchIdException;
import ru.askar.lab5.object.MusicBand;
import ru.askar.lab5.object.Person;

import java.util.Collections;
import java.util.LinkedList;

public class CollectionStorage {
    private static CollectionStorage instance;
    private final LinkedList<MusicBand> collection;
    // TODO: поменять LinkedList под вариант

    private CollectionStorage() {
        collection = new LinkedList<>();
    }

    public static CollectionStorage getInstance() {
        if (instance == null) {
            instance = new CollectionStorage();
        }
        return instance;
    }

    public void add(MusicBand item) {
        collection.add(item);
    }

    public void removeById(long id) throws NoSuchIdException {
        if (!collection.removeIf(item -> item.getId() == id)) {
            throw new NoSuchIdException();
        }
    }

    public void removeByFrontMan(Person frontMan) {
        collection.removeIf(item -> item.getFrontMan().equals(frontMan));
    }

    public int countByNumberOfParticipants(int numberOfParticipants) {
        int count = 0;
        for (MusicBand item : collection) {
            if (item.getNumberOfParticipants() == numberOfParticipants) {
                count++;
            }
        }
        return count;
    }

    public String filterByAlbumsCount(int albumsCount) {
        StringBuilder result = new StringBuilder();
        for (MusicBand item : collection) {
            if (item.getAlbumsCount() == albumsCount) {
                result.append(item).append("\n");
            }
        }
        return result.toString();
    }

    public void updateById(long id, MusicBand newMusicBand) throws NoSuchIdException {
        if (!collection.removeIf(item -> item.getId() == id)) {
            throw new NoSuchIdException();
        }
        collection.add(newMusicBand);
    }

    public void shuffle() {
        Collections.shuffle(collection);
    }

    public void reorder() {
        Collections.reverse(collection);
    }

    public void removeAtIndex(int index) {
        collection.remove(index);
    }

    public void clear() {
        collection.clear();
    }

    public void save(String path) {
        // TODO: реализовать сохранение коллекции в файл
    }

    public String getAllElementsToString() {
        StringBuilder result = new StringBuilder();
        collection.forEach(item -> result.append(item).append("\n"));
        return result.toString();
    }
}