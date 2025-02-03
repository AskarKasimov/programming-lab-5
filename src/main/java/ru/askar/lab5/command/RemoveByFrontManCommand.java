package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.exception.NoSuchIdException;

import java.io.IOException;

public class RemoveByFrontManCommand extends Command {
    public RemoveByFrontManCommand() {
        super("remove_any_by_front_man");
    }

    @Override
    public void execute(String[] args) throws NoSuchIdException, IOException {
        if (!CollectionStorage.getInstance().getCollection().removeIf(item -> item.getFrontMan().equals(args[0]))) {
            throw new NoSuchIdException();
        }
        outputWriter.writeOnSuccess("Элемент удален");
    }

    @Override
    public String getInfo() {
        return "remove_any_by_front_man frontMan : удалить из коллекции один элемент, значение поля frontMan которого эквивалентно заданному";
    }
}
