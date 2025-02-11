package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionManager;
import ru.askar.lab5.exception.CollectionIsEmptyException;

public class ClearCommand extends Command {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CollectionIsEmptyException {
        if (collectionManager.getCollection().isEmpty())
            throw new CollectionIsEmptyException();
        else {
            collectionManager.getCollection().clear();
            outputWriter.writeOnSuccess("Коллекция очищена");
        }
    }

    @Override
    public String getInfo() {
        return "clear - очистить коллекцию";
    }
}
