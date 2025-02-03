package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.exception.CollectionIsEmptyException;

public class ShowCommand extends Command {
    public ShowCommand() {
        super("show", 0);
    }

    @Override
    public void execute(String[] args) throws CollectionIsEmptyException {
        if (CollectionStorage.getInstance().getCollection().isEmpty())
            throw new CollectionIsEmptyException();
        else
            CollectionStorage.getInstance().getCollection().forEach(band -> outputWriter.writeOnSuccess(band.toString()));
    }

    @Override
    public String getInfo() {
        return "show - вывести все элементы коллекции в строковом представлении";
    }
}
