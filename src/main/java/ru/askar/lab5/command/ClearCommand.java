package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;

public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear");
    }

    @Override
    public void execute(String[] args) {
        if (CollectionStorage.getInstance().getCollection().isEmpty())
            outputWriter.writeOnFail("Коллекция пуста");
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
