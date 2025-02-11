package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionManager;
import ru.askar.lab5.exception.CollectionIsEmptyException;

public class ShowCommand extends Command {
    public ShowCommand() {
        super("show", 0);
    }

    @Override
    public void execute(String[] args) throws CollectionIsEmptyException {
        if (CollectionManager.getInstance().getCollection().isEmpty())
            throw new CollectionIsEmptyException();
        else
            CollectionManager.getInstance().getCollection()
                    .forEach((id, ticket) -> outputWriter.writeOnSuccess(id + ": " + ticket.toString()));
    }

    @Override
    public String getInfo() {
        return "show - вывести все элементы коллекции в строковом представлении";
    }
}
