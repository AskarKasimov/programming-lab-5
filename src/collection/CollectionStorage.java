package collection;

import exception.NoSuchIdException;
import object.MusicBand;
import object.Person;

import java.util.Collections;
import java.util.LinkedList;

public class CollectionStorage {
    private static final LinkedList<MusicBand> collection = new LinkedList<>();
    // TODO: поменять LinkedList под вариант

    public static void add(MusicBand item) {
        collection.add(item);
    }

    public static void removeById(long id) throws NoSuchIdException {
        if (!collection.removeIf(item -> item.getId() == id)) {
            throw new NoSuchIdException();
        }
    }

    public static void removeByFrontMan(Person frontMan) {
        collection.removeIf(item -> item.getFrontMan().equals(frontMan));
    }

    public static int countByNumberOfParticipants(int numberOfParticipants) {
        int count = 0;
        for (MusicBand item : collection) {
            if (item.getNumberOfParticipants() == numberOfParticipants) {
                count++;
            }
        }
        return count;
    }

    public static String filterByAlbumsCount(int albumsCount) {
        StringBuilder result = new StringBuilder();
        for (MusicBand item : collection) {
            if (item.getAlbumsCount() == albumsCount) {
                result.append(item).append("\n");
            }
        }
        return result.toString();
    }

    public static void updateById(long id, MusicBand newMusicBand) throws NoSuchIdException {
        if (!collection.removeIf(item -> item.getId() == id)) {
            throw new NoSuchIdException();
        }
        collection.add(newMusicBand);
    }

    public static void shuffle() {
        Collections.shuffle(collection);
    }

    public static void reorder() {
        Collections.reverse(collection);
    }

    public static void removeAtIndex(int index) {
        collection.remove(index);
    }

    public static void clear() {
        collection.clear();
    }

    public static void save(String path) {
        // TODO: реализовать сохранение коллекции в файл
    }

    public static String getAllElementsToString() {
        StringBuilder result = new StringBuilder();
        collection.forEach(item -> result.append(item).append("\n"));
        return result.toString();
    }
}