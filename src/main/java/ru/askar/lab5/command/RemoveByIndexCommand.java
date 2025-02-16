package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;

public class RemoveByIndexCommand extends Command {
    public RemoveByIndexCommand() {
        super("remove_at", 6);
    }

    @Override
    public void execute(String[] args) {
        int index = Integer.parseInt(args[0]);
        if (index < 0 || index >= CollectionStorage.getInstance().getCollection().size())
            throw new IndexOutOfBoundsException();
        CollectionStorage.getInstance().getCollection().remove(index);
        outputWriter.writeOnSuccess("Элемент удален");
    }

    @Override
    public String getInfo() {
        return "remove_at index - удалить элемент из коллекции по индексу";
    }
}
