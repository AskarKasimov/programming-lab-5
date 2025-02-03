package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.exception.NoSuchIdException;

import java.io.IOException;

public class FilterByAlbumsCountCommand extends Command {
    public FilterByAlbumsCountCommand() {
        super("filter_by_albums_count");
    }

    @Override
    public void execute(String[] args) throws NoSuchIdException, IOException {
        int albumsCount = Integer.parseInt(args[0]);
        CollectionStorage.getInstance().getCollection().forEach(band -> {
            if (band.getAlbumsCount() == albumsCount)
                outputWriter.writeOnSuccess(band.toString());
        });
    }

    @Override
    public String getInfo() {
        return "filter_by_albums_count albumsCount - вывести элементы, значение поля albumsCount которых равно заданному";
    }
}
