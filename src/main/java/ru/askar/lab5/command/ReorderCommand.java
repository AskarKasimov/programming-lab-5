package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.exception.CollectionIsEmptyException;

import java.util.Collections;

public class ReorderCommand extends Command {
    public ReorderCommand() {
        super("reorder");
    }

    @Override
    public void execute(String[] args) throws CollectionIsEmptyException {
        if (CollectionStorage.getInstance().getCollection().isEmpty())
            throw new CollectionIsEmptyException();
        else {
            Collections.reverse(CollectionStorage.getInstance().getCollection());
            outputWriter.writeOnSuccess("Коллекция перевёрнута");
        }
    }

    @Override
    public String getInfo() {
        return "reorder - перевернуть коллекцию";
    }
}
