package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;

import java.util.Collections;

public class ReorderCommand extends Command {
    public ReorderCommand() {
        super("reorder");
    }

    @Override
    public void execute(String[] args) {
        if (CollectionStorage.getInstance().getCollection().isEmpty())
            outputWriter.writeOnFail("Коллекция пуста");
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
