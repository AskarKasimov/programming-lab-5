import object.MusicBand;

import java.util.LinkedList;

public class Collection {
    private static final LinkedList<MusicBand> collection = new LinkedList<>();

    public static LinkedList<MusicBand> getCollection() {
        return collection;
    }

    public static void add(MusicBand musicBand) {
        collection.add(musicBand);
    }

    public static void remove(MusicBand musicBand) {
        collection.remove(musicBand);
    }
}
