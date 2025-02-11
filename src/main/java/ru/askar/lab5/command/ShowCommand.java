package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionManager;
import ru.askar.lab5.exception.CollectionIsEmptyException;

public class ShowCommand extends Command {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CollectionIsEmptyException {
        if (collectionManager.getCollection().isEmpty())
            throw new CollectionIsEmptyException();
        else
            collectionManager.getCollection()
                    .forEach((id, ticket) -> outputWriter.writeOnSuccess(id + ": " + ticket.toString()));
    }

    @Override
    public String getInfo() {
        return "show - вывести все элементы коллекции в строковом представлении";
    }
}
