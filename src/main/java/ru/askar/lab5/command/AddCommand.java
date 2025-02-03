package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.object.*;

public class AddCommand extends Command {
    public AddCommand() {
        super("add");
    }

    @Override
    public void execute(String[] args) {
        CollectionStorage.getInstance().getCollection().add(new MusicBand("name", new Coordinates(1.0, 123L), 0, 0, MusicGenre.POP, new Person("name", "1", Color.BROWN, Country.GERMANY, new Location(1, 1, 1, "a"))));
        outputWriter.writeOnSuccess("Элемент добавлен в коллекцию");
    }

    @Override
    public String getInfo() {
        return "add - добавить новый элемент в коллекцию";
    }

    @Override
    public String getName() {
        return "add";
    }
}
