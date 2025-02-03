package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.exception.NoSuchIdException;

public class RemoveCommand extends Command {
    public RemoveCommand() {
        super("remove_by_id");
    }

    @Override
    public void execute(String[] args) throws NoSuchIdException {
        long id = Long.parseLong(args[0]);
        if (!CollectionStorage.getInstance().getCollection().removeIf(item -> item.getId() == id)) {
            throw new NoSuchIdException();
        }
        outputWriter.writeOnSuccess("Элемент удален");
    }

    @Override
    public String getInfo() {
        return "remove_by_id id  - удалить элемент из коллекции по его id";
    }
}
