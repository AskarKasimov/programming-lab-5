package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;

import java.util.Collections;

public class ShuffleCommand extends Command {
    public ShuffleCommand() {
        super("shuffle");
    }

    @Override
    public void execute(String[] args) {
        if (CollectionStorage.getInstance().getCollection().isEmpty())
            outputWriter.writeOnFail("Коллекция пуста");
        else {
            Collections.shuffle(CollectionStorage.getInstance().getCollection());
            outputWriter.writeOnSuccess("Коллекция перемешана");
        }
    }

    @Override
    public String getInfo() {
        return "shuffle - перемешать коллекцию";
    }
}
