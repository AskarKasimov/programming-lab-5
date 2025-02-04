package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;

public class RemoveGreaterKeyCommand extends Command {
    public RemoveGreaterKeyCommand() {
        super("remove_greater_key", 1);
    }

    @Override
    public void execute(String[] args) {
        Long key = Long.parseLong(args[0]);
        CollectionStorage.getInstance().getCollection().entrySet().removeIf(e -> e.getKey() > key);
        outputWriter.writeOnSuccess("Ёлементы удалены");
    }

    @Override
    public String getInfo() {
        return "remove_greater_key key - удалить элементы, ключ которых превышает заданный";
    }
}
