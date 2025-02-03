package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.exception.CollectionIsEmptyException;

public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear");
    }

    @Override
    public void execute(String[] args) throws CollectionIsEmptyException {
        if (CollectionStorage.getInstance().getCollection().isEmpty())
            throw new CollectionIsEmptyException();
        else {
            CollectionStorage.getInstance().getCollection().clear();
            outputWriter.writeOnSuccess("Коллекция очищена");
        }
    }

    @Override
    public String getInfo() {
        return "clear - очистить коллекцию";
    }
}
