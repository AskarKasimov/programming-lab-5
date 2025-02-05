package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.exception.NoSuchKeyException;

public class RemoveByKeyCommand extends Command {
    public RemoveByKeyCommand() {
        super("remove_key", 1);
    }

    @Override
    public void execute(String[] args) throws NoSuchKeyException {
        long id = Long.parseLong(args[0]);
        if (CollectionStorage.getInstance().getCollection().remove(id) != null) {
            outputWriter.writeOnSuccess("Элемент удален");
        } else {
            throw new NoSuchKeyException();
        }
    }

    @Override
    public String getInfo() {
        return "remove_key key - удалить элемент из коллекции по его id";
    }
}
